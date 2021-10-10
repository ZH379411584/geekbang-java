package com.study.netty;

import java.util.concurrent.locks.LockSupport;

/**
 * @author hong.zheng
 * @Date: 10/8/21 11:05 AM
 **/
public class LockSupportDemo {

    private int i = 0;

    public static void main(String[] args) {
        LockSupportDemo lockSupportDemo = new LockSupportDemo();

        for (int j = 0; j <50; j++) {
            Thread thread = new Thread(()->{
                try{
                    LockSupport.park(lockSupportDemo);
                    lockSupportDemo.i++;
                }catch (Exception e){
                    e.printStackTrace();
                }finally {

                }
            } );
            thread.start();
            LockSupport.unpark(thread);

        }

        System.out.println(lockSupportDemo.i);



    }
}