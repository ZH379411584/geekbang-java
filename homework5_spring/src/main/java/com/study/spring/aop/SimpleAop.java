package com.study.spring.aop;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zheng.hong
 * date: 2021/10/23
 * description:
 */
public class SimpleAop {
    public static void main(String[] args) {
        jdkProxy();
        cglibProxy();
    }
    private static void jdkProxy(){
        PrintHello printHello = new PrintHello();
        IPrint iPrint = (IPrint) Proxy.newProxyInstance(printHello.getClass().getClassLoader(),printHello.getClass().getInterfaces(),
                new MyInvocationHandler(printHello));
        iPrint.print();
        System.out.println(iPrint instanceof PrintHello);
    }
    private static void cglibProxy(){
        IPrint iPrint =(IPrint)new MyMethodInterceptor().getProxyObject(new PrintHello());
        iPrint.print();
        System.out.println(iPrint instanceof PrintHello);

    }

    /**
     * jdk
     */
   private static class MyInvocationHandler implements InvocationHandler{
       private Object target;

       public MyInvocationHandler(Object traget) {
           this.target = traget;
       }
       @Override
       public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
           System.out.println("方法调用前。。。。。。");
           Object object = method.invoke(target,args);
           System.out.println("方法调用后。。。。。。");
           return object;
       }
   }

    /**
     * Cglib
     */
   private static class MyMethodInterceptor implements MethodInterceptor {
       private Object object;
       public Object getProxyObject(Object object) {
           this.object = object;
           Enhancer enhancer = new Enhancer();
           enhancer.setCallback(this);
           enhancer.setSuperclass(object.getClass());
           return enhancer.create();

       }
       @Override
       public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
           System.out.println("方法调用前。。。。。。");
           Object result = methodProxy.invoke(object,objects);
           System.out.println("方法调用后。。。。。。");
           return result;
       }
   }

}
