package com.lqs.util;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/*
* 在开发的过程中可能会遇到服务器出现异常
*   出现异常后，返回给前端的数据可能就不是指定的格式
*   可以使用springmvc的异常处理机制，做一个异常处理器，进行统一的处理
* */

//@ControllerAdvice
@RestControllerAdvice
public class ProjectExceptionAdvice {
//    可根据不同的异常来进行不同处理
//    @ExceptionHandler(NullPointerException.class)
//    public R doException(NullPointerException ex){
//        ex.printStackTrace();
//        return new R(false, "空指针异常");
//    }
//

//    所有异常统一处理
    @ExceptionHandler
    public R doException(Exception ex){
        ex.printStackTrace();
        return new R(false, "服务器异常");
    }
}
