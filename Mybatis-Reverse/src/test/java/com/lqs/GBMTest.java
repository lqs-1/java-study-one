package com.lqs;

import com.lqs.mybatis.mapper.UserMapper;
import com.lqs.mybatis.pojo.User;
import com.lqs.mybatis.pojo.UserExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class GBMTest {


    @Test
    public void test1(){
        try {
            InputStream resourceAsReader = Resources.getResourceAsStream("Mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsReader);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);

            // 查询
//            UserExample userExample = new UserExample();
//            userExample.createCriteria().andNameEqualTo("fefewr");
//            userExample.or().andIdEqualTo(1);
//            List<User> userList = mapper.selectByExample(userExample);
//            userList.forEach(user -> System.out.println(user));

            // 添加
//            mapper.insert(new User(1, "haha", null));
            mapper.insert(new User(1));
            List<User> userList = mapper.selectByExample(null);
            userList.forEach(user -> System.out.println(user));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
