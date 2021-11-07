package com.study.homework7.dao;

import com.study.homework7.annotation.DataSourceSwitch;
import com.study.homework7.datasource.DataSourceConstant;
import com.study.homework7.entity.Order;

import java.util.List;

/**
 * @author hong.zheng
 * @Date: 11/7/21 9:25 PM
 **/
public interface OrderDao {


    List<Order> selectByUserId(Long userId);


    Order selectById(Long id);
}