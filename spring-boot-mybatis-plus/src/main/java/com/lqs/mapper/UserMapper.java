package com.lqs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lqs.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
