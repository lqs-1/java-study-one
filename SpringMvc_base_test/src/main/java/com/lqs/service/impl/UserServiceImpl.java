package com.lqs.service.impl;

import com.lqs.dao.RoleDao;
import com.lqs.dao.UserDao;
import com.lqs.domain.Role;
import com.lqs.domain.User;
import com.lqs.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private RoleDao roleDao;

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> list() {
        List<User> userList = userDao.userList();
        for (User user: userList){
            Long id = user.getId();
            List<Role> roles = roleDao.get_roles(id);
            user.setRoles(roles);
        }
        return userList;
    }

    @Override
    public int save(String username, String password, String email, String phoneNum, int[] roleIds) {
        // 判断是否存在这个用户
        List<User> userHas = userDao.findId(phoneNum);
        int rst = 0;
        if (!userHas.isEmpty()){
            return rst;
        }
        // 不存在就存库
        rst = userDao.saveUser(username, password, email, phoneNum);
        // 判断是否选择身份
        if (roleIds == null && rst != 0){
            return rst;
        }
        List<User> user = userDao.findId(phoneNum);
        // 选择身份保存身份关系
        for (int role_id: roleIds){
            rst += userDao.saveUserRole(user.get(0).getId(), role_id);
        }
        return rst;
    }

    @Override
    public int del(int user_id) {
        int rst = userDao.delUserRole(user_id);
        rst += userDao.delUser(user_id);
        return rst;
    }

    @Override
    public User find(String username, String password) {
        User user = userDao.findUser(username, password);
        return user;
    }
}
