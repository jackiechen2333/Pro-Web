package com.cx.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @Author cx
 * @Data 2023/3/1-19:45
 * @Version 2022.2 1.8
 */
public class Demo05 {
    public static void main(String[] args) throws Exception{
        Class clazz = Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/fruitdb?useSSL=false&useUnicode=true&characterEncoding=utf-8";
        String user = "root";
        String pwd = "123456";
        Connection connection = DriverManager.getConnection(url, user, pwd);

        String sql = "select * from t_fruit";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();
        List<Fruit> fruitList = new ArrayList<>();
        while (resultSet.next()){
            int fid = resultSet.getInt(1);
            String fname = resultSet.getString("fname");
            int price = resultSet.getInt(3);
            int fcount = resultSet.getInt(4);
            String remark = resultSet.getString(5);

            Fruit fruit = new Fruit(fid, fname, price, fcount, remark);
            fruitList.add(fruit);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

        fruitList.forEach(System.out::println);
    }
}
