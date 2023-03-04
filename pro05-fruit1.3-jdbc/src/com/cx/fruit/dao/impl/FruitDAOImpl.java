package com.cx.fruit.dao.impl;

import com.cx.fruit.dao.FruitDAO;
import com.cx.fruit.dao.base.BaseDAO;
import com.cx.jdbc.Fruit;

import java.util.List;

/**
 * Description:
 *
 * @Author cx
 * @Data 2023/3/1-20:38
 * @Version 2022.2 1.8
 */
public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {


    @Override
    public List<Fruit> getFruitList() {
        String sql = "select * from t_fruit";
        return super.executeQuery(sql);
    }

    @Override
    public Fruit getFruitByFname(String fname) {
        String sql = "select * from t_fruit where fname like ? ";
        return super.load(sql, fname);
    }

    @Override
    public boolean addFruit(Fruit fruit) {
        String sql = "insert into t_fruit values(0,?,?,?,?)";
        int count = super.executeUpdate(sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark());
        return count > 0;
    }

    @Override
    public boolean updateFruit(Fruit fruit) {
        String sql = "update t_fruit set fcount = ? where fid = ? ";
        return super.executeUpdate(sql, fruit.getFcount(), fruit.getFid()) > 0;

    }

    @Override
    public boolean delFruit(String fname) {
        String sql = "delete from t_fruit where fname like ? ";
        return super.executeUpdate(sql, fname) > 0;
    }
}