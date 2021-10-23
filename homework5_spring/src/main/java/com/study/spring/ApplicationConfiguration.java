package com.study.spring;

import com.study.spring.model.KClass;
import com.study.spring.model.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author zheng.hong
 * date: 2021/10/23
 * description:
 */
@Configuration
public class ApplicationConfiguration {
    @Bean("student1")
    public Student student(){

        Student student = new Student("liliang",2L);
        return student;
    }

    @Bean("student2")
    public Student student2(){

        Student student = new Student("mindong",1L);
        return student;
    }

   /* @Bean
    public KClass kClass(List<Student> studentList){

        KClass kClass = new KClass();
        kClass.setStudentList(studentList);
        return kClass;
    }*/
}
