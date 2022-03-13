package com.lqs.mapper;

import com.lqs.pojo.User;

public interface UserMapper {
    User findByUserName(String username);
}
