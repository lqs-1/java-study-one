package com.lqs.dao;

import com.lqs.domain.Role;

import java.util.List;

public interface RoleDao {
    public List<Role> findAll();
    public Role findOne(int id);

}
