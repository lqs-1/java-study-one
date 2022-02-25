package com.lqs.user.userservice.service;

import com.lqs.user.userservice.mapper.UserMapper;
import com.lqs.user.userservice.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User queryById(Long id) {
        return userMapper.findById(id);
    }
}