package com.lqs.controller;

import com.lqs.service.TestService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class WebController {

    @Reference
    private TestService testService;

    @GetMapping(value = "/get")
    public String getString(){
        return testService.getString();

    }


}
