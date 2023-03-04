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
public class Demo01Batch {
    public static void main(String[] args) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
//        Driver driver = (Driver) clazz.newInstance();
//        DriverManager.registerDriver(driver);

        String url = "jdbc:mysql://localhost:3306/fruitdb?rewriteBatchedStatements=true&useSSL=false&useUnicode=true&characterEncoding=utf-8";
        String user = "root";
        String pwd = "123456";
        Connection connection = DriverManager.getConnection(url, user, pwd);

        String sql = "insert into t_fruit values(0,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < 10; i++) {
            preparedStatement.setString(1,"榴莲");
            preparedStatement.setInt(2,15);
            preparedStatement.setInt(3,100);
            preparedStatement.setString(4,"榴莲是一种神奇的水果");

            preparedStatement.addBatch();

            if(i % 1000==0) {//如果任务较多，可以分批次执行，每次执行完，清空任务队列
                preparedStatement.executeBatch ();
                preparedStatement.clearBatch ();
            }
        }


        int[] ints = preparedStatement.executeBatch();
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }

        preparedStatement.close();
        connection.close();
    }
}
