package com.cs.dao.impl;

import com.cs.entity.Goods;
import org.junit.Test;

import java.util.List;

public class IGoodsDaoImplTest {
    IGoodsDaoImpl goodsDao = new IGoodsDaoImpl();

    @Test
    public void addGoodsTest(){
        Integer integer = goodsDao.addGoods(new Goods("name", "pic", 99, "helloword", 6));
        System.out.println(integer);
    }

    @Test
    public void showGoodsTest(){
        List<Goods> list = goodsDao.showGoods();
        System.out.println(list);
    }

    @Test
    public void delGoodsTest(){
        Integer integer = goodsDao.delGoods(6);
        System.out.println(integer);
    }

    @Test
    public void updateShowGoodsTest(){
        Goods goods = goodsDao.updateShowGoods(6);
        System.out.println(goods);
    }

    @Test
    public void updateGoodsTest(){
        Integer integer = goodsDao.updateGoods(new Goods(1, "name", "pic", 99, "helloword", 6));
        System.out.println(integer);
    }

//    @Test
//    public void searchGoodsTest(){
//        List<Goods> list = goodsDao.searchGoods("一");
//        System.out.println(list);
//    }
}
