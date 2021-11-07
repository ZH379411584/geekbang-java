package com.study.homework7.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author hong.zheng
 * @Date: 11/7/21 9:50 PM
 **/
@Configuration
public class PrimaryDataSourceConfig {

    private final static String PACKAGE = "";



    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari.primary")
    public DataSource dataSource(){

        return new HikariDataSource();
    }


    @Bean(name = "primaryTransactionManager")
    public DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(this.dataSource());
    }


}