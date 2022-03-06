package com.lqs.service.impl;

import com.lqs.domain.User;
import com.lqs.mapper.UserMapper;
import com.lqs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByName(User user) {
        return userMapper.findByName(user);
    }

}
