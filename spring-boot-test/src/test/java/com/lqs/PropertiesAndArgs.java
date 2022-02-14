package com.lqs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

// 添加临时属性, 添加的临时属性只在这个测试用例中有效, 应用场景：
    // 在配置文件中的某些自定义的属性，在测试的时候，临时修改值, 覆盖配置文件的配置， 如果配置文件中没有这个属性，也可以
    //@SpringBootTest(properties = {"test.prop=test"})
    //@SpringBootTest(args = {"--test.prop=test"})
//@SpringBootTest(properties = {"test.prop=test"})
@SpringBootTest(args = {"--test.prop=test111"})
public class PropertiesAndArgs {

    @Value("${test.prop}")
    private String msg;
    @Test
    void testProperties(){
        System.out.println(msg);
    }
}
