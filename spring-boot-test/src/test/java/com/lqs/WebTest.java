package com.lqs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.HeaderResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;
import org.springframework.transaction.annotation.Transactional;

// 表现层测试，启动web环境：
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT), 使用随机端口
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT), 使用默认端口
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)

// 事务开启，开启之后，默认回滚， 防止测试的数据提交到数据库
@Transactional
// 开启事务只有，关闭回滚
@Rollback(value = false)

// 开启虚拟mvc调用
@AutoConfigureMockMvc
public class WebTest {
    @Test
    // 注入虚拟mvc调用对象
    void test1(@Autowired MockMvc mvc) throws Exception {
        // http://localhost:8080/tests
        // 创建虚拟请求
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/tests");
        // 执行请求
        ResultActions ac = mvc.perform(builder);

        // 设置预期值， 与真实值进行比较， 江湖人称：断言

        // 1.请求状态匹配
        // 定义本次调用的预期值, 定义执行状态匹配器
        StatusResultMatchers status = MockMvcResultMatchers.status();
        // 预计本次调用是成功的，状态200，预期值
        ResultMatcher ok = status.isOk();
        // 添加预计值到本次调用过程中进行匹配，执行断言
        ac.andExpect(ok);

        // 2.执行结果，相应参数匹配, 字符串
        // 定义本次调用的预期值, 定义执行状态匹配器
        ContentResultMatchers content = MockMvcResultMatchers.content();
        // 预计本次调用是成功的，状态200，预期值
        ResultMatcher string = content.string("success");
        // 添加预计值到本次调用过程中进行匹配，执行断言
        ac.andExpect(string);

    }

    @Test
    void testJson(@Autowired MockMvc mvc) throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/tests/1");
        ResultActions perform = mvc.perform(mockHttpServletRequestBuilder);

        // 3.执行结果，相应参数匹配, json
        // 定义本次调用的预期值, 定义执行状态匹配器
        ContentResultMatchers content = MockMvcResultMatchers.content();
        // 预计本次调用是成功的，状态200，预期值
        ResultMatcher json = content.json("{\"id\":1,\"name\":\"lqs\"}");
        // 添加预计值到本次调用过程中进行匹配，执行断言
        perform.andExpect(json);

    }


    @Test
    void testContentType(@Autowired MockMvc mvc) throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/tests/1");
        ResultActions perform = mvc.perform(mockHttpServletRequestBuilder);

        // 4.Content-Type
        // 定义本次调用的预期值, 定义执行状态匹配器
        HeaderResultMatchers header = MockMvcResultMatchers.header();
        // 预计本次调用是成功的，状态200，预期值
        ResultMatcher contentType = header.string("Content-Type", "application/json");
        // 添加预计值到本次调用过程中进行匹配，执行断言
        perform.andExpect(contentType);

    }


}
