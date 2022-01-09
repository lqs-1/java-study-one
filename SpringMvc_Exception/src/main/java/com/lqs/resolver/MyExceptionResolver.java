package com.lqs.resolver;

import com.lqs.exception.MyException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        /*
        * 自定义异常处理器：
        *   接口HandlerExceptionResolver，并且重写一个方法就可以了
        *   instanceof 是判断左边的是否是右边的实例对象
        * */
        if (ex instanceof MyException){
            modelAndView.setViewName("/error1.jsp");
        }else if(ex instanceof ClassCastException){
            modelAndView.setViewName("/error2.jsp");
        }else{
            modelAndView.setViewName("/error.jsp");
        }

        return modelAndView;
    }
}
