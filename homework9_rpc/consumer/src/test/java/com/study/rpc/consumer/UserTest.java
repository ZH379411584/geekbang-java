package com.study.rpc.consumer;

import com.study.rpc.api.model.User;
import com.study.rpc.api.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author hong.zheng
 * @Date: 11/21/21 7:26 PM
 **/
public class UserTest extends BaseTest {
    @Autowired
    private UserService userService;


    @Test
    public void testFindById(){
        User user = userService.findById(1);
       // System.out.println("find user id=1 from server: " + user.getName());
    }
}