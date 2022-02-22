package com.lqs.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lqs.mapper.CheckItemMapper;
import com.lqs.pojo.CheckItem;
import com.lqs.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.transaction.annotation.Transactional;

// 检查项服务接口的实现类
// 这个service注解也是dubbo的
@Service(interfaceClass = CheckItemService.class)
@Transactional // 事务
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemMapper checkItemMapper;

    public void add(CheckItem checkItem) {
        checkItemMapper.add(checkItem);
    }
}
