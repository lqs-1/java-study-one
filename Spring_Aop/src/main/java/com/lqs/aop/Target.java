package com.lqs.aop;


import org.springframework.stereotype.Component;

@Component("target")  // 目标类添加
public class Target {

    public void save() {
//        int i = 1/0;
        System.out.println("save running....");
    }
}
