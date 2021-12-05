package com.study.cache.controller;

import com.study.cache.service.LockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author hong.zheng
 * @Date: 12/5/21 10:43 PM
 **/
@RestController
@Slf4j
public class LockController {

    @Autowired
    private LockService lockService;

    private static String TEST_LOCK = "testLock";


    @RequestMapping("/lock")
    public String lock(){
        String uid = UUID.randomUUID().toString();
        boolean lockSuccess = lockService.lock(TEST_LOCK,uid,5);
        if(lockSuccess){
            return uid;
        }
        return "fail to lock";
    }


    @RequestMapping("/unLock")
    public boolean unLock(@RequestParam("lockValue")String lockValue){

        return lockService.unLock(TEST_LOCK,lockValue);


    }
}