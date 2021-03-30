package com.sleepymango.blog.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sleepymango.blog.entity.User;

import java.util.Date;

/**
 * @Description JWT工具类
 * @Author sleepymango
 * @Date 2021/3/25 22:26
 **/

public class JwtUtil {
    /**
     *     15分钟过期时间
     */
    public static final Long EXPIRE_TIME_MILLS = 900000L;

    /**
     * 创建token
     * @param user
     * @return
     */
    public static String createToken(User user){
        return JWT.create()
                .withAudience(String.valueOf(user.getUserId()))
                .withExpiresAt(new Date(System.currentTimeMillis()+EXPIRE_TIME_MILLS))
                .sign(Algorithm.HMAC256(user.getPassword()));
    }

}
