package com.lqs.dao;

import com.lqs.domain.User;

import java.util.List;

public interface UserDao {
    public List<User> findAll();
    public List<User> findAll2();
    public User findMany(User user);
    public List<User> findById(List<Integer> ids);
    public void insertOne(User user);

}
