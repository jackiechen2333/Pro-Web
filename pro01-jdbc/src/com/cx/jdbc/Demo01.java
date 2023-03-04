package com.cx.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Description:
 *
 * @Author cx
 * @Data 2023/3/1-19:16
 * @Version 2022.2 1.8
 */
public class Demo01 {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
//        Driver driver = (Driver) clazz.newInstance();
//        DriverManager.registerDriver(driver);

        String url = "jdbc:mysql://localhost:3306/fruitdb?useSSL=false&useUnicode=true&characterEncoding=utf-8";
        String user = "root";
        String pwd = "123456";
        Connection connection = DriverManager.getConnection(url, user, pwd);

        System.out.println(connection);
    }
}
