package com.lqs.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.lqs.api.MemberService;
import com.lqs.mapper.MemberMapper;
import com.lqs.pojo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

@Service(interfaceClass = MemberService.class, version = "1.0")
@Transactional
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberMapper memberMapper;


    // 登录，存库
    @Override
    public Member login(Map paramMap) {
        Member member = new Member();
        String password = (String) paramMap.get("password");
        String telephone = (String) paramMap.get("telephone");

        // 查一下是否已经有这个会员
        Member findMember = memberMapper.findByTelephone(telephone);
        if (findMember != null){
            return findMember;
        }

        // 没有这个会员就添加
        if(password != null){
            member.setPassword(password);
        }
        member.setPhoneNumber(telephone);
        member.setRegTime(new Date());
        memberMapper.add(member);
        return member;
    }
}
