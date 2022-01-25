package com.lqs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lqs.domain.User;

import java.util.List;

//mapper接口继承BaseMapper泛型就是实体对象
public interface UserMapper extends BaseMapper<User> {}
