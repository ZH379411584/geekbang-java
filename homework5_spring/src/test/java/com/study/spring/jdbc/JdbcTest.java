package com.study.spring.jdbc;

import org.junit.Test;

import java.sql.*;

/**
 * @author hong.zheng
 * @Date: 10/24/21 10:34 PM
 **/
public class JdbcTest {
    @Test
    public void testInsert() throws SQLException {
        Connection connection = DBUtil.getConnection();
        Statement statement =
                connection.createStatement();

        int rows = statement.executeUpdate("insert into t_user values(5,'zhegnhong',now(),now())");

        System.out.println("rows:"+rows);

        DBUtil.close(connection,statement);
    }

    @Test
    public void testInsertUsePrepare() throws SQLException {
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("insert into t_user values (?,?,?,?)");

        preparedStatement.setLong(1,4l);
        preparedStatement.setString(2,"zengting");
        preparedStatement.setDate(3,new Date(System.currentTimeMillis()));
        preparedStatement.setDate(4,new Date(System.currentTimeMillis()));

        int rows = preparedStatement.executeUpdate();

        System.out.println("rows:"+rows);

        DBUtil.close(connection,preparedStatement);
    }

    @Test
    public void testQuery() throws SQLException {
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("select * from t_user ");

        //preparedStatement.setLong(1,4l);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            Long id = resultSet.getLong(1);
            String name = resultSet.getString(2);
            Date joinDate = resultSet.getDate(3);
            Date bothDate = resultSet.getDate(4);
            System.out.println("id:"+id+",name:"+name+",joinDate"+joinDate+",bothDate:"+bothDate);

        }
        resultSet.next();


        DBUtil.close(connection,preparedStatement,resultSet);
    }
}