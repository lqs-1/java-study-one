package com.lqs.mapper;

import com.lqs.pojo.Member;

public interface MemberMapper {
    // 根据电话号码查找会员
    Member findByTelephone(String telephone);
    // 添加会员
    void add(Member member);

    Member findById(Integer memberId);
}
