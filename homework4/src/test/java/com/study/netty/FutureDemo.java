package com.study.netty;

import java.util.concurrent.*;

/**
 * @author hong.zheng
 * @Date: 10/10/21 7:59 PM
 **/
public class FutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(1000);
                return 2;
            }
        });
        System.out.println("result:"+ future.get());
        executorService.shutdown();

    }
}