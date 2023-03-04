package com.cx.fruit.dao.impl;

import com.cx.fruit.dao.FruitDAO;
import com.cx.fruit.dao.base.BaseDAO;
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
public class FruitDAOImpl extends BaseDAO implements FruitDAO {


    @Override
    public List<Fruit> getFruitList() {
        List<Fruit> fruitList = new ArrayList<>();

        try {
            //1.加载驱动
            //2.通过驱动管理器获取连接对象
            connection = getConnection();
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

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection,prepareStatement,resultSet);
        }
        return fruitList;
    }

    @Override
    public Fruit getFruitByFname(String fname) {
        try {
            //1.加载驱动
            //2.通过驱动管理器获取连接对象
            connection = getConnection();
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection,prepareStatement,resultSet);
        }

        return null;
    }

    @Override
    public boolean addFruit(Fruit fruit) {
        String sql = "insert into t_fruit values(0,?,?,?,?)";
        return super.executeUpdate(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark()) > 0;
    }

    @Override
    public boolean updateFruit(Fruit fruit) {
        String sql = "update t_fruit set fcount = ? where fid = ? ";
        return super.executeUpdate(sql,fruit.getFcount(),fruit.getFid()) > 0;

    }

    @Override
    public boolean delFruit(String fname) {
        String sql = "delete from t_fruit where fname like ? ";
        return super.executeUpdate(sql,fname) > 0;
    }
}