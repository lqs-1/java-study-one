package com.lqs;

import com.lqs.config.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

// 在测试的时候加入临时的配置， @Import(TestConfig.class)， 配置类定义在test中的某个目录， 只在导入配置的测试中生效， 用于辅助测试
@SpringBootTest
@Import(TestConfig.class)
public class ConfigurationTest {
    @Autowired
    private String msg;

    @Test
    public void test1(){
        System.out.println(msg);
    }
}
