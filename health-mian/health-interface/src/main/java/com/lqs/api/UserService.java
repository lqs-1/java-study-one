package com.lqs.api;

import com.lqs.pojo.User;

public interface UserService {
    User findByUserName(String username);
}
