package com.sleepymango.blog.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sleepymango.blog.entity.User;

/**
 * @Description JWT工具类
 * @Author sleepymango
 * @Date 2021/3/25 22:26
 **/

public class JwtUtil {
    public static String createToken(User user){
        return JWT.create()
                .withAudience(String.valueOf(user.getUserId()))
                .sign(Algorithm.HMAC256(user.getUserPass()));
    }

    public static void parseToken(String token){
        JWT.decode(token);
    }
}
