package com.lqs.service.impl;


import com.lqs.service.TestService;
import org.apache.dubbo.config.annotation.Service;


@Service
public class TestServiceImpl implements TestService {

    @Override
    public String getString() {
        return "hello dubbo";
    }
}
