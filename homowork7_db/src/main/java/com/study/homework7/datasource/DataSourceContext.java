package com.study.homework7.datasource;

/**
 * @author hong.zheng
 * @Date: 11/7/21 9:09 PM
 * 数据源上下文
 **/
public class DataSourceContext {

    private static ThreadLocal<String> dataSourceNameThreadLocal = new ThreadLocal<>();


    public static void setDataSourceKey(String dataSourceKey){

        dataSourceNameThreadLocal.set(dataSourceKey);
    }


    public static String getDataSourceKey(){
        return dataSourceNameThreadLocal.get();
    }

    public static void clear(){
        dataSourceNameThreadLocal.remove();
    }
}