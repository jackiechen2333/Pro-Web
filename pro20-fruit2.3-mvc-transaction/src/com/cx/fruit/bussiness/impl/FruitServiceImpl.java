package com.cx.fruit.bussiness.impl;

import com.cx.fruit.bussiness.FruitService;
import com.cx.fruit.dao.FruitDAO;
import com.cx.fruit.pojo.Fruit;

import java.util.List;

/**
 * Description:
 *
 * @Author cx
 * @Data 2023/3/2-19:51
 * @Version 2022.2 1.8
 */
public class FruitServiceImpl implements FruitService {

    private FruitDAO fruitDAO = null;

    @Override
    public void updateFruit(Fruit fruit){
        fruitDAO.updateFruit(fruit);
    };

    @Override
    public List<Fruit> getFruitList(String keyword, Integer pageNo) {
        return fruitDAO.getFruitList(keyword,pageNo);
    }

    @Override
    public void addFruit(Fruit fruit) {
        fruitDAO.addFruit(fruit);
    }

    @Override
    public Fruit getFruitByFid(Integer fid) {
        return fruitDAO.getFruitByFid(fid);
    }

    @Override
    public void delFruit(Integer fid) {
        fruitDAO.delFruitByFid(fid);
    }

    @Override
    public Integer getPageCount(String keyword) {
        int fruitCount = fruitDAO.getFruitCount(keyword);
        int pagecount = (fruitCount+5-1)/ 5;

        return pagecount;
    }
}
