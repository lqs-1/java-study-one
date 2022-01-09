package com.lqs.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component("myAspect")  // 切面类添加
@Aspect  // 标注当前类是一个切面类
public class MyAspect {

    // 使用切点表达式
    @Before("execution(* com.lqs.aop..*(..))")
    public void before(){
        System.out.println("前置增强");

    }
    @AfterReturning("execution(* com.lqs.aop..*(..))")
    public void afterRunning(){
        System.out.println("后置增强");
    }

    @Around("execution(* com.lqs.aop..*(..))")
    // ProceedingJoinPoint,切入点
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕前");
        proceedingJoinPoint.proceed();  // 执行目标方法
        System.out.println("环绕后");
    }
    @AfterThrowing("execution(* com.lqs.aop..*(..))")
    public void thowing(){
        System.out.println("抛出异常");
    }
    @After("execution(* com.lqs.aop..*(..))")
    public void after(){
        System.out.println("最终");
    }

    // 抽取切点表达式
    @Pointcut("execution(* com.lqs.aop..*(..))")
    public void getPointcut(){}

}
