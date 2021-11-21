package com.study.rpc.api.core;

import java.util.List;

/**
 * @author hong.zheng
 * @Date: 11/21/21 1:52 PM
 **/
public interface Router {

    List<String> route(List<String> urls);
}