package com.lqs.domain;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    public static void main(String[] args) throws IOException {

        User user = new User();
        user.setUsername("woca");
        user.setId(23);
        // 加载配置文件
        InputStream resourceAsStrean = Resources.getResourceAsStream("Mybatis-config.xml");
        // 获取sqlSession工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStrean);
        // 获取sqlSession对象, 事务不会自动提交，需要手动commit
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 获取sqlSession对象, 事务自动提交
//        SqlSession sqlSession = sqlSessionFactory.openSession(true);


        // 执行sql
//        List<User> userlist = sqlSession.selectList("userMapper.findAll");

         User userlist = (User) sqlSession.selectOne("userMapper.filterMany", 3);

//        sqlSession.insert("userMapper.insertOne", user);
//        sqlSession.commit();

//        sqlSession.update("userMapper.updateOne", user);
//        sqlSession.commit();

//        sqlSession.delete("userMapper.deleteOne", 2);
//        sqlSession.commit();

        System.out.println(userlist);
        // 释放资源
        sqlSession.clearCache();


    }
}
