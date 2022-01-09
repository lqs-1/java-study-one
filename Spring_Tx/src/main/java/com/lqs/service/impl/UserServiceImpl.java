package com.lqs.service.impl;

import com.lqs.dao.UserDao;
import com.lqs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

//    public void setUserDao(UserDao userDao) {
//        this.userDao = userDao;
//    }

    @Override
    // 注解方式使用事务
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = -1, readOnly = false)
    public void update(String username1, String username2, int price) {
        userDao.upadd(username2, price);
        int i = 3/0;
        userDao.upaex(username1, price);
    }
}
