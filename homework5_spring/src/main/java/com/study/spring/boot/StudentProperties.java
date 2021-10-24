package com.study.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author hong.zheng
 * @Date: 10/24/21 9:44 PM
 **/
@ConfigurationProperties(prefix = "student")
public class StudentProperties {

    private String name;

    private Long id;


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
}