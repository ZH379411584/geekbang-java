package com.study.netty.filter;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author hong.zheng
 * @Date: 9/29/21 11:17 PM
 **/
public interface HttpResponseFilter {
    void filter(FullHttpRequest fullRequest);

}