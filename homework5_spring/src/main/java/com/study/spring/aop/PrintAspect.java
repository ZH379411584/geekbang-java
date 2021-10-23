package com.study.spring.aop;


import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author zheng.hong
 * date: 2021/10/23
 * description:
 */
public class PrintAspect {

    public void before(){
        System.out.println("before");
    }

    public void after(){
        System.out.println("before");
    }

    public void around(ProceedingJoinPoint joinpoint) throws Throwable {
        System.out.println("around start");
        joinpoint.proceed();
        System.out.println("around end");
    }

    public void handleEx(JoinPoint jp, Throwable e){
        e.printStackTrace();
    }
}
