package com.sleepymango.blog.controller;

import com.sleepymango.blog.annotation.PassVerifyToken;
import com.sleepymango.blog.common.Result;
import com.sleepymango.blog.common.ResultCode;
import com.sleepymango.blog.entity.User;
import com.sleepymango.blog.service.UserService;
import com.sleepymango.blog.utils.JwtUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author:
 * @Date: 2021-03-24 13:54:51
 */
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 登录接口，返回存有token的cookie
     * @param response
     * @return
     */
    @PassVerifyToken
    @PostMapping("/login")
    public Result login(String username,String password, HttpServletResponse response){
//        String username = (String) params.get("username");
        User user = userService.findByName(username);
        if (null==user){

            return new Result(ResultCode.AUTHENTICATED_NOT_LOGIN.getStatusCode(),ResultCode.AUTHENTICATED_NOT_LOGIN.getMessage(),null);
        }
        String accessToken = JwtUtil.createAccessToken(user);
        String refreshToken = JwtUtil.createRefreshToken(user);
        Cookie cookie1 = new Cookie("accessToken", accessToken);
        Cookie cookie2 = new Cookie("refreshToken", refreshToken);
        // 设置cookie路径，否则以前端请求前缀为路径，刷新后cookie会消失
        cookie1.setPath("/");
        cookie2.setPath("/");
        response.addCookie(cookie1);
        response.addCookie(cookie2);
        return new Result(ResultCode.SUCCESS.getStatusCode(), ResultCode.SUCCESS.getMessage(), null);
    }

    /**
     *
     */
    @GetMapping("/user")
    public Result findAll(String username) {
        User user = userService.findByName(username);
        return new Result(ResultCode.SUCCESS.getStatusCode(), ResultCode.SUCCESS.getMessage(),"niubi");
    }

    @GetMapping("/users/{username}")
    public Result findByUsername(@PathVariable("username") String username) {
        User user = userService.findByName(username);
        return new Result(ResultCode.SUCCESS.getStatusCode(), ResultCode.SUCCESS.getMessage(),user);
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
