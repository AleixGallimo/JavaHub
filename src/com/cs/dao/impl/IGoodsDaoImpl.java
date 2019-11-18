package com.cs.dao.impl;

import com.cs.dao.IGoodsDao;
import com.cs.entity.Goods;
import com.cs.util.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IGoodsDaoImpl implements IGoodsDao {
    //添加商品
    @Override
    public Integer addGoods(Goods goods) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement("insert into goods(g_goods_name, g_goods_pic, g_goods_price, g_goods_description, g_goods_stock) values(?,?,?,?,?)");
            preparedStatement.setString(1,goods.getName());
            preparedStatement.setString(2,goods.getPic());
            preparedStatement.setInt(3,goods.getPrice());
            preparedStatement.setString(4,goods.getDescription());
            preparedStatement.setInt(5,goods.getStock());
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(preparedStatement,connection);
        }
        return count;
    }

    //展示商品
    @Override
    public List<Goods> showGoods() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Goods> list = new ArrayList<>();
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement("select * from goods where is_delete=1");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("g_id");
                String name = resultSet.getString("g_goods_name");
                String pic = resultSet.getString("g_goods_pic");
                int price = resultSet.getInt("g_goods_price");
                int stock = resultSet.getInt("g_goods_stock");
                String description = resultSet.getString("g_goods_description");
                list.add(new Goods(id, name, pic, price, description, stock));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(resultSet, preparedStatement, connection);
        }
        return list;
    }

    //通过id展示对应的商品信息
    @Override
    public Goods updateShowGoods(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Goods goods = null;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement("select * from goods where g_id=? and is_delete=1");
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("g_goods_name");
                String pic = resultSet.getString("g_goods_pic");
                int price = resultSet.getInt("g_goods_price");
                int stock = resultSet.getInt("g_goods_stock");
                String description = resultSet.getString("g_goods_description");
                goods = new Goods(id, name, pic, price, description, stock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(resultSet, preparedStatement, connection);
        }
        return goods;
    }

    //通过id删除商品信息
    @Override
    public Integer delGoods(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement("update goods set is_delete=0 where g_id=?");
            preparedStatement.setInt(1,id);
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(preparedStatement,connection);
        }
        return count;
    }

    //通过名字排序
    @Override
    public List<Goods> sortByName() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Goods> list = new ArrayList<>();
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement("select * from goods where is_delete=1 order by g_goods_name");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("g_id");
                String name = resultSet.getString("g_goods_name");
                String pic = resultSet.getString("g_goods_pic");
                int price = resultSet.getInt("g_goods_price");
                int stock = resultSet.getInt("g_goods_stock");
                String description = resultSet.getString("g_goods_description");
                list.add(new Goods(id, name, pic, price, description, stock));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(resultSet, preparedStatement, connection);
        }
        return list;
    }

    //通过价格范围展示
    @Override
    public List<Goods> priceAtoB(Integer priceA, Integer priceB, Boolean flag) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Goods> list = new ArrayList<>();
        try {
            connection = DBUtils.getConnection();
            if (flag){
                preparedStatement = connection.prepareStatement("select * from goods where is_delete=1 and g_goods_price BETWEEN ? and ? order by g_goods_price desc");
            } else {
                preparedStatement = connection.prepareStatement("select * from goods where is_delete=1 and g_goods_price BETWEEN ? and ? order by g_goods_price asc");
            }
            preparedStatement.setInt(1,priceA);
            preparedStatement.setInt(2,priceB);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("g_id");
                String name = resultSet.getString("g_goods_name");
                String pic = resultSet.getString("g_goods_pic");
                int price = resultSet.getInt("g_goods_price");
                int stock = resultSet.getInt("g_goods_stock");
                String description = resultSet.getString("g_goods_description");
                list.add(new Goods(id, name, pic, price, description, stock));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(resultSet, preparedStatement, connection);
        }
        return list;
    }


    //排序 -- 升序&降序
    @Override
    public List<Goods> sort(String value) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Goods> list = new ArrayList<>();
        try {
            connection = DBUtils.getConnection();
            if ("asc".equals(value)){
                preparedStatement = connection.prepareStatement("select * from goods where is_delete=1 order by g_goods_price asc");
            } else {
                preparedStatement = connection.prepareStatement("select * from goods where is_delete=1 order by g_goods_price desc");
            }
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("g_id");
                String name = resultSet.getString("g_goods_name");
                String pic = resultSet.getString("g_goods_pic");
                int price = resultSet.getInt("g_goods_price");
                int stock = resultSet.getInt("g_goods_stock");
                String description = resultSet.getString("g_goods_description");
                list.add(new Goods(id, name, pic, price, description, stock));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(resultSet, preparedStatement, connection);
        }
        return list;
    }

    //修改商品信息
    @Override
    public Integer updateGoods(Goods goods) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement("update goods set g_goods_name=?,g_goods_pic=?,g_goods_price=?,g_goods_description=?,g_goods_stock=? where g_id=? and is_delete=1;");
            preparedStatement.setString(1,goods.getName());
            preparedStatement.setString(2,goods.getPic());
            preparedStatement.setInt(3,goods.getPrice());
            preparedStatement.setString(4,goods.getDescription());
            preparedStatement.setInt(5,goods.getStock());
            preparedStatement.setInt(6,goods.getId());
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(preparedStatement,connection);
        }
        return count;
    }

    //模糊搜索
    @Override
    public List<Goods> searchGoods(String search) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Goods> list = new ArrayList<>();
        try {
            connection = DBUtils.getConnection();
            statement = connection.prepareStatement("select * from goods where is_delete=1 and g_goods_name like ? ");
            statement.setString(1, "%"+search+"%");
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("g_id");
                String name = resultSet.getString("g_goods_name");
                String pic = resultSet.getString("g_goods_pic");
                int price = resultSet.getInt("g_goods_price");
                int stock = resultSet.getInt("g_goods_stock");
                String description = resultSet.getString("g_goods_description");
                list.add(new Goods(id, name, pic, price, description, stock));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(resultSet, statement, connection);
        }
        return list;
    }
    /*
    //模糊搜索总行数
    @Override
    public Integer searchGoodsCount(String search) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement("select count(*) from goods where is_delete=1 and g_goods_name like ? ");
            preparedStatement.setString(1,"%"+search+"%");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                count = resultSet.getInt("count(*)");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(resultSet, preparedStatement, connection);
        }
        return count;
    }*/

    /*//根据设定的分页数据，获取到数据库该范围的内容
    @Override
    public List<Goods> pageGoods(Integer start, Integer count) {
        List<Goods> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement("select * from goods where is_delete=1 limit ?,? ");
            preparedStatement.setInt(1,start);
            preparedStatement.setInt(2,count);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("g_id");
                String name = resultSet.getString("g_goods_name");
                String pic = resultSet.getString("g_goods_pic");
                int price = resultSet.getInt("g_goods_price");
                int stock = resultSet.getInt("g_goods_stock");
                String description = resultSet.getString("g_goods_description");
                list.add(new Goods(id, name, pic, price, description, stock));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(resultSet, preparedStatement, connection);
        }
        return list;
    }

    //获取数据库总行数
    @Override
    public Integer countGoods() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement("select count(*) from goods where is_delete=1");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                count = resultSet.getInt("count(*)");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(resultSet, preparedStatement, connection);
        }
        return count;
    }*/
}
