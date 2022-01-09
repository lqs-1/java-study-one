package com.lqs.controller;


import com.lqs.exception.MyException;
import com.lqs.service.DemoService;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "demo")
public class DemoController {

    private DemoService demoService;

    public void setDemoService(DemoService demoService) {
        this.demoService = demoService;
    }

    @RequestMapping(value = "demo")
    public String demo() throws MyException {
        demoService.save4();
        return "/index.jsp";
    }
}
