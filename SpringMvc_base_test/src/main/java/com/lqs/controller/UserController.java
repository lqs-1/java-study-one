package com.lqs.controller;


import com.lqs.domain.Role;
import com.lqs.domain.User;
import com.lqs.service.RoleService;
import com.lqs.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping(value = "user")
public class UserController {

    private UserService userService;
    private RoleService roleService;

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "list", method = {RequestMethod.GET})
    public ModelAndView get_user_list(ModelAndView modelAndView){
        List<User> userList = userService.list();
        modelAndView.addObject("userList", userList);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    @RequestMapping(value = "add", method = {RequestMethod.GET})
    public ModelAndView get_user_add_page(ModelAndView modelAndView){
        List<Role> roleList = roleService.list();
        modelAndView.addObject("roleList", roleList);
        modelAndView.setViewName("user-add");
        return modelAndView;
    }

    @RequestMapping(value = "save", method = {RequestMethod.POST})
//    @ResponseBody
    public String save_add_user(@RequestParam(value = "username", required = true) String username,
                                @RequestParam(value = "password", required = true) String password,
                                @RequestParam(value = "email", required = true) String email,
                                @RequestParam(value = "phoneNum", required = true) String phoneNum,
                                @RequestParam(value = "roleId", required = false) int[] roleIds
                                ){
        int rst = userService.save(username, password, email, phoneNum, roleIds);
        if (rst == 0){
            return "redirect:/user/add";
        }
        return "redirect:/user/list";
    }

    @RequestMapping(value = "del/{user_id}", method = {RequestMethod.GET})
//    @ResponseBody
    public String user_del(@PathVariable("user_id") int user_id){
        int rst = userService.del(user_id);
        return "redirect:/user/list";
    }
}
