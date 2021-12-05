package com.study.cache.controller;

import com.study.cache.model.User;
import com.study.cache.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hong.zheng
 * @Date: 12/5/21 8:30 PM
 **/

@RestController
public class UserController {
    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Autowired
    UserService userService;

    @RequestMapping("/user/find")
    User find(@RequestParam("id") Integer id) {
        return userService.find(id);
        //return new User(1,"KK", 28);
    }

    @RequestMapping("/user/list")
    List<User> list() {
        return userService.list();
//        return Arrays.asList(new User(1,"KK", 28),
//                             new User(2,"CC", 18));
    }
}