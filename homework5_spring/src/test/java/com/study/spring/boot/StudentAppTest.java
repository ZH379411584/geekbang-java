package com.study.spring.boot;

import com.study.spring.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author hong.zheng
 * @Date: 10/24/21 9:59 PM
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentAppTest {

    @Autowired
    private Student student;

    @Test
    public void testStudent(){
        System.out.println(student);
    }
}