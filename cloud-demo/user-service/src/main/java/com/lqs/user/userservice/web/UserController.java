package com.lqs.user.userservice.web;


import com.lqs.user.userservice.pattern.Testpattern;
import com.lqs.user.userservice.pojo.User;
import com.lqs.user.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
//@RefreshScope
public class UserController {

    @Autowired
    private UserService userService;

//    @Value("${pattern.name}")
//    private String name;

//    @Autowired
//    private Testpattern testpattern;

    /**
     * 路径： /user/110
     *
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id) {
        return userService.queryById(id);
    }

//    @GetMapping(value = "/i")
//    public String test(){
//        System.out.println(testpattern.getName());
//        return testpattern.getName();
//    }
}
