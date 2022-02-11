package com.lqs.servicce.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqs.domain.User;
import com.lqs.mapper.UserMapper;
import com.lqs.servicce.UserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean insert(User user) {
        return userMapper.insert(user) > 0;
    }

    @Override
    public Boolean update(User user) {
        return userMapper.updateById(user) > 0;
    }

    @Override
    public Boolean delete(int id) {
        return userMapper.deleteById(id) > 0;
    }

    @Override
    public User getById(int id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<User> getAll() {
        return userMapper.selectList(null);
    }

    @Override
    public IPage<User> getPaginator(int currentPage, int page, User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<User>();
        lambdaQueryWrapper.like(user.getId() >= 0, User::getId, user.getId());
        lambdaQueryWrapper.like(Strings.isNotEmpty(user.getName()), User::getName, user.getName());
        lambdaQueryWrapper.like(user.getMoney() >= 0, User::getMoney, user.getMoney());
        IPage page1 = new Page(currentPage, page);
        return userMapper.selectPage(page1, lambdaQueryWrapper);
    }
}
