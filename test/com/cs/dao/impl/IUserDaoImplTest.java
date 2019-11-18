package com.cs.dao.impl;

import com.cs.entity.User;
import org.junit.Test;

public class IUserDaoImplTest {
    IUserDaoImpl userDao = new IUserDaoImpl();

    public static String username = "admin";
    public static String password = "admin";
    public static String sex = "man";
    public static String hobbies = "skate,game";
    public static String phone = "13229790442";
    public static String email = "a969803965@qq.com";
    public static String address = "ch,gd,gz";

    @Test
    public void addUserTest(){
        Integer integer = userDao.addUser(new User("admin","admin","man","skate,game","13229790442","a969803965@qq.com","ch,gd,gz"));
        System.out.println(integer);
    }

    @Test
    public void checkUserTest(){
        Integer integer = userDao.checkUser(new User(username, password));
        System.out.println(integer);
    }

    @Test
    public void checkUsernameTest(){
        Integer integer = userDao.checkUsername("admin");
        System.out.println(integer);
    }
}

