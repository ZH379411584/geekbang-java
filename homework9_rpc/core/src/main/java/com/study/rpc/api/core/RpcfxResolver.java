package com.study.rpc.api.core;

public interface RpcfxResolver<T> {

    T resolve(Class<T> serviceClass);

}
