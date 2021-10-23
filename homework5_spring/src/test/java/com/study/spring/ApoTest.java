package com.study.spring;

import com.study.spring.aop.IPrint;
import com.study.spring.model.KClass;
import com.study.spring.model.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zheng.hong
 * date: 2021/10/23
 * description:
 */
public class ApoTest {
    @Test
    public  void testAop() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-Context.xml");


        IPrint iPrint = applicationContext.getBean(IPrint.class);
        iPrint.print();

    }

    @Test
    public  void testXmlApplicationContext() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-Context.xml");
       /* Student student = applicationContext.getBean(Student.class);
        System.out.println(student);*/
        System.out.println("------------------------------");

        KClass kClass = applicationContext.getBean(KClass.class);
        kClass.getStudentList().forEach(System.out::println);
    }

    @Test
    public  void testAnnotationApplicationContext() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.study.spring");
        //Student student = applicationContext.getBean(Student.class);
        //System.out.println(student);


        System.out.println("------------------------------");
        KClass kClass = applicationContext.getBean(KClass.class);
        kClass.getStudentList().forEach(System.out::println);
    }
}
