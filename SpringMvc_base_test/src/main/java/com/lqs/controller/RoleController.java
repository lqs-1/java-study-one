package com.lqs.controller;


import com.lqs.domain.Role;
import com.lqs.service.RoleService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RequestMapping(value = "role")
public class RoleController {

    private RoleService roleService;

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }


    public ModelAndView get_page_data(){
        ModelAndView modelAndView = new ModelAndView();
        List<Role> roleList = roleService.list();
        // 设置模版、模型
        modelAndView.addObject("roleList", roleList);
        // 设置视图
        modelAndView.setViewName("role-list");
        return modelAndView;
    }

    @RequestMapping(value = "list", method = {RequestMethod.GET})
    public ModelAndView get_role_list(){
        ModelAndView role_list_page_data = get_page_data();
        return role_list_page_data;
    }


    @RequestMapping(value = "add", method = {RequestMethod.GET})
    public  String  set_add_role(){
        return "role-add";
    }


    @RequestMapping(value = "save", method = {RequestMethod.POST})
//    @ResponseBody
    public String save_add_role(@RequestParam(value = "roleName") String roleName, @RequestParam(value = "roleDesc") String roleDesc){
        int rst = roleService.save(roleName, roleDesc);
        if (rst == 0){
            return "redirect:/role/add";
        }
        return "redirect:/role/list";
    }

    @RequestMapping(value = "del/{id}", method = {RequestMethod.GET})
    public String del_role(ModelAndView modelAndView, @PathVariable(value = "id", required = true) int id, HttpServletResponse response){
        int delRst = roleService.del(id);
        if (delRst == 0){
            return "redirect:/role/list";
        }
        return "redirect:/role/list";
    }

}
