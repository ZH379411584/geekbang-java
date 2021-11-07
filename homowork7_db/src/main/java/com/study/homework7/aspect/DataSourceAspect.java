package com.study.homework7.aspect;

import com.study.homework7.annotation.DataSourceSwitch;
import com.study.homework7.datasource.DataSourceContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author hong.zheng
 * @Date: 11/7/21 9:13 PM
 **/
@Aspect
@Component
public class DataSourceAspect {
    {
        System.out.println("DataSourceAspect init");
    }


    @Pointcut("@annotation(com.study.homework7.annotation.DataSourceSwitch)")
    public void pointCut() {

    }
    @Before("pointCut()")
    public void before(JoinPoint joinPoint){
        System.out.println("before");

    }


    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinpoint) throws Throwable {
        Method method =((MethodSignature)joinpoint.getSignature()).getMethod();
        DataSourceSwitch dataSourceSwitch = method.getAnnotation(DataSourceSwitch.class);
        if(null!= dataSourceSwitch){
            DataSourceContext.setDataSourceKey(dataSourceSwitch.dataSource());
        }
        try{
            return joinpoint.proceed();
        }finally {
            DataSourceContext.clear();
        }


    }
}