package com.lqs.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lqs.dao.RoleDao;
import com.lqs.dao.UserDao;
import com.lqs.domain.Role;
import com.lqs.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceDemo {
    public static void main(String[] args) throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("Mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sessionFactory.openSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);


        List<User> userList = mapper.findAll();
        for (User user : userList) {
            System.out.println(user);
        }


        // 固化sql
//        List<User> userList = mapper.findAll();

//        使用类型转换器
//        User user = new User();
//        user.setUsername("type");
//        user.setId(23);
//        user.setBd(new Date());
//
//        mapper.insertOne(user);
//        sqlSession.commit();


        // 动态sql
//        User user = new User();
//        user.setId(23);
//        user.setUsername("type");
//        User userList = (User) mapper.findMany(user);

//        List<Integer> ids = new ArrayList<>();
//        ids.add(1);
//        ids.add(3);
//        List<User> userList = mapper.findById(ids);
//        System.out.println(userList.getBd());


//        分页插件
        // 设置分页参数，当前页，每页多少条
//        PageHelper.startPage(2, 2);
//        List<User> userList = mapper.findAll();
//        for (User user : userList) {
//            System.out.println(user);
//        }
        // 获取分页信息
        // 当前页
//        PageInfo<User> pageInfo = new PageInfo<>(userList);
//        System.out.println("当前页"+pageInfo.getPageNum());
//        // 每页显示多少条
//        System.out.println("每页显示"+pageInfo.getPageSize());
//        // 总条数
//        System.out.println("总条数"+pageInfo.getTotal());
//        // 总页数
//        System.out.println("总页数"+pageInfo.getPages());
//        // 上一页
//        System.out.println("上一页"+pageInfo.getPrePage());
//        // 下一页
//        System.out.println("下一页"+pageInfo.getNextPage());
//        // 是否是第一页
//        System.out.println("是否是第一页"+pageInfo.isIsFirstPage());
//        // 是否是最后一页
//        System.out.println("是否是最后一页"+pageInfo.isIsLastPage());
//        // 是否有下一页
//        System.out.println("是否有下一页"+pageInfo.isHasNextPage());
//        // 是否有上一页
//        System.out.println("是否有上一页"+pageInfo.isHasNextPage());


        // 多表查询
//        List<User> userList = mapper.findAll2();
//        for (User user : userList) {
//            System.out.println(user);
//        }
//        RoleDao mapper1 = sqlSession.getMapper(RoleDao.class);
//        List<Role> roleList = mapper1.findAll();
//        for (Role role : roleList) {
//            System.out.println(role);
//        }

//        Role role = mapper1.findOne(1);
//        System.out.println(role);

    }

}
