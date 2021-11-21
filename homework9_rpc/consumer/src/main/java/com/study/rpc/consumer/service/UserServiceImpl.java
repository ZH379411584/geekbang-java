package com.study.rpc.consumer.service;

import com.study.rpc.api.annoation.RpcInterface;
import com.study.rpc.api.model.User;
import com.study.rpc.api.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author hong.zheng
 * @Date: 11/21/21 7:09 PM
 * 为了实现AOP，实现了接口。
 **/
@Service
public class UserServiceImpl implements UserService {
    @Override
    @RpcInterface
    public User findById(int id) {
        return null;
    }
}