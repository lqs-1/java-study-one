package com.lqs.service;

import com.lqs.domain.Role;

import java.util.List;

public interface RoleService {
    public List<Role> list();

    public int save(String roleName, String roleDesc);

    public int del(int id);
}
