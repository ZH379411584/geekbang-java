package com.study.cache.service;

import com.study.cache.mapper.UserMapper;
import com.study.cache.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hong.zheng
 * @Date: 12/5/21 8:31 PM
 **/
@Service
//@CacheConfig(cacheNames = {"userCache"})
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;


    @Override
    @Cacheable(key="#id")
    public User find(int id) {
        log.info("find start id:{}",id);
        return userMapper.find(id);
    }

    @Override
    @CachePut
    public List<User> list() {
        log.info("list start ");
        return userMapper.list();
    }
}