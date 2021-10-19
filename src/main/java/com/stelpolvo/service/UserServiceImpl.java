package com.stelpolvo.service;

import com.stelpolvo.dao.UserDao;
import com.stelpolvo.pojo.User;

public class UserServiceImpl implements UserService {
    // 自动注入
    private UserDao userDao;

    @Override
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
