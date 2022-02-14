package com.lqs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 添加为配置类
@Configuration
public class TestConfig {
    @Bean
    public String fig(){
        return "hehehe";
    }
}
