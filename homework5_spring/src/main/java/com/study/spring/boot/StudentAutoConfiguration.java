package com.study.spring.boot;

import com.study.spring.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hong.zheng
 * @Date: 10/24/21 9:49 PM
 **/
@Configuration
@EnableConfigurationProperties(StudentProperties.class)
public class StudentAutoConfiguration {

    @Autowired
    private StudentProperties studentProperties;


    @Bean
    public Student student(){
        return new Student(studentProperties.getName(),studentProperties.getId());
    }
}