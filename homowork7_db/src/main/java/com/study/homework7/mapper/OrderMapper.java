package com.study.homework7.mapper;

import com.study.homework7.entity.Order;

import java.util.List;

/**
 * @author hong.zheng
 * @Date: 11/7/21 9:34 PM
 **/
public interface OrderMapper {

    List<Order> selectByUserId(Long userId);


    Order selectById(Long id);

}