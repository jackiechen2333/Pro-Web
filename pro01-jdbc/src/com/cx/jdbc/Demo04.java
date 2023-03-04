package com.cx.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Description:
 *
 * @Author cx
 * @Data 2023/3/1-19:43
 * @Version 2022.2 1.8
 */
public class Demo04 {
    public static void main(String[] args) throws Exception{
        Fruit fruit = new Fruit(33,"猕猴桃","猕猴桃是水果之王");

        Class clazz = Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/fruitdb?useSSL=false&useUnicode=true&characterEncoding=utf-8";
        String user = "root";
        String pwd = "123456";
        Connection connection = DriverManager.getConnection(url, user, pwd);

        String sql = "delete from t_fruit where fid = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,33);

        int count = preparedStatement.executeUpdate();

        System.out.println(count > 0?"删除成功！":"删除失败！");

        preparedStatement.close();
        connection.close();
    }
}
