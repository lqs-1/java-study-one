package com.lqs.dao.impl;

import com.lqs.dao.UserDao;
import com.lqs.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

        // 在添加中间表的数据的时候，需要user_id,也就是user表的主键，以下方法可以直接返回生成并且返回主键
        // 创建PreparedStatementCreator， 用于执行sql
//        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
//            @Override
//            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
//                PreparedStatement preparedStatement = connection.prepareStatement("insert into sys_user values (?,?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
//                preparedStatement.setObject(1, null);
//                preparedStatement.setString(2, user.getUserName());
//                preparedStatement.setString(3, user.getPasswrod);
//                preparedStatement.setString(4, user.getEmail);
//                preparedStatement.setString(5, user.getPhoneNum);
//                return preparedStatement;
//            }
//        };

        // 创建KeyHolder, 生成主键, 获取主键
//        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
        // 获取生成的主键
//        int user_id = generatedKeyHolder.getKey().intValue();
//        return user_id;

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

    @Override
    public User findUser(String username, String password) {
        User query = jdbcTemplate.queryForObject("select * from sys_user where username=? and password=?", new BeanPropertyRowMapper<>(User.class), username, password);
        return query;
    }
}
