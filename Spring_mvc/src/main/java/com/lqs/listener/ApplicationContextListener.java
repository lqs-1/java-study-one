package com.lqs.listener;

import com.lqs.config.SpringConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 读取web.xml中的全局参数,这样就能不把Spring配置文件写死，但是我这里没用
        ServletContext servletContext = sce.getServletContext();
//        String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");
//        System.out.println(contextConfigLocation);

        ApplicationContext app = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        servletContext.setAttribute("app", app);
//        System.out.println("监听执行");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
