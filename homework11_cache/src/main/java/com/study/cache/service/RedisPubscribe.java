package com.study.cache.service;

import io.netty.util.CharsetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Service;

/**
 * @author hong.zheng
 * @Date: 12/5/21 11:01 PM
 **/
@Service
public class RedisPubscribe {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;


    /**
     *
     * @param topic
     * @param message
     */
    public void sendMessage(String topic,String message){
        redisConnectionFactory.getConnection().publish(topic.getBytes(CharsetUtil.UTF_8),message.getBytes());
    }
}