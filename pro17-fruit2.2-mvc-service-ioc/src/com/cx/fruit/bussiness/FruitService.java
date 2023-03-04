package com.cx.fruit.bussiness;

import com.cx.fruit.pojo.Fruit;

import java.util.List;

/**
 * Description:
 *
 * @Author cx
 * @Data 2023/3/2-19:49
 * @Version 2022.2 1.8
 */
public interface FruitService {

    void updateFruit(Fruit fruit);

    List<Fruit> getFruitList(String keyword,Integer pageNo);

    void addFruit(Fruit fruit);

    Fruit getFruitByFid(Integer fid);

    void delFruit(Integer fid);

    Integer getPageCount(String keyword);
}
