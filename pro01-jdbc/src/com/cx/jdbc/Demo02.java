package com.cx.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Description:
 *
 * @Author cx
 * @Data 2023/3/1-19:33
 * @Version 2022.2 1.8
 */
public class Demo02 {
    public static void main(String[] args) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
//        Driver driver = (Driver) clazz.newInstance();
//        DriverManager.registerDriver(driver);

        String url = "jdbc:mysql://localhost:3306/fruitdb?useSSL=false&useUnicode=true&characterEncoding=utf-8";
        String user = "root";
        String pwd = "123456";
        Connection connection = DriverManager.getConnection(url, user, pwd);

        String sql = "insert into t_fruit values(0,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,"榴莲");
        preparedStatement.setInt(2,15);
        preparedStatement.setInt(3,100);
        preparedStatement.setString(4,"榴莲是一种神奇的水果");

        int count = preparedStatement.executeUpdate();
        System.out.println(count > 0?"添加成功！":"添加失败！");

        preparedStatement.close();
        connection.close();
    }
}
