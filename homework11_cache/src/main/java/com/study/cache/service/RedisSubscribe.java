package com.study.cache.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * @author hong.zheng
 * @Date: 12/5/21 11:01 PM
 **/
@Slf4j
public class RedisSubscribe implements MessageListener {


    @Override
    public void onMessage(Message message, byte[] pattern) {
        String topic = new String(message.getChannel());
        String body = new String(message.getBody());
        log.info("topic:{},body:{}",topic,body);

    }
}