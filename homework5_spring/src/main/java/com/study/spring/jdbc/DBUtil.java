package com.study.spring.jdbc;

import java.sql.*;

/**
 * @author hong.zheng
 * @Description
 * @date 2017-12-22-19:11
 **/
public class DBUtil {

    // 创建数据库的基本信息
    // 创建url
    private static final String url = "jdbc:mysql://localhost:3306/mybatis?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Shanghai";
    // 数据库的用户名和密码
    private static final String user = "root";
    private static final String password = "123456";
    private static Connection conn = null;
    /**
     * 一：注册的驱动程序 获取连接对象的方法 静态代码块（好处是只需要加载一次，且随着类的加载而加载）
     */
    static {
        try {
            /**
             *com.mysql.jdbc.Driver 代码
             static {
                try {
                        java.sql.DriverManager.registerDriver(new Driver());
                    }
                    catch (SQLException E) {
                    throw new RuntimeException("Can't register driver!");
             }
             }
             */
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("获取数据库连接对象出错");
        }
    }

    /**
     * 二：获取连接对象 该方法返回一个连接
     */

    public static Connection getConnection() {

        // 创建连接对象
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return conn;

    }


    public static void close(Connection conn, Statement stsm) {
        close(stsm);
        close(conn);
    }
    public static void close(Connection conn,Statement stmt,ResultSet rs){
        close(rs);
        close(stmt);
        close(conn);
    }
    public static void close(Connection conn, CallableStatement cs) {
        close(cs);
        close(conn);
    }

    public static void close(Connection conn)
    {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
    public static void close(Statement statement)
    {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
    public static void close(CallableStatement cs)
    {
        if (cs != null) {
            try {
                cs.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
    public static void close(ResultSet resultSet)
    {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}
