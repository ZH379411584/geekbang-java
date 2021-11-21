package com.study.rpc.api.service;

import com.study.rpc.api.annoation.RpcInterface;
import com.study.rpc.api.model.User;

/**
 * @author hong.zheng
 * @Date: 11/21/21 1:33 PM
 **/
public interface UserService {

    @RpcInterface
    User findById(int id);

}