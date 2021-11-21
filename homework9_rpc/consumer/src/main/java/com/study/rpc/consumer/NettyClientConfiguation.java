package com.study.rpc.consumer;

import com.study.rpc.consumer.netty.RpcMessageCode;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.codec.http.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author hong.zheng
 * @Date: 11/21/21 9:09 PM
 **/
@Configuration
public class NettyClientConfiguation {

    public static  Channel channel = null;


    @Bean(destroyMethod = "shutdownGracefully")
    public EventLoopGroup eventLoopGroup() throws InterruptedException {
        int port = 8080;
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap b = new Bootstrap();

        b.group(group).channel(NioSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>(){

                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        ChannelPipeline channelPipeline = channel.pipeline();
                        channelPipeline.addLast(new HttpRequestEncoder());
                        channelPipeline.addLast(new HttpResponseDecoder());
                        channelPipeline.addLast(new HttpObjectAggregator(1024 * 1024));
                        channelPipeline.addLast(new RpcMessageCode());

                    }
                });

        ChannelFuture channelFuture = b.connect("127.0.0.1",port).sync();
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if(future.isSuccess()){
                    System.out.println("success");
                    channel = future.channel();
                }
            }
        });
        return group;
    }


    public static void main(String[] args) throws InterruptedException {
        NettyClientConfiguation nettyClientConfiguation = new NettyClientConfiguation();
        nettyClientConfiguation.eventLoopGroup();

    }
}