package com.lqs.test;


import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class OneTest {

    @Autowired
    DruidDataSource dataSource;

    @Test
    public void test1(){
        System.out.println(dataSource.getDriverClassName());
    }
}
