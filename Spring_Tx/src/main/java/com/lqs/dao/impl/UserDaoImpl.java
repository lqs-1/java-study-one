package com.lqs.dao.impl;

import com.lqs.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    @Override
    public void upadd(String username, int price) {
        jdbcTemplate.update("update user set price=price+? where username=?", price, username);
    }

    @Override
    public void upaex(String username, int price) {
        jdbcTemplate.update("update user set price=price-? where username=?", price, username);
    }
}
