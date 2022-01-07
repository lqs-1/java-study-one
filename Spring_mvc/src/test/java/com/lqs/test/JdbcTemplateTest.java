package com.lqs.test;

import com.lqs.domain.User;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.beans.PropertyVetoException;
import java.util.List;

public class JdbcTemplateTest {



    // 测试jdbc模板
    @Test
    public void test1() throws PropertyVetoException {
        // 传统方式
//        ComboPooledDataSource dataSource = new ComboPooledDataSource();
//        dataSource.setDriverClass("com.mysql.jdbc.Driver");
//        dataSource.setPassword("lqs");
//        dataSource.setUser("lqs");
//        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
//        JdbcTemplate jdbcTemplateTest = new JdbcTemplate();
//        jdbcTemplateTest.setDataSource(dataSource);
//        int rst = jdbcTemplateTest.update("delete from user");
//        System.out.println(rst);


        // spring的javabean模式
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContextTest.xml");
        JdbcTemplate jdbcTemplate = app.getBean(JdbcTemplate.class);
//        int rst = jdbcTemplate.update("insert into user values(?,?)", 3, "lqs");
//        System.out.println(rst);

        // 更新，修改，删除都用update


        // 查询
        // 查询全部
//        List<User> userlist = jdbcTemplate.query("select * from user", new BeanPropertyRowMapper<User>(User.class));
//        System.out.println(userlist);


        // 条件查询
//        List<User> userlist = jdbcTemplate.query("select * from user where id=?", new BeanPropertyRowMapper<User>(User.class), 3);
//        System.out.println(userlist);

        // 查询一个,只能查
//        User user = jdbcTemplate.queryForObject("select * from user where id=?", new BeanPropertyRowMapper<User>(User.class), 3);
//        System.out.println(user);

        // 聚合查询
        Integer integer = jdbcTemplate.queryForObject("select count(*) as sum from user where id=?", Integer.class, 3);
        System.out.println(integer);
    }

}
