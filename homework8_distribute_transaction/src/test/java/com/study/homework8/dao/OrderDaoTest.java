package com.study.homework8.dao;

import com.study.homework8.BaseTest;
import com.study.homework8.entity.Order;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * @author hong.zheng
 * @Date: 11/7/21 10:32 PM
 **/
public class OrderDaoTest extends BaseTest {

    @Autowired
    private OrderDao orderDao;

    @Test
    public void batchInsert(){
        orderDao.batchInsert(Arrays.asList(1L,2L));

    }

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