package com.study.homework7.annotation;

/**
 * @author hong.zheng
 * @Date: 11/7/21 9:12 PM
 **/

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
public @interface DataSourceSwitch {

    String dataSource() default "";
}