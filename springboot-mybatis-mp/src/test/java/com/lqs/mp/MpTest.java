package com.lqs.mp;

import com.lqs.mp.mapper.UserMapper;
import com.lqs.mp.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MpTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void findAll(){
        List<User> userList = this.userMapper.selectList(null);
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
