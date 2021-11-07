package com.study.homework7;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author hong.zheng
 * @Date: 11/7/21 9:06 PM
 **/
@SpringBootApplication
@MapperScan(basePackages = {"com.study.homework7.mapper"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class HomeWork7App {

    public static void main(String[] args) {
        SpringApplication.run(HomeWork7App.class,args);
    }
}