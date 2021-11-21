package com.study.rpc.consumer;

import com.study.rpc.api.core.client.RpcFx;
import com.study.rpc.api.model.User;
import com.study.rpc.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hong.zheng
 * @Date: 11/21/21 1:43 PM
 **/
@SpringBootApplication
@RestController
public class ConsumerApp {


    public static void main(String[] args) {
        SpringApplication.run(ConsumerApp.class);

        //UserService userService = RpcFx.create(UserService.class, "http://localhost:8080/");
        //User user = userService.findById(1);
        //System.out.println("find user id=1 from server: " + user.getName());

    }
    @Autowired
    private UserService userService;


    @RequestMapping("/test")
    public void test(){
       User user=  userService.findById(1);
        System.out.println("find user id=1 from server: " + user.getName());

    }
}