package com.lqs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseLogger {
    private Class aClass;
    static Logger log;
    public BaseLogger(){
        aClass = this.getClass();
        log = LoggerFactory.getLogger(aClass);
    }


}
