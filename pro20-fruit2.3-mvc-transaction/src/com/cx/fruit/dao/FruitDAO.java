package com.cx.fruit.dao;

import com.cx.fruit.pojo.Fruit;

import java.util.List;

public interface FruitDAO {
    //查询库存列表
    List<Fruit> getFruitList(String keyWord ,Integer pageNo);

    Fruit getFruitByFid(Integer fid);

    void updateFruit(Fruit fruit);

    //新增库存
    void addFruit(Fruit fruit);


    //根据名称查询特定库存
    Fruit getFruitByFname(String fname);

    //删除特定库存记录
    void delFruitByFid(Integer fid);

    int getFruitCount(String keyWord);
}
