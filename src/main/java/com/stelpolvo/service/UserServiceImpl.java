package com.stelpolvo.service;

import com.stelpolvo.dao.UserDao;
import com.stelpolvo.pojo.User;

public class UserServiceImpl implements UserService {
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    private UserDao userDao;

    @Override
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }
}
