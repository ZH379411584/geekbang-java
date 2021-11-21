package com.study.rpc.provider;

import com.study.rpc.api.core.RpcfxRequest;
import com.study.rpc.api.core.RpcfxResolver;
import com.study.rpc.api.core.RpcfxResponse;
import com.study.rpc.api.core.server.RpcfxInvoker;
import com.study.rpc.api.service.UserService;
import com.study.rpc.provider.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hong.zheng
 * @Date: 11/21/21 5:41 PM
 **/
@SpringBootApplication
@Slf4j
@RestController
public class ProviderApp {


    public static void main(String[] args)
    {
        SpringApplication.run(ProviderApp.class);
    }


    @Autowired
    private RpcfxInvoker invoker;

    @PostMapping("/")
    public RpcfxResponse invoke(@RequestBody RpcfxRequest request) {
        log.info("invoke start request request:{}",request);
        return invoker.invoke(request);
    }


    @Bean
    public RpcfxInvoker createInvoker(@Autowired RpcfxResolver resolver){
        return new RpcfxInvoker(resolver);
    }

    @Bean
    public RpcfxResolver createResolver(){
        return new DemoResolver();
    }


    @Bean(name = "com.study.rpc.api.service.UserService")
    public UserService createUserService(){
        return new UserServiceImpl();
    }


}