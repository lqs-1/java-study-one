package com.lqs;

import com.alibaba.druid.pool.DruidDataSource;
import com.lqs.config.ServerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

/*
* EnableConfigurationProperties和ConfigurationProperties
* EnableConfigurationProperties：可以将ConfigurationProperties对应的类加入到Spring容器
* EnableConfigurationProperties和Component不可以同时使用，否则就会出现NoUniqueBeanDefinitionException异常
* EnableConfigurationProperties可以更直观的看到哪些实体使用了配置参数
*
* ConfigurationProperties可以给自定义的实体使用，也可以给第三方的实体使用prefix属性就是参数的名字
* */

@EnableConfigurationProperties({ServerConfig.class})
public class SpringBootConfigurationApplication {

    @ConfigurationProperties(prefix = "datasource")
    @Bean
    public DruidDataSource dataSource(){
        DruidDataSource ds = new DruidDataSource();
//        ds.setDriverClassName("com.mysql.jdbc.Driver");

        return ds;
    }

    public static void main(String[] args) {



        ConfigurableApplicationContext run = SpringApplication.run(SpringBootConfigurationApplication.class, args);
        ServerConfig bean = run.getBean(ServerConfig.class);
        DruidDataSource bean1 = run.getBean(DruidDataSource.class);
        System.out.println(bean);
        System.out.println(bean1.getDriverClassName());
    }

}
