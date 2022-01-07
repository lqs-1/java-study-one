package com.lqs.controller;


import com.lqs.domain.Role;
import com.lqs.service.RoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RequestMapping(value = "role")
public class RoleController {

    private RoleService roleService;

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(value = "list")
    public ModelAndView get_role_list(ModelAndView modelAndView){

        List<Role> roleList = roleService.list();
        // 设置模版、模型
        modelAndView.addObject("roleList", roleList);
        // 设置视图
        modelAndView.setViewName("role-list");
        return modelAndView;
    }


}
