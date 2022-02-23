package com.lqs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class WebController {

    @GetMapping(value = "/get")
    public String getString(){
        return null;
    }


}
