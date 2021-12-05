package com.study.cache.controller;

import com.study.cache.service.RedisPubscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hong.zheng
 * @Date: 12/5/21 11:05 PM
 **/
@RestController
public class PubSubController {

    @Autowired
    private RedisPubscribe redisPubscribe;

    @RequestMapping("/pub")
    public void pub(@RequestParam("topic")String topic,@RequestParam("message")String message){
        redisPubscribe.sendMessage(topic,message);
    }
}