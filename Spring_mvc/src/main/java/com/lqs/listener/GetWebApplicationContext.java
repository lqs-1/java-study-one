package com.lqs.listener;

import org.springframework.context.ApplicationContext;

import javax.servlet.ServletContext;

public class GetWebApplicationContext {
    public static ApplicationContext getWebApplicationContext(ServletContext servletContext){
        ApplicationContext app = (ApplicationContext) servletContext.getAttribute("app");
        return app;
    }

}
