package com.study.homework7.config;

import com.study.homework7.datasource.DataSourceConstant;
import com.study.homework7.datasource.MultiDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hong.zheng
 * @Date: 11/7/21 10:14 PM
 **/
//@Configuration
public class DatasourceConfig {

    @Bean
    @Primary
    public MultiDataSource multiDataSource(@Qualifier("primaryDataSource") DataSource primaryDataSource,
                                    @Qualifier("secondDataSource") DataSource secondDataSource){

        MultiDataSource multiDataSource = new MultiDataSource();
        Map<Object,Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceConstant.PRIMARY,primaryDataSource);
        dataSourceMap.put(DataSourceConstant.SECOND,secondDataSource);

        multiDataSource.setTargetDataSources(dataSourceMap);
        return multiDataSource;
    }


}