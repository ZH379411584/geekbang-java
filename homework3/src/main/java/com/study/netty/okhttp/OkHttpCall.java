package com.study.netty.okhttp;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import okhttp3.*;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author hong.zheng
 * @Date: 9/29/21 10:44 PM
 **/
public class OkHttpCall {

    private static  OkHttpClient client = new OkHttpClient();

    /**
     *
     * @param url
     * @param headers
     * @param ctx
     * @param keepAlive
     * @throws IOException
     */
    public static void httpCall(String url, Map<String,String> headers,
                                ChannelHandlerContext ctx,boolean keepAlive) throws IOException {


        Headers.Builder headersBuilder = new Headers.Builder();
        Optional.ofNullable(headers).map(Map::entrySet).ifPresent(
           entries -> entries.forEach((entry)->{
               headersBuilder.add(entry.getKey(),entry.getValue());
           })
        );


        Headers headersOkHttp = headersBuilder.build();


        Request request = new Request.Builder()
                .url(url)
                .headers(headersOkHttp)
                .build();



        Call call = client.newCall(request);
        //第四步 call对象调用enqueue()方法，通过Callback()回调拿到响应体Response
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //异步请求失败之后的回调
                e.printStackTrace();
                call.cancel();
                sendResponse(null,false,ctx,keepAlive);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                //异步请求成功之后的回调
                String responseBody = response.body().string();
                System.out.println("responseBody:"+responseBody);
                sendResponse(responseBody,true,ctx,keepAlive);

            }
        });

       // String responseBody = client.newCall(request).execute().body().string();
       // sendResponse(responseBody,true,ctx,keepAlive);



    }

    /**
     *
     * @param responseBody
     * @param success
     * @param ctx
     * @param keepAlive
     */
    public static void sendResponse(String responseBody,boolean success,ChannelHandlerContext ctx,boolean keepAlive) {
        FullHttpResponse response = null;
        if (success) {
            byte[] body = responseBody.getBytes();

            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));

            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", response.content().readableBytes());
        } else {
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);

        }
        if (!keepAlive) {
            ctx.write(response).addListener(ChannelFutureListener.CLOSE);
        } else {
            ctx.write(response);
        }
        ctx.flush();
    }
}