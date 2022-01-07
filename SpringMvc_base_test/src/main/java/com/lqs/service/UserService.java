package com.lqs.service;

import com.lqs.domain.User;

import java.util.List;

public interface UserService {
    public List<User> list();

    public int save(String username, String password, String email, String phoneNum, int[] roleIds);

    public int del(int user_id);
}
