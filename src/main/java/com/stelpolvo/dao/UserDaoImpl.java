package com.stelpolvo.dao;

import com.stelpolvo.pojo.User;

public class UserDaoImpl implements UserDao {
    @Override
    public User getUserById(Integer id) {
        return new User(id, "张三");
    }
}
