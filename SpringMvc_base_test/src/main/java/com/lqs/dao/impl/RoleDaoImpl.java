package com.lqs.dao.impl;

import com.lqs.dao.RoleDao;
import com.lqs.domain.Role;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RoleDaoImpl implements RoleDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Role> finAll() {
        List<Role> roleList = jdbcTemplate.query("select * from sys_role", new BeanPropertyRowMapper<Role>(Role.class));
        return roleList;
    }

    @Override
    public int saveRole(String roleName, String roleDesc) {
        int rst = jdbcTemplate.update("insert into sys_role values (null, ?, ?)", roleName, roleDesc);
        return rst;
    }

    @Override
    public Boolean filterOne(String roleName) {
        List<Role> roleList = jdbcTemplate.query("select * from sys_role where roleName = ?", new BeanPropertyRowMapper<Role>(Role.class), roleName);
        if (roleList.isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public int delRole(int id) {
        int rst = jdbcTemplate.update("delete from sys_role where id = ?", id);
        return rst;
    }
}
