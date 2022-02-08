package test;

import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.lqs.domain.User;
import com.lqs.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")

public class TestMybatisPlus {
    @Autowired
    UserMapper mapper;
    @Test
    public void testFindAll() throws Exception {
//        InputStream resourceAsStream = Resources.getResourceAsStream("Mybatis-config.xml");
//
//        // mybatis整合mybatis-plus，只需要将SqlSessionFactorBuilder改成MybatisSqlSessionFactoryBuilder就可以了，plus的方便就在于提供了很多方法
//        SqlSessionFactory sqlSessionFactory = new MybatisSqlSessionFactoryBuilder().build(resourceAsStream);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<User> userList = this.mapper.selectList(null);
        for (User user : userList) {
            System.out.println(user);
        }

    }
}
