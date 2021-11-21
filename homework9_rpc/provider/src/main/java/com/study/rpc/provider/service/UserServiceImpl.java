package com.study.rpc.provider.service;

import com.study.rpc.api.model.User;
import com.study.rpc.api.service.UserService;

/**
 * @author hong.zheng
 * @Date: 11/21/21 1:34 PM
 **/
public class UserServiceImpl implements UserService {


    @Override
    public User findById(int id) {
        return new User(1,"liliang");
    }
}