package com.study.homework8.dao;

import com.study.homework8.entity.Order;

import java.util.List;

/**
 * @author hong.zheng
 * @Date: 11/7/21 9:25 PM
 **/
public interface OrderDao {

    void batchInsert(List<Long> userIds);


    List<Order> selectByUserId(Long userId);


    Order selectById(Long id);
}