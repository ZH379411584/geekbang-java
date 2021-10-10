package com.study.netty;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author hong.zheng
 * @Date: 10/10/21 8:05 PM
 **/
public class FutureTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<Integer>(()->{
            return 1;
        });

        Thread thread = new Thread(futureTask);
        thread.start();

        System.out.println("result:"+futureTask.get());


    }
}