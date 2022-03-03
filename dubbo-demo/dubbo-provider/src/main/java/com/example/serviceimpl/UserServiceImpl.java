package com.example.serviceimpl;

import com.example.dao.UserDao;
import com.example.domain.User;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserServiceImpl implements UserService{


    @Autowired
    private UserDao userDao;


    @Override
    public User findAll() {
        return userDao.find();
    }
}
