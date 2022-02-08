package com.lqs;

import com.lqs.domain.User;
import com.lqs.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MybatisTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test1(){
        List<User> mapperAll = userMapper.selectList(null);
        for (User user : mapperAll) {
            System.out.println(user);
        }
    }

    @Test
    public void test2(){
        User user = new User();
        user.setId(2222);
        user.setName("hahahahaha");
        user.setMoney(22222);
        userMapper.insert(user);
    }




}
