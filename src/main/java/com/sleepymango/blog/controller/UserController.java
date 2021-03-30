package com.sleepymango.blog.controller;

import com.sleepymango.blog.annotation.PassVerifyToken;
import com.sleepymango.blog.annotation.VerifyToken;
import com.sleepymango.blog.common.Result;
import com.sleepymango.blog.common.ResultEnum;
import com.sleepymango.blog.entity.User;
import com.sleepymango.blog.service.UserService;
import com.sleepymango.blog.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

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

    /**
     * 登录接口，返回存有token的cookie
     * @param username
     * @param password
     * @param response
     * @return
     */
    @PassVerifyToken
    @PostMapping("/login")
    public Result login(String username, String password, HttpServletResponse response){
        User user = userService.findByUsername(username);
        String token = JwtUtil.createToken(user);
        Cookie cookie = new Cookie("token", token);
        // 设置cookie路径，否则以前端请求前缀为路径，刷新后cookie会消失
        cookie.setPath("/");
        response.addCookie(cookie);
        return new Result(ResultEnum.SUCCESS.getStatusCode(),ResultEnum.SUCCESS.getMessage(), token);
    }

    /**
     *
     */
    @VerifyToken
    @GetMapping("/user")
    public Result findAll(String username) {
        User user = userService.findByUsername(username);
        return new Result(ResultEnum.SUCCESS.getStatusCode(),ResultEnum.SUCCESS.getMessage(),"niubi");
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
