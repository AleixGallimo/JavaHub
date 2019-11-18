package com.cs.dao;

import com.cs.entity.Goods;

import java.util.List;

public interface IGoodsDao {
    //添加商品
    Integer addGoods(Goods goods);

    //展示商品
    List<Goods> showGoods();

    //通过id展示商品
    Goods updateShowGoods(Integer id);

    //通过id删除商品信息
    Integer delGoods(Integer id);

    //通过名字排序
    List<Goods> sortByName();
    //通过价格范围展示
    List<Goods> priceAtoB(Integer priceA, Integer priceB, Boolean flag);

    //排序
    List<Goods> sort(String value);

    //修改商品信息
    Integer updateGoods(Goods goods);

    //模糊搜索
    List<Goods> searchGoods(String search);

/*    //分页展示商品
    List<Goods> pageGoods(Integer start, Integer count);

    //获取展示商品总行数
    Integer countGoods();*/
}
