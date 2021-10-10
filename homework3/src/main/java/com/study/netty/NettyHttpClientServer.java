package com.study.netty;

import com.study.netty.inbound.HttpClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author hong.zheng
 * @Date: 9/29/21 10:43 PM
 **/
public class NettyHttpClientServer {

    public static void main(String[] args) {
        int port = 8081;
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();

            b.group(group).channel(NioSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>(){

                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            ChannelPipeline channelPipeline = channel.pipeline();
                            channelPipeline.addLast(new HttpServerCodec());
                            channelPipeline.addLast(new HttpObjectAggregator(1024 * 1024));
                            channelPipeline.addLast(new HttpContentDecompressor());
                            channelPipeline.addLast(new HttpClientHandler());

                        }
                    });

            ChannelFuture channelFuture = b.connect("127.0.0.1",port).sync();
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if(future.isSuccess()){
                        future.channel();
                    }
                }
            });
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }

    }
}