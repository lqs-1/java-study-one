package com.lqs.service;


import com.alibaba.dubbo.config.annotation.Reference;
import com.lqs.api.UserService;
import com.lqs.pojo.Permission;
import com.lqs.pojo.Role;
import com.lqs.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class SpringSecurityUserService implements UserDetailsService {
    // 使用dubbo通过网络远程调用服务提供方获取数据库中的信息
    @Reference(version = "1.0")
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    // 根据用户名查询数据库获取用户信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        String passowrd = bCryptPasswordEncoder.encode("admin");
//        System.out.println(passowrd);


        User user = userService.findByUserName(username);
        if (user == null){
            // 用户不存在
            return null;
        }
        // 动态为当前用户授权
        List<GrantedAuthority> list = new ArrayList<>();
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            // 遍历集合，为用户授予角色
            list.add(new SimpleGrantedAuthority(role.getKeyword()));
            // 通过角色获取权限
            Set<Permission> permissions = role.getPermissions();
            // 遍历授予权限
            for (Permission permission : permissions) {
                list.add(new SimpleGrantedAuthority(permission.getKeyword()));
            }
        }
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), list);
    }
}
