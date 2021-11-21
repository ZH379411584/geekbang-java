package com.study.rpc.consumer.aop;

import com.alibaba.fastjson.JSON;
import com.study.rpc.api.annoation.RpcInterface;
import com.study.rpc.api.core.Filter;
import com.study.rpc.api.core.RpcfxRequest;
import com.study.rpc.api.core.RpcfxResponse;
import com.study.rpc.api.core.client.RpcFx;
import com.study.rpc.consumer.NettyClientConfiguation;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author hong.zheng
 * @Date: 11/21/21 7:11 PM
 **/
@Aspect
@Component
@Slf4j
public class RpcAspect {


    @Autowired(required = false)
    private List<Filter> filters;

    static {
        System.out.println("RpcAspect static method ");
    }



    @Pointcut("@annotation(com.study.rpc.api.annoation.RpcInterface)")
    public void cut() {

    }



    @Around("cut()")
    public Object around(ProceedingJoinPoint joinpoint) throws Throwable {

        log.info("rpc start ");

        Method method = ((MethodSignature) joinpoint.getSignature()).getMethod();
        RpcInterface rpcInterface = method.getAnnotation(RpcInterface.class);

        if (null == rpcInterface) {
            return joinpoint.proceed();
        }

        RpcfxRequest request = new RpcfxRequest();
        request.setServiceClass(joinpoint.getTarget().getClass().getInterfaces()[0].getName());
        request.setMethod(method.getName());
        request.setParams(joinpoint.getArgs());

        if (null != filters) {
            for (Filter filter : filters) {
                if (!filter.filter(request)) {
                    return null;
                }
            }
        }
        // todo 获取同步发送结果
        Channel channel = NettyClientConfiguation.channel;
        channel.writeAndFlush(request).sync().addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {

            }
        });

        //
        //RpcfxResponse response = RpcFx.post(request, "http://localhost:8080/");

        // 加filter地方之三
        // Student.setTeacher("cuijing");

        // 这里判断response.status，处理异常
        // 考虑封装一个全局的RpcfxException

        //return JSON.parse(response.getResult().toString());
        return null;

    }
}