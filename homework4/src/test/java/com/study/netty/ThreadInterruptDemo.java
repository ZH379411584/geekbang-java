package com.study.netty;

/**
 * @author hong.zheng
 * @Date: 10/8/21 3:47 PM
 **/
public class ThreadInterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            try {
                System.out.println("Thread start");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread end");
        });
        thread.start();
        Thread.sleep(500);
        thread.interrupt();

        System.out.println(thread.isInterrupted());
        thread.interrupt();
        System.out.println(thread.isInterrupted());

    }
}