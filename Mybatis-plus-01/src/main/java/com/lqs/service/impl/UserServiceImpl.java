package com.lqs.service.impl;

import com.lqs.domain.User;
import com.lqs.mapper.UserMapper;
import com.lqs.service.UserService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> findAll() {
        List<User> userList = userMapper.selectList(null);
        return userList;
    }

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        List<User> all = userService.findAll();
        for (User user : all) {
            System.out.println(user);
        }
    }
}
