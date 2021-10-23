package com.study.spring.aop;

/**
 * @author zheng.hong
 * date: 2021/10/23
 * description:
 */
public class PrintHello implements IPrint{
    @Override
    public void print() {
        System.out.println("Hello");
        //throw new RuntimeException("Hello is Error");
    }
}
