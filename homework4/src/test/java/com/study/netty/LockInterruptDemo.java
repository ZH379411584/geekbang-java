package com.study.netty;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author hong.zheng
 * @Date: 10/8/21 10:18 AM
 **/
public class LockInterruptDemo {

    private static final Object ob = new Object();
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        lock(lock);


        Thread thread =
        new Thread(()->{
            try {
                System.out.println("Thread start");
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        thread.interrupt();


    }

    private static void lock(Lock lock ) throws InterruptedException {

        lock.lock();
        System.out.println("13131");
    }

}