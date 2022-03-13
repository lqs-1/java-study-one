package com.lqs.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.lqs.api.UserService;
import com.lqs.mapper.PermissionMapper;
import com.lqs.mapper.RoleMapper;
import com.lqs.mapper.UserMapper;
import com.lqs.pojo.Permission;
import com.lqs.pojo.Role;
import com.lqs.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service(interfaceClass = UserService.class, version = "1.0")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;


    @Override
    // 根据用户名查询数据库获取用户信息和关联的角色信息，同时查出角色对应的权限信息
    public User findByUserName(String username) {
        User user = userMapper.findByUserName(username); // 查询用户基本信息，不包含用户角色
        if(user == null){
            return null;
        }
        Integer userId = user.getId();
        // 查询用户角色
        Set<Role> roles = roleMapper.findByUserId(userId);
        for (Role role : roles) {
            Integer roleId = role.getId();
            // 根据角色查询关联的权限
            Set<Permission> permissions = permissionMapper.findByRoleId(roleId);
            role.setPermissions(permissions); // 让角色关联权限
        }
        // 让用户关联角色
        user.setRoles(roles);
        return user;
    }
}
