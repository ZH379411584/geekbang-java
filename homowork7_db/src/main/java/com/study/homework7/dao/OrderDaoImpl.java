package com.study.homework7.dao;
import com.study.homework7.annotation.DataSourceSwitch;
import com.study.homework7.dao.OrderDao;
import com.study.homework7.datasource.DataSourceConstant;
import com.study.homework7.entity.Order;
import com.study.homework7.mapper.OrderMapper;
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
    @DataSourceSwitch(dataSource = DataSourceConstant.PRIMARY)
    public List<Order> selectByUserId(Long userId) {
        return orderMapper.selectByUserId(userId);
    }

    @Override
    @DataSourceSwitch(dataSource = DataSourceConstant.SECOND)
    public Order selectById(Long id) {
        return orderMapper.selectById(id);
    }
}
