package com.study.spring.model;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zheng.hong
 * date: 2021/10/23
 * description:
 */
@Component
public class KClass implements ApplicationContextAware {

    public KClass() {
    }

    public KClass(List<Student> studentList) {
        this.studentList = studentList;
    }

    //@Autowired
    //@Resource
    private List<Student> studentList;

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
       //studentList = new ArrayList<>(applicationContext.getBeansOfType(Student.class).values());
    }
}
