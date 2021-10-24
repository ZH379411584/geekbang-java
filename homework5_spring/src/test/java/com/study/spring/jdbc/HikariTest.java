package com.study.spring.jdbc;

import com.study.spring.boot.StudentApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.*;

/**
 * @author hong.zheng
 * @Date: 10/24/21 9:59 PM
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StudentApp.class)
public class HikariTest {

    @Autowired
    private DataSource dataSource;



    @Test
    public void testInsert() throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement =
                connection.createStatement();

        int rows = statement.executeUpdate("insert into t_user values(5,'zhegnhong',now(),now())");

        System.out.println("rows:"+rows);

    }

    @Test
    public void testInsertUsePrepare() throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("insert into t_user values (?,?,?,?)");

        preparedStatement.setLong(1,4l);
        preparedStatement.setString(2,"zengting");
        preparedStatement.setDate(3,new Date(System.currentTimeMillis()));
        preparedStatement.setDate(4,new Date(System.currentTimeMillis()));

        int rows = preparedStatement.executeUpdate();

        System.out.println("rows:"+rows);
    }

    @Test
    public void testQuery() throws SQLException {
        Connection connection = dataSource.getConnection();
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

    }
}