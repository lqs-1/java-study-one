package com.lqs.dao.impl;

import com.lqs.dao.UserDao;
import com.lqs.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> userList() {
        List<User> userList = jdbcTemplate.query("select * from sys_user", new BeanPropertyRowMapper<User>(User.class));
        return userList;
    }

    @Override
    public int saveUser(String username, String password, String email, String phoneNum) {
        int rst = jdbcTemplate.update("insert into sys_user values (null,?,?,?,?)", username, password, email, phoneNum);
        return rst;
    }

    @Override
    public List<User> findId(String phoneNum) {
        List<User> user = jdbcTemplate.query("select id from sys_user where phoneNum=?", new BeanPropertyRowMapper<User>(User.class), phoneNum);
        return user;
    }

    @Override
    public int saveUserRole(Long user_id, int role_id) {
        int rst = jdbcTemplate.update("insert into sys_user_role values (?, ?)", user_id, role_id);
        return rst;
    }

    @Override
    public int delUser(int id) {
        int rst = jdbcTemplate.update("delete from sys_user where id=?", id);
        return rst;
    }

    @Override
    public int delUserRole(int user_id) {
        int rst = jdbcTemplate.update("delete from sys_user_role where userId=?", user_id);
        return 0;
    }
}
