package com.lqs;

import com.lqs.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

@SpringBootTest
public class MongoTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void test1(){
        User book = new User();
        book.setId(1);
        book.setName("lqs");
        mongoTemplate.insert(book);
    }

    @Test
    void test2(){
        List<User> all = mongoTemplate.findAll(User.class);
        for (User user : all) {
            System.out.println(user);
        }
    }
}
