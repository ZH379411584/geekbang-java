package com.study.homework8;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author hong.zheng
 * @Date: 11/7/21 9:06 PM
 **/
@SpringBootApplication
@MapperScan(basePackages = {"com.study.homework8.mapper"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class HomeWork8App {

    public static void main(String[] args) {
        SpringApplication.run(HomeWork8App.class,args);
    }
}