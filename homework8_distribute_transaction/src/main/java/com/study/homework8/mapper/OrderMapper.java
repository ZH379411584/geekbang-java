package com.study.homework8.mapper;

import com.study.homework8.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hong.zheng
 * @Date: 11/7/21 9:34 PM
 **/
public interface OrderMapper {

    void insert(@Param("userId")Long userId);

    List<Order> selectByUserId(Long userId);


    Order selectById(Long id);

}