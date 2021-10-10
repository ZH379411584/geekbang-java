package com.study.netty.filter;

import io.netty.handler.codec.http.FullHttpRequest;

import java.util.UUID;

/**
 * @author hong.zheng
 * @Date: 10/10/21 8:47 PM
 **/
public class HeadRequestFilter implements HttpRequestFilter {

    @Override
    public void filter(FullHttpRequest fullRequest) {
        fullRequest.headers().add("RequestId", UUID.randomUUID().toString());
    }
}