package com.lqs.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.lqs.mapper.CheckItemMapper;
import com.lqs.pojo.CheckItem;
import org.springframework.beans.factory.annotation.Autowired;

@Service(interfaceClass = CheckItemService.class)
//@Transactional
public class CheckItemServiceImpl implements CheckItemService{
    @Autowired
    private CheckItemMapper checkItemMapper;

    @Override
    public void add(CheckItem checkItem) {
        checkItemMapper.add(checkItem);
    }
}
