package com.lqs.service.impl;

import com.lqs.exception.MyException;
import com.lqs.service.DemoService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class DemoServiceImpl implements DemoService {
    @Override
    public void save1() throws MyException {
        System.out.println("自定义异常");
        throw new MyException();
    }

    @Override
    public void save2() {
        System.out.println("空指针异常");
        String str = null;
        str.length();
    }

    @Override
    public void save3() throws FileNotFoundException {
        System.out.println("文件找不到异常");
        InputStream in = new FileInputStream("xxxx/xxx/xxx");
    }

    @Override
    public void save4() {
        System.out.println("0异常");
        int i = 2/0;
    }

    @Override
    public void save5() {
        System.out.println("类型转换异常");
        Object str = "liqisong";
        Integer num = (Integer)str;
    }
}
