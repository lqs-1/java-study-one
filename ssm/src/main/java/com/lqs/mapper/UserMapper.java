package com.lqs.mapper;

import com.lqs.domain.User;

import java.util.List;

public interface UserMapper {
    public void save(User user);
    public List<User> findAll();
}
