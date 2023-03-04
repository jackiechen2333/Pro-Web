package com.cx.fruit.dao;

import com.cx.jdbc.Fruit;

import java.util.List;

/**
 * Description:
 *
 * @Author cx
 * @Data 2023/3/1-20:37
 * @Version 2022.2 1.8
 */
public interface FruitDAO {
    //查询库存列表
    List<Fruit> getFruitList();

    //新增库存
    boolean addFruit(Fruit fruit);

    //修改库存
    boolean updateFruit(Fruit fruit);

    //根据名称查询特定库存
    Fruit getFruitByFname(String fname);

    //删除特定库存记录
    boolean delFruit(String fname);
}
