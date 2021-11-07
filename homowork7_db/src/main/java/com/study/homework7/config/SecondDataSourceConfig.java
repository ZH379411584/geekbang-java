package com.study.homework7.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author hong.zheng
 * @Date: 11/7/21 10:10 PM
 **/
@Configuration
public class SecondDataSourceConfig {




    @Bean(name = "secondDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari.second")
    public DataSource dataSource(){

        return new HikariDataSource();
    }


    @Bean(name = "secondTransactionManager")
    public DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(this.dataSource());
    }



}