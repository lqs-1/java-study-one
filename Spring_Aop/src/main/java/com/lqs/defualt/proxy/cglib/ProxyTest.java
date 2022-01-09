package com.lqs.defualt.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ProxyTest {

    public static void main(String[] args) {
        // 目标对象
        Target target = new Target();
        // 增强对象
        Advice advice = new Advice();



        // 创建增强器
        Enhancer enhancer = new Enhancer();
        // 设置父（目标）
        enhancer.setSuperclass(target.getClass());
        // 设置回调
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object proxyObject, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
                // 执行前置
                advice.before();
                // 执行目标
                method.invoke(target, params);
                // 执行后置
                advice.afterRunning();
                return null;
            }
        });

        // 生成代理对象
        Target proxy = (Target) enhancer.create();
        proxy.save();

    }

}
