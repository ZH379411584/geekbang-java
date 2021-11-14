package com.study.homework8.dao;

import com.study.homework8.entity.Order;
import com.study.homework8.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author hong.zheng
 * @Date: 11/7/21 9:25 PM
 **/
@Service
public class OrderDaoImpl implements OrderDao {

    static {
        System.out.println("OrderDao  init static");

    }
    @Autowired
    private OrderMapper orderMapper;

    {
        System.out.println("OrderDao  init");
    }


    @Override
    public void batchInsert(List<Long> userIds) {
        userIds.forEach(e->orderMapper.insert(e));
    }

    @Override
    public List<Order> selectByUserId(Long userId) {
        return orderMapper.selectByUserId(userId);
    }

    @Override
    public Order selectById(Long id) {
        return orderMapper.selectById(id);
    }
}
