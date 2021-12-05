package com.study.cache.service;

import com.study.cache.model.User;
import org.springframework.cache.annotation.CacheConfig;

import java.util.List;

/**
 * @author hong.zheng
 * @Date: 12/5/21 8:30 PM
 **/
@CacheConfig(cacheNames = {"userCache"})
public interface UserService {

    User find(int id);

    List<User> list();

}
