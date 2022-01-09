package com.lqs.dao;

import com.lqs.domain.User;

import java.util.List;

public interface UserDao {
    public List<User> userList();

    public int saveUser(String username, String password, String email, String phoneNum);

    public List<User> findId(String phoneNum);

    public int saveUserRole(Long user_id, int role_id);

    public int delUser(int id);

    public int delUserRole(int user_id);

    public User findUser(String username, String password);
}
