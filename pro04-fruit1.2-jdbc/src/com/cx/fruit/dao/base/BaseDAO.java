package com.cx.fruit.dao.base;

import java.sql.*;

/**
 * Description:
 *
 * @Author cx
 * @Data 2023/3/1-21:08
 * @Version 2022.2 1.8
 */
public abstract class BaseDAO {

    protected Connection connection = null;
    protected PreparedStatement prepareStatement = null;
    protected ResultSet resultSet = null;

    protected final String DRIVER = "com.mysql.jdbc.Driver";
    protected final String URL = "jdbc:mysql://localhost:3306/fruitdb?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    protected final String USER = "root";
    protected final String PWD = "123456";

    protected int executeUpdate(String sql, Object... params) {
        try {
            //1.加载驱动
            //2.通过驱动管理器获取连接对象
            connection = getConnection();
            prepareStatement = connection.prepareStatement(sql);
            if (params != null && params.length > 0){
                for (int i = 0; i < params.length; i++) {
                    prepareStatement.setObject(i + 1,params[i]);
                }
            }

            return prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection,prepareStatement,null);
        }
        return 0;
    }

    protected Connection getConnection() {
        try {
            //1.加载驱动
            Class.forName(DRIVER);
            //2.通过驱动管理器获取连接对象
            return DriverManager.getConnection(URL, USER, PWD);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void close(Connection connection, PreparedStatement prepareStatement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (prepareStatement != null) {
                prepareStatement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
