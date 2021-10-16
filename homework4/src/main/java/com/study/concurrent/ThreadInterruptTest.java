package com.study.concurrent;

/**
 * @author hong.zheng
 * @Date: 10/16/21 11:49 AM
 **/
public class ThreadInterruptTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread =
        new Thread(()->{
            while (true){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("Thread isInterrupted");
                    break;
                }
                try{
                    // Thread.sleep()中断而抛出异常，此时，它会清楚中断标识。
                    Thread.sleep(2900);
                }catch (Exception e){
                    e.printStackTrace();
                    //
                    Thread.currentThread().interrupt();
                }
                Thread.yield();
            }
        });
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}