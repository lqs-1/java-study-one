package com.lqs.service;

import com.lqs.exception.MyException;

import java.io.FileNotFoundException;

public interface DemoService {

    public void save1() throws MyException;
    public void save2();
    public void save3() throws FileNotFoundException;
    public void save4();
    public void save5();

}
