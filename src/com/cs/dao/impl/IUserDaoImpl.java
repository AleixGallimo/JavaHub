package com.cs.dao.impl;

import com.cs.dao.IUserDao;
import com.cs.entity.User;
import com.cs.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IUserDaoImpl implements IUserDao {
    @Override
    public Integer addUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            System.out.println(user);
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement("insert into user(username,password,sex,hobbies,phone,email,address) values(?,?,?,?,?,?,?)");
            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getSex());
            preparedStatement.setString(4,user.getHobbies());
            preparedStatement.setString(5,user.getPhone());
            preparedStatement.setString(6,user.getEmail());
            preparedStatement.setString(7,user.getAddress());
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(preparedStatement,connection);
        }
        return count;
    }

    @Override
    public Integer checkUsername(String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement("select count(*) from user where username=? and is_delete=1");
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                count = resultSet.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(resultSet,preparedStatement,connection);
        }
        return count;
    }

    @Override
    public Integer checkUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int uId = 0;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement("select id from user where username=? and password=? and is_delete=1");
            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2,user.getPassword());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                uId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(resultSet,preparedStatement,connection);
        }
        return uId;
    }
}
