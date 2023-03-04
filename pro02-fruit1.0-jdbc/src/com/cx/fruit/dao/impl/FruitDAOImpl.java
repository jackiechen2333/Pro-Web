package com.cx.fruit.dao.impl;

import com.cx.fruit.dao.FruitDAO;
import com.cx.jdbc.Fruit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @Author cx
 * @Data 2023/3/1-20:38
 * @Version 2022.2 1.8
 */
public class FruitDAOImpl implements FruitDAO {
    Connection connection = null;
    PreparedStatement prepareStatement = null;
    ResultSet resultSet = null;

    final String DRIVER = "com.mysql.jdbc.Driver";
    final String URL = "jdbc:mysql://localhost:3306/fruitdb?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    final String USER = "root";
    final String PWD = "123456";

    @Override
    public List<Fruit> getFruitList() {
        List<Fruit> fruitList = new ArrayList<>();

        try {
            //1.加载驱动
            Class.forName(DRIVER);
            //2.通过驱动管理器获取连接对象
            connection = DriverManager.getConnection(URL, USER, PWD);
            //3.编写SQL语句
            String sql = "select * from t_fruit";
            //4.创建预处理命令对象
            prepareStatement = connection.prepareStatement(sql);
            //5.执行查询
            resultSet = prepareStatement.executeQuery();

            //6.解析rs
            while (resultSet.next()) {
                int fid = resultSet.getInt(1);
                String fname = resultSet.getString(2);
                int price = resultSet.getInt(3);
                int fcount = resultSet.getInt(4);
                String remark = resultSet.getString(5);

                Fruit fruit = new Fruit(fid, fname, price, fcount, remark);
                fruitList.add(fruit);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
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
        return fruitList;
    }

    @Override
    public boolean addFruit(Fruit fruit) {

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PWD);
            String sql = "insert into t_fruit values(0,?,?,?,?)";
            prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setString(1, fruit.getFname());
            prepareStatement.setInt(2, fruit.getPrice());
            prepareStatement.setInt(3, fruit.getFcount());
            prepareStatement.setString(4, fruit.getRemark());

            return prepareStatement.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
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

        return false;
    }

    @Override
    public boolean updateFruit(Fruit fruit) {

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PWD);
            String sql = "update t_fruit set fcount = ? where fid = ? ";
            prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setInt(1, fruit.getFcount());
            prepareStatement.setInt(2, fruit.getFid());
            return prepareStatement.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
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


        return false;
    }

    @Override
    public Fruit getFruitByFname(String fname) {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PWD);
            String sql = "select * from t_fruit where fname like ? ";
            prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setString(1, fname);
            resultSet  = prepareStatement.executeQuery();
            if (resultSet.next()) {
                int fid = resultSet.getInt(1);
                int price = resultSet.getInt(3);
                int fcount = resultSet.getInt(4);
                String remark = resultSet.getString(5);

                return new Fruit(fid, fname, price, fcount, remark);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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

        return null;
    }

    @Override
    public boolean delFruit(String fname) {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PWD);
            String sql = "delete from t_fruit where fname like ? ";
            prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setString(1, fname);
            return prepareStatement.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
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
        return false;
    }
}
