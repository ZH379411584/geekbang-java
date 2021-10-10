package com.study.netty.filter;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author hong.zheng
 * @Date: 9/29/21 11:16 PM
 **/
public interface HttpRequestFilter {
    void filter(FullHttpRequest fullRequest);
}