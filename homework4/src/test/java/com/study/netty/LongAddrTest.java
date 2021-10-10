package com.study.netty;

import java.util.concurrent.atomic.LongAdder;

/**
 * @author hong.zheng
 * @Date: 10/8/21 8:48 PM
 **/
public class LongAddrTest {
    public static void main(String[] args)
    {
        LongAdder adder = new LongAdder();
        adder.add(1);

        for (int i = 0; i <100 ; i++) {
            new Thread(()->{
                adder.add(1);
            }).start();
        }

        System.out.println(adder.sum());
    }
}