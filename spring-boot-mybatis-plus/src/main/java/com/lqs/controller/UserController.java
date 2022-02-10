package com.lqs.controller;

import com.lqs.domain.User;
import com.lqs.servicce.UserService;
import com.lqs.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/*
* R 用于同意格式
* */
@RestController
@RequestMapping(value = "users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public R getAll(){
//        List<User> userList = userService.getAll();
//        return userList;
        return new R(true, userService.getAll());
    }

    @GetMapping("{id}")
    public R getUser(@PathVariable int id){
//        User user = userService.getById(id);
//        return user;
        return new R(true, userService.getById(id));
    }

    @PostMapping
    public R addUser(@RequestBody User user){
//        Boolean insert = userService.insert(user);
//        return insert;
        return new R(userService.insert(user));
    }

    @PutMapping
    public R updateUser(@RequestBody User user){
//        Boolean update = userService.update(user);
//        return update;
        return new R(userService.update(user));
    }

    @DeleteMapping("{id}")
    public R deleteUser(@PathVariable int id){
//        Boolean del = userService.delete(id);
//        return del;
        return new R(userService.delete(id));
    }

    @GetMapping("page/{page}")
    public R paginator(@PathVariable int page){
//        IPage<User> paginator = userService.getPaginator(page, 2);
//        return paginator.getRecords();
        return new R(true, userService.getPaginator(page, 2).getRecords());
    }


}
