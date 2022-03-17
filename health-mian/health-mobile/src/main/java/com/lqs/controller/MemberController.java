package com.lqs.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.lqs.api.MemberService;
import com.lqs.constant.MessageConstant;
import com.lqs.entity.Result;
import com.lqs.pojo.Member;
import com.lqs.utils.SMSUtils;
import com.lqs.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//login17398827615:登录手机验证码
//login_17398827615:member信息
//17398827615:预定验证码信息
//


@RestController
@RequestMapping(value = "member")
public class MemberController {

    @Reference(version = "1.0")
    private MemberService memberService;

    @Autowired
    private JedisPool jedisPool;

    // 发送验证码
    @GetMapping(value = "getSmsCode.do")
    public Result getSmsCode(@RequestParam("telephone") String telephone) {

        // 先判断是否在60秒内redis中有过验证码
        String isHas;
        try {
            isHas = jedisPool.getResource().get("login" + telephone);
        } catch (Exception e) {
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        if (isHas != null) {
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FREQUENTLY);
        }

        // 发送验证码
        String smsCode = ValidateCodeUtils.generateValidateCode4String(4);
        try {
            Result result = SMSUtils.sendShortMessage(telephone, smsCode);
            jedisPool.getResource().setex("login" + telephone, 300, smsCode);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
    }


    // 验证码快捷登录
    @PostMapping(value = "login.do")
    public Result login(HttpServletResponse response, @RequestBody Map paramMap) {
        // 验证码是否输入正确
        String telephone = (String) paramMap.get("telephone");
        String smsCode = (String) paramMap.get("validateCode");
        try {
            String realSmsCode = jedisPool.getResource().get("login" + telephone);
            if (!realSmsCode.equals(smsCode)) {
                return new Result(false, MessageConstant.VALIDATECODE_ERROR);
            }
            // 保存到数据库
            Member member = memberService.login(paramMap);
            // 设置cookie
            Cookie cookie = new Cookie("login_telephone", telephone);
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60 * 24 * 15);
            response.addCookie(cookie);
            // 将member信息保存到redis中
            // 序列化member对象
            String memberJson = JSON.toJSON(member).toString();
            jedisPool.getResource().setex("login_" + telephone, 3600, memberJson);

            return new Result(true, MessageConstant.LOGIN_SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
    }

    public static void main(String[] args) {

    }
}
