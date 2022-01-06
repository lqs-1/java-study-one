package com.lqs.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration

@ComponentScan(basePackages = "com.lqs")

@Import({DataSourceConfiguration.class})

public class SpringConfiguration {
}
