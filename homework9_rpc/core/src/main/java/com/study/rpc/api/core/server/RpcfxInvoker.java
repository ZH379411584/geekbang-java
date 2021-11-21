package com.study.rpc.api.core.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.study.rpc.api.core.RpcfxRequest;
import com.study.rpc.api.core.RpcfxResolver;
import com.study.rpc.api.core.RpcfxResponse;

import java.lang.reflect.Method;
import java.util.Arrays;

public class RpcfxInvoker {

    private RpcfxResolver resolver;

    public RpcfxInvoker(RpcfxResolver resolver){
        this.resolver = resolver;
    }

    public RpcfxResponse invoke(RpcfxRequest request) {
        RpcfxResponse response = new RpcfxResponse();
        String serviceClass = request.getServiceClass();

        // 作业1：改成泛型和反射
        //this.applicationContext.getBean(serviceClass);


        try {
            Class classC = Class.forName(request.getServiceClass());
            Object service = resolver.resolve(classC);

            Method method = resolveMethodFromClass(service.getClass(), request.getMethod());
            // dubbo, fastjson,
            Object result = method.invoke(service, request.getParams());
            // 两次json序列化能否合并成一个
            response.setResult(JSON.toJSONString(result, SerializerFeature.WriteClassName));
            response.setStatus(true);
            return response;
        }catch (Exception e) {
            e.printStackTrace();
            response.setException(e);
            response.setStatus(false);
            return response;
        }
    }

    private Method resolveMethodFromClass(Class<?> klass, String methodName) {
        return Arrays.stream(klass.getMethods()).filter(m -> methodName.equals(m.getName())).findFirst().get();
    }

}
