package com.cx.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.sql.SQLException;

/**
 * Description:
 *
 * @Author cx
 * @Data 2023/3/1-23:14
 * @Version 2022.2 1.8
 */
public class Demo03Druid {
    public static void main(String[] args) throws SQLException {

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/fruitdb?rewriteBatchedStatements=true&useSSL=false&useUnicode=true&characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        dataSource.setInitialSize(2);
        dataSource.setMaxActive(5);
        dataSource.setMaxWait(5000);

        DruidPooledConnection connection = null;
        for (int i = 0; i < 10; i++) {
            connection = dataSource.getConnection();
            System.out.println(connection);
        }

        try {
            if(connection != null)
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
