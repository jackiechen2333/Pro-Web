package com.cx.jdbc;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Description:
 *
 * @Author cx
 * @Data 2023/3/1-23:50
 * @Version 2022.2 1.8
 */
public class Demo05Druid {
    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        InputStream resourceAsStream = Demo04Druid.class.getClassLoader().getResourceAsStream("jdbc2.properties");
        properties.load(resourceAsStream);

        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = null;
        for (int i = 0; i < 10; i++) {
            connection = dataSource.getConnection();
            System.out.println(connection);
        }

        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
