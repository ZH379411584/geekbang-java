package com.study.rpc.provider;

import com.study.rpc.api.core.RpcfxResolver;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author hong.zheng
 * @Date: 11/21/21 5:38 PM
 **/
public class DemoResolver<T> implements RpcfxResolver<T>, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public T resolve(Class<T> serviceClass) {
        return this.applicationContext.getBean(serviceClass);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}