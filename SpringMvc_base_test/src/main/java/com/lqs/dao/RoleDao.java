package com.lqs.dao;

import com.lqs.domain.Role;

import java.util.List;

public interface RoleDao {
    public List<Role> finAll();

    public int saveRole(String roleName, String roleDesc);

    public Boolean filterOne(String roleName);

    public int delRole(int id);

    public List<Role> get_roles(Long id);
}
