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



    @Override
    public int save(String roleName, String roleDesc) {
        int rst;
        Boolean roleHas = roleDao.filterOne(roleName);
        if (!roleHas){
            return 0;
        }
        rst = roleDao.saveRole(roleName, roleDesc);
        return rst;


    }

    @Override
    public int del(int id) {
        int rst = roleDao.delRole(id);
        return rst;
    }
}
