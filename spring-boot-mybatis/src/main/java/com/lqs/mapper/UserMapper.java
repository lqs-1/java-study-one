package com.lqs.mapper;


import com.lqs.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user")
    List<User> findAll();

    @Insert("insert into user values(#{id},#{name},#{money})")
    void insert(User user);





}
