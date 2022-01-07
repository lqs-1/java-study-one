package com.lqs.service.impl;

import com.lqs.dao.RoleDao;
import com.lqs.domain.Role;
import com.lqs.service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> list() {
        List<Role> roleList = roleDao.finAll();
        return roleList;
    }
}
