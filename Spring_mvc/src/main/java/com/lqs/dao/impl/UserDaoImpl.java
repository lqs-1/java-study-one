package com.lqs.dao.impl;

import com.lqs.dao.UserDao;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Override
    public void save() {
        System.out.println("我日你妈，你能不能运行了");
    }
}
