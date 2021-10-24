package com.study.spring.boot;

import com.study.spring.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hong.zheng
 * @Date: 10/24/21 9:53 PM
 **/
@SpringBootApplication
public class StudentApp {
    public static void main(String[] args)
    {
        SpringApplication.run(StudentApp.class,args);
    }




}