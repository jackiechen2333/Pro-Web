package com.cx.fruit.dao.impl;

import com.cx.fruit.dao.FruitDAO;
import com.cx.fruit.dao.base.BaseDAO;
import com.cx.fruit.pojo.Fruit;

import java.util.List;

/**
 * Description:
 *
 * @Author cx
 * @Data 2023/2/23-12:17
 * @Version 2022.2 1.8
 */
public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {
    @Override
    public List<Fruit> getFruitList(Integer pageNo) {
        return super.executeQuery("select * from t_fruit limit ? , 5",(pageNo - 1) * 5);
    }

    @Override
    public Fruit getFruitByFid(Integer fid) {
        return super.load("select * from t_fruit where fid = ?",fid);
    }


    @Override
    public void updateFruit(Fruit fruit) {
        super.executeUpdate("update t_fruit set fname = ?,price = ?, fcount = ?,remark = ? where fid = ?",fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark(),fruit.getFid());
    }

    @Override
    public void addFruit(Fruit fruit) {
        String sql = "insert into t_fruit values(,?,?,?,?)";
        super.executeUpdate(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark());
    }

    @Override
    public Fruit getFruitByFname(java.lang.String fname) {
        return null;
    }

    @Override
    public void delFruitByFid(Integer fid) {
        super.executeUpdate("delete from t_fruit where fid = ?",fid);
    }

}