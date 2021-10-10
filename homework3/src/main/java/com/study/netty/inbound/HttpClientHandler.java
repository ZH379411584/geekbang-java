package com.study.netty.inbound;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * @author hong.zheng
 * @Date: 10/10/21 10:12 PM
 **/
public class HttpClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        URI uri = new URI("/hello");
        FullHttpRequest fullHttpRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_0, HttpMethod.GET,uri.toASCIIString());

        ctx.writeAndFlush(fullHttpRequest);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Msg");
        if(msg instanceof FullHttpResponse){
            FullHttpResponse response = (FullHttpResponse) msg;
            ByteBuf buf = response.content();

            String result = buf.toString(CharsetUtil.UTF_8);
            System.out.println(result);
        }
    }
}