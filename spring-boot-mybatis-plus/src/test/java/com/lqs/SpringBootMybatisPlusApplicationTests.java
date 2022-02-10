package com.lqs;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lqs.domain.User;
import com.lqs.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class SpringBootMybatisPlusApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void findAll() {
        List<User> userList = userMapper.selectList(null);
        for (User user : userList) {
            System.out.println(user);
        }
    }



    @Test
    public void insertOne(){
        User user = new User();
        user.setId(300);
        user.setMoney(3000);
        user.setName("lq");
        int i = userMapper.insert(user);
        System.out.println(i);
    }

    @Test
    public void delById(){
        int i = userMapper.deleteById(300);
        System.out.println(i);
    }

    @Test
    public void updateById(){
        User user = new User();
        user.setId(300);
        user.setMoney(3000);
        user.setName("lqs");
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    @Test
    public void getPage(){
//        第一个参数当前页，第二个参数每页多少条
        IPage<User> page = new Page(2,3);
//        第一个参数是分页对象，第二个参数是分页数据的查询条件
        userMapper.selectPage(page, null);
//        总页数
        System.out.println(page.getPages());
//        当前页
        System.out.println(page.getCurrent());
//        当前页数据
        System.out.println(page.getRecords());
//        每页多少条
        System.out.println(page.getSize());
//        总条数
        System.out.println(page.getTotal());

    }

    @Test
    public void selectBy(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.like("name", "l");
        List<User> userList = userMapper.selectList(queryWrapper);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void selectBy2(){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>();
        queryWrapper.like(User::getName, "l");
        List<User> userList = userMapper.selectList(queryWrapper);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void selectBy3(){
//        String name = "l";
        String name = null;
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>();
        queryWrapper.like(name != null,User::getName, "l");
        List<User> userList = userMapper.selectList(queryWrapper);
        for (User user : userList) {
            System.out.println(user);
        }
    }


}
