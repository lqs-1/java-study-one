package com.lqs.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lqs.domain.User;
import com.lqs.servicce.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void testGetById(){
        User user = userService.getById(300);
        System.out.println(user);
    }

    @Test
    void testFindAll(){
        List<User> userList = userService.getAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    void deleteById(){
        Boolean delete = userService.delete(300);
        if (delete) {
            System.out.println("ok");
        }
    }

    @Test
    void insert(){
        User user = new User();
        user.setName("lqs");
        user.setMoney(2);
        user.setId(3000);
        Boolean rst = userService.insert(user);
        if (rst) {
            System.out.println("ok");
        }
    }

    @Test
    void update(){
        User user = new User();
        user.setName("lqs");
        user.setMoney(200000);
        user.setId(3000);
        Boolean rst = userService.update(user);
        if (rst) {
            System.out.println("ok");
        }
    }

    @Test
    void getPage() {
        IPage<User> paginator = userService.getPaginator(2, 3);
        System.out.println(paginator.getPages());
    }
}
