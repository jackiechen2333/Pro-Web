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
    public List<Fruit> getFruitList() {
        return super.executeQuery("select * from t_fruit");
    }

    @Override
    public boolean addFruit(Fruit fruit) {
        return false;
    }

    @Override
    public boolean updateFruit(Fruit fruit) {
        return false;
    }

    @Override
    public Fruit getFruitByFname(String fname) {
        return null;
    }

    @Override
    public boolean delFruit(String fname) {
        return false;
    }
}
