package com.study.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author hong.zheng
 * @Date: 12/5/21 8:25 PM
 **/
@EnableCaching
@SpringBootApplication
public class CacheApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(CacheApplication.class,args);
    }
}