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
     * 15分钟过期时间
     */
//    public static final Long ACCESS_EXPIRE_TIME_MILLS = 900000L;
    public static final Long ACCESS_EXPIRE_TIME_MILLS = 5000L;
    /**
     * 两个小时过期时间
     */
//    public static final Long REFRESH_EXPIRE_TIME_MILLS = 60*60*2*1000L;
    public static final Long REFRESH_EXPIRE_TIME_MILLS = 10*2*1000L;

    /**
     * 创建token
     *
     * @param user
     * @return
     */
    public static String createAccessToken(User user) {
        return JWT.create()
                .withClaim("userId",user.getId())
                .withAudience("accessToken")
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_EXPIRE_TIME_MILLS))
                .sign(Algorithm.HMAC256(user.getPassword()));
    }

    public static String createRefreshToken(User user) {
        return JWT.create()
                .withClaim("userId",user.getId())
                .withAudience("refreshToken")
                .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_EXPIRE_TIME_MILLS))
                .sign(Algorithm.HMAC256(user.getPassword()));
    }

}
