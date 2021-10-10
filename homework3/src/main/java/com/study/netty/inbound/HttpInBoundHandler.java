package com.study.netty.inbound;

import com.study.netty.NamedThreadFactory;
import com.study.netty.filter.HeadRequestFilter;
import com.study.netty.filter.HttpRequestFilter;
import com.study.netty.okhttp.OkHttpCall;
import com.study.netty.route.RandomRouteSelect;
import com.study.netty.route.RouteSelect;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.util.ReferenceCountUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @author hong.zheng
 * @Date: 9/29/21 10:51 PM
 **/
public class HttpInBoundHandler extends ChannelInboundHandlerAdapter {

    private static RouteSelect routeSelect = new RandomRouteSelect();

    private static HttpRequestFilter httpRequestFilter = new HeadRequestFilter();

    private  ThreadPoolExecutor proxyService = null;

    {
        int cores = Runtime.getRuntime().availableProcessors();
        long keepAliveTime = 1000;
        int queueSize = 2048;
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
        proxyService = new ThreadPoolExecutor(cores, cores,
                keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueSize),
                new NamedThreadFactory("proxyService"), handler);

    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        try {
            FullHttpRequest fullRequest = (FullHttpRequest) msg;

            //System.out.println(fullRequest.uri());
            //System.out.println(fullRequest.headers());
            //System.out.println(fullRequest.method().name());

            ByteBuf buf = fullRequest.content();
            System.out.println("requestContent:"+buf.readCharSequence(buf.readableBytes(), StandardCharsets.UTF_8));

            httpRequestFilter.filter(fullRequest);


            String toUrl = routeSelect.select(fullRequest.uri());

            proxyService.execute(()->{
                try {
                    OkHttpCall.httpCall(toUrl, getHeader(fullRequest), ctx, HttpUtil.isKeepAlive(fullRequest));

                } catch (IOException e) {
                    e.printStackTrace();
                    ctx.fireExceptionCaught(e);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
            ctx.close();
        } finally {
            ReferenceCountUtil.refCnt(msg);
        }
    }

    private static Map<String,String> getHeader(FullHttpRequest fullRequest ){

        Map<String,String> header = new HashMap<>();
        fullRequest.headers().entries().forEach(entry->{
            header.put(entry.getKey(),entry.getValue());
        });
        return header;
    }
}