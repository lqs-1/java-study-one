package com.lqs.defualt.proxy.jdk;

public class Advice {

    public void before(){
        System.out.println("前置增强");

    }

    public void afterRunning(){
        System.out.println("后置增强");
    }

}
