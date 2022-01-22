package com.lqs.dao;

import com.lqs.domain.Role;
import com.lqs.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {
    @Insert("insert into user(id,username) values(#{id},#{username})")
    public void insertOne(User user);
    @Update("update user set username=#{username} where id=#{id}")
    public void updateOne(User user);
    @Delete("  delete from user where id=#{id}")
    public void deleteOne(int id);
    @Select(" select * from user;")
    public List<User> findAll();
    @Select("select * from user where id=#{id}")
    public User findOne(int id);
    @Select("select * from user where role_id=#{role_id}")
    public List<User> findUserList(int role_id);


//    one to one第一种方式，一起查
//    @Select(" select *,r.id r_id from user u,role r where u.role_id=r.id;")
//    @Results({
//            @Result(column = "id", property = "id"),
//            @Result(column = "username", property = "username"),
//            @Result(column = "r_id", property = "role.id"),
//            @Result(column = "name", property = "role.name"),
//    })
//    public List<User> findUserAndRole();


    //    one to one第一种方式，分开查
    @Select(" select * from user")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(
                    property = "role", //要封装的属性名称
                    column = "role_id",  // 根据那个字段进行查询role表的数据
                    javaType = Role.class,  // 要封装的实体类型
                    // 一对一查询语法，select表示查询哪个接口获得数据
                    one = @One(select = "com.lqs.dao.RoleDao.findById")


            )
    })
    public List<User> findUserAndRole();
}
