package com.lqs.mapper;

import com.lqs.domain.User;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@MapperScan
public interface UserMapper {
    List<User> findAll();
}
