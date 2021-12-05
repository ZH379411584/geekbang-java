package com.study.cache.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author hong.zheng
 * @Date: 12/5/21 10:20 PM
 **/
@Service
@Slf4j
public class LockService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    private final static String LOCK_KEY = "lockKey:";


    /**
     *
     * @param key
     * @param lockValue
     * @param minute
     * @return
     */
    public boolean lock(String key,String lockValue,Integer minute){
        Assert.notNull(key,"key can't be null");
        if(minute<=0){
            throw new IllegalArgumentException("lock minute is illegal");
        }
        String lockKey = LOCK_KEY +key;


        return redisTemplate.opsForValue().setIfAbsent(lockKey,lockValue,minute, TimeUnit.MINUTES);
    }


    public boolean unLock(String key,String lockValue){

        String script = "if (redis.call('get', KEYS[1]) == ARGV[1]) then " +
                "redis.call('del', KEYS[1]) end";


        String lockKey = LOCK_KEY +key;

        DefaultRedisScript<Long> redisScript = new DefaultRedisScript();
        //用于解锁的lua脚本位置
        redisScript.setLocation(new ClassPathResource("unlock.lua"));
        redisScript.setResultType(Long.class);
        //没有指定序列化方式，默认使用上面配置的
        Object result = redisTemplate.execute(redisScript, Arrays.asList(lockKey), lockValue);

        return Long.valueOf(1).equals(result);
    }
}