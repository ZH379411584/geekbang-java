package com.study.homework7.dao;

import com.study.homework7.BaseTest;
import com.study.homework7.entity.Order;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author hong.zheng
 * @Date: 11/7/21 10:32 PM
 **/
public class OrderDaoTest extends BaseTest {

    @Autowired
    private OrderDao orderDao;

    @Test
    public void testSelectByUserId(){
        List<Order> orders = orderDao.selectByUserId(2L);
        orders.stream().forEach(System.out::println);

    }

    @Test
    public void testSelectById(){
        Order order = orderDao.selectById(1L);
        System.out.println(order);
    }


}