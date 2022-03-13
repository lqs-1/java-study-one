package com.lqs.mapper;

import com.lqs.pojo.Permission;

import java.util.Set;

public interface PermissionMapper {
    Set<Permission> findByRoleId(Integer roleId);
}
