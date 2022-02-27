package com.lqs.mapper;

import com.lqs.domain.User;
//import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//@Mapper
public interface UserMapper {
    public void save(User user);
    public List<User> findAll();
}
