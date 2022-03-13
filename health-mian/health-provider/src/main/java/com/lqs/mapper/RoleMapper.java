package com.lqs.mapper;

import com.lqs.pojo.Role;

import java.util.List;
import java.util.Set;

public interface RoleMapper {
    Set<Role> findByUserId(Integer userId);
}
