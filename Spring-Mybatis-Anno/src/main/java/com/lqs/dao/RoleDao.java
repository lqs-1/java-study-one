package com.lqs.dao;

import com.lqs.domain.Role;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleDao {
    @Select("select * from role where id=#{id}")
    public List<Role> findById(int id);


    @Select("select * from role")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(
                    property = "userList",
                    column = "id",
                    javaType = List.class,
                    many = @Many(select = "com.lqs.dao.UserDao.findUserList")
            )
    })
    public List<Role> findOneToMany();



}
