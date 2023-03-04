package com.cx.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Description:
 *
 * @Author cx
 * @Data 2023/3/1-20:21
 * @Version 2022.2 1.8
 */
public class Demo07 {
    public static void main(String[] args) throws Exception{
        Class clazz = Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/fruitdb?useSSL=false&useUnicode=true&characterEncoding=utf-8";
        String user = "root";
        String pwd = "123456";
        Connection connection = DriverManager.getConnection(url, user, pwd);

        String sql = "select count(*) from t_fruit";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            int count = resultSet.getInt(1);
            System.out.println(count);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

    }
}
