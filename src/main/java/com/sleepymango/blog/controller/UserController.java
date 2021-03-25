package com.sleepymango.blog.controller;

import com.sleepymango.blog.annotation.PassVerifyToken;
import com.sleepymango.blog.common.Result;
import com.sleepymango.blog.common.ResultUtil;
import com.sleepymango.blog.entity.User;
import com.sleepymango.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author:
 * @Date: 2021-03-24 13:54:51
 */
@RestController
@CrossOrigin
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PassVerifyToken
    @PostMapping("/login")
    public Result login(String username,String password){
        System.out.println(username+""+password);
        return ResultUtil.success(new User());
    }

    /**
     *
     */
    @GetMapping("/user")
    public void findAll() {
        System.out.println("hello");
    }


    /**
     * @param
     */
    @PostMapping("/user")
    public void save() {
    }

    @PutMapping("/user")
    public void update() {
    }


    @DeleteMapping("/user")
    public void delete(@PathVariable Integer id) {
    }

}
