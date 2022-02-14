package com.lqs;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void setRedis(){
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("name", "liqisong");
        HashOperations<String, Object, Object> stringObjectObjectHashOperations = redisTemplate.opsForHash();
        stringObjectObjectHashOperations.put("history",  "h1", "1");
        stringObjectObjectHashOperations.put("history",  "h2", "2");
    }

    @Test
    void getRedis(){
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String name = valueOperations.get("name");
        System.out.println(name);
        HashOperations<String, Object, Object> stringObjectObjectHashOperations = redisTemplate.opsForHash();
        Object o = stringObjectObjectHashOperations.get("history", "h2");
        System.out.println(o);

    }


}
