package com.cs.dao;

import com.cs.entity.User;

public interface IUserDao {
    //注册 -- 添加用户
    Integer addUser(User user);

    //注册 -- 检查用户是否存在
    Integer checkUsername(String username);

    //登录校验，获取u_id
    Integer checkUser(User user);

}
