package com.study.geekbang.lesson1;

import java.io.*;

/**
 * @author hong.zheng
 * @Date: 9/15/21 9:07 PM
 **/
public class CustomerClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        File file = new File("Hello.xlass");
        byte [] total = new byte[0];

        try {
            FileInputStream fileInputStream = new FileInputStream(file);

            byte[] bytes = new byte[100];
            int length = -1;
            while ((length =fileInputStream.read(bytes))!=-1){
                byte[] totalTemp = new byte[total.length+length];
                System.arraycopy(total,0,totalTemp,0,total.length);
                System.arraycopy(bytes,0,totalTemp,total.length,length);
                total = totalTemp;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(total.length ==0){
            throw new ClassNotFoundException(name + "not found");
        }
        for (int i = 0; i <total.length ; i++) {
            total[i]= (byte) (255-total[i]);
        }


        return defineClass(name,total,0,total.length);
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        CustomerClassLoader customerClassLoader = new CustomerClassLoader();
        Class classT = customerClassLoader.loadClass("Hello");
        Object object = classT.newInstance();
        System.out.println(object.getClass().getName());

    }
}