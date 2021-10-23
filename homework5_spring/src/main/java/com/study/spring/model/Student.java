package com.study.spring.model;

/**
 * @author zheng.hong
 * date: 2021/10/23
 * description:
 */
public class Student {
    private String name;
    private Long id;

    public Student() {
    }

    public Student(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
