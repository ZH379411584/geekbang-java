package com.study.homework7.jdbc;


import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author hong.zheng
 * @Date: 11/7/21 6:57 PM
 **/
public class DBUtilTest {



    /**
     *
     */
    @Test
    public void testBatchInsert() {

        long start = System.currentTimeMillis();

        //Snowflake snowflake = IdUtil.createSnowflake(1, 1);

        Connection connection = DBUtil.getConnection();
        try {
            //手动提交事务
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `t_order` (`user_id`, `create_time`, `update_time`, `status`,total_money) VALUES ( ?, ?, ?, ?,?)");

            Date date = new Date(System.currentTimeMillis());

            for (int i = 0; i < 1000000; i++) {

                //preparedStatement.setLong(1, snowflake.nextId());
                preparedStatement.setLong(1, 1L);
                preparedStatement.setDate(2, date);
                preparedStatement.setDate(3, date);
                preparedStatement.setInt(4, 1);
                preparedStatement.setBigDecimal(5,  BigDecimal.ZERO);
                //
                preparedStatement.addBatch();
                // 每次提交10万数据
                if(i%1000000==0){
                    preparedStatement.executeBatch();
                    preparedStatement.clearBatch();
                }
            }
            //
            preparedStatement.executeBatch();
            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DBUtil.close(connection);
        }


        System.out.println("cost " + (System.currentTimeMillis() - start) + " milliseconds");
        // cost 165510 milliseconds

        // cost 15441 milliseconds 使用 rewriteBatchedStatements=true

        // cost 12982 milliseconds 使用 rewriteBatchedStatements=true 使用 rewriteBatchedStatements=true 并且每次提交10万条
    }






}