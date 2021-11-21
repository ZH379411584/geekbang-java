package com.study.rpc.consumer.netty;

import com.alibaba.fastjson.JSON;
import com.study.rpc.api.core.RpcfxRequest;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.*;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.List;


/**
 * @author hong.zheng
 * @Date: 11/21/21 8:55 PM
 **/
@Slf4j
public class RpcMessageCode extends MessageToMessageCodec<FullHttpResponse,RpcfxRequest> {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }



    @Override
    protected void encode(ChannelHandlerContext ctx, RpcfxRequest msg, List<Object> out) throws Exception {
        log.info("encode start");
        byte body[] = JSON.toJSONString(msg).getBytes(StandardCharsets.UTF_8);

        FullHttpRequest fullHttpRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_0, HttpMethod.POST,"/",
                Unpooled.wrappedBuffer(body));


        fullHttpRequest.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json; charset=UTF-8");
        fullHttpRequest.headers().set(HttpHeaderNames.CONTENT_LENGTH, fullHttpRequest.content().readableBytes());

        log.info("encode end");
        out.add(fullHttpRequest);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, FullHttpResponse msg, List<Object> out) throws Exception {
        log.info("msg:"+msg);
        ByteBuf content = msg.content();
        byte[] bytes = new byte[content.readableBytes()];
        content.readBytes(bytes);
        log.info("response content:"+new String(bytes,StandardCharsets.UTF_8));

    }
}