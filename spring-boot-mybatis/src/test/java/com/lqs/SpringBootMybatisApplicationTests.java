package com.lqs;

import com.lqs.domain.User;
import com.lqs.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringBootMybatisApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test1(){
        List<User> mapperAll = userMapper.selectList(null);
        System.out.println(mapperAll);
    }

}
