package com.lqs.test;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
    @Test
    public void SpringTest1(){
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        DruidDataSource druidDataSource = app.getBean(DruidDataSource.class);
        System.out.println(druidDataSource.getDriverClassName());
    }

}
