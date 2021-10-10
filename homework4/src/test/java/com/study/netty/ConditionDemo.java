package com.study.netty;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author hong.zheng
 * @Date: 10/8/21 10:43 AM
 **/
public class ConditionDemo {
    private static int i = 0;
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        //condition.await();
        for (int j = 0; j < 100; j++) {
            new Thread(()->{
                try {
                    lock.lockInterruptibly();
                    i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //condition.signal();
                    lock.unlock();
                }
            }).start();
        }
        System.out.println("result:"+i);

    }
}