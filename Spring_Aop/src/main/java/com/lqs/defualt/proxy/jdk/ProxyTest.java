package com.lqs.defualt.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {

    public static void main(String[] args) {
        // 目标对象
        Target target = new Target();
        // 增强对象
        Advice advice = new Advice();
        TargetInterface targetInterface = (TargetInterface) Proxy.newProxyInstance(
                // 目标对象的类加载器
                target.getClass().getClassLoader(),
                // 目标对象相同的接口的字节码对象数组
                target.getClass().getInterfaces(),

                new InvocationHandler() {
                    @Override
                    // 代理对象执行的任何方法，实际上都是调用invoke方法
                    public Object invoke(Object proxyObject, Method method, Object[] targetParams) throws Throwable {
                        //前置方法
                        advice.before();
                        // 执行目标方法
                        method.invoke(target, targetParams);
                        // 后置方法
                        advice.afterRunning();
                        return method;
                    }
                }
        );
        targetInterface.save();
    }
}
