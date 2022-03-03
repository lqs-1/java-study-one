package com.example.dao;

import com.example.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserDao {
    public User find(){
        User user = new User();
        user.setId(2);
        user.setMoney(2);
        user.setName("李奇凇");
        return user;
    }
}
