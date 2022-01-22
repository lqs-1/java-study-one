package com.lqs.service.impl;

import com.lqs.domain.User;
import com.lqs.mapper.UserMapper;
import com.lqs.service.UserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper mapper;
    @Override
    public void save(User user) throws IOException {
        mapper.save(user);
    }

    @Override
    public List<User> findAll() throws IOException {
        List<User> userList = mapper.findAll();
        return userList;
    }
}
