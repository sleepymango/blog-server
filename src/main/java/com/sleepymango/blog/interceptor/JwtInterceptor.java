package com.sleepymango.blog.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sleepymango.blog.annotation.PassVerifyToken;
import com.sleepymango.blog.annotation.VerifyToken;
import com.sleepymango.blog.entity.User;
import com.sleepymango.blog.exception.AuthorizationException;
import com.sleepymango.blog.service.UserService;
import com.sleepymango.blog.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Description jwt拦截器
 * @Author sleepymango
 * @Date 2021/3/25 21:59
 **/

public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        String token = request.getHeader("Authorization");
        // 如果不是映射方法则放行
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();

        // 该方法是否被放行
        if (method.isAnnotationPresent(PassVerifyToken.class)) {
            PassVerifyToken passVerifyToken = method.getAnnotation(PassVerifyToken.class);
            if (passVerifyToken.required()) {
                return true;
            }
        }

        // 该方法需要验证
        if (method.isAnnotationPresent(VerifyToken.class)) {
            VerifyToken verifyToken = method.getAnnotation(VerifyToken.class);
            if (!verifyToken.required()) {
                return true;
            }

            if (null == token) {
                throw new AuthorizationException(403, "请登录");
            }
            String[] tokens = token.split(";");
            String accessToken;
            String refreshToken;
            try {
                accessToken = tokens[0];
                refreshToken = tokens[1];
            } catch (IndexOutOfBoundsException e) {
                throw new AuthorizationException(403, "token解析异常，长度异常");
            }
            // 从token中获取ID
            Long userId;
            User user;
            try {
                DecodedJWT decodedAccessJwt = JWT.decode(accessToken);
                userId = decodedAccessJwt.getClaim("userId").asLong();
                // 用户是否存在
                user = userService.findById(userId);
                if (null == user) {
                    throw new AuthorizationException(403, "身份信息异常，无该用户");
                }
                Date accessJwtExpiresAt = decodedAccessJwt.getExpiresAt();
                // 判断accessToken是否过期
                if (accessJwtExpiresAt.before(new Date())) {
                    DecodedJWT decodeRefreshJwt = JWT.decode(refreshToken);
                    Date refreshJwtExpiresAt = decodeRefreshJwt.getExpiresAt();
                    // accessToken过期，判断refreshToken
                    if (refreshJwtExpiresAt.before(new Date())) {
                        throw new AuthorizationException(403, "身份过期，请重新登录");
                    }
                    // 重新签发token
                    accessToken = JwtUtil.createAccessToken(user);
                    refreshToken = JwtUtil.createRefreshToken(user);
                    Cookie newAccessToken = new Cookie("accessToken", accessToken);
                    Cookie newRefreshToken = new Cookie("refreshToken", refreshToken);
                    newAccessToken.setPath("/");
                    newRefreshToken.setPath("/");
                    response.addCookie(newAccessToken);
                    response.addCookie(newRefreshToken);
                }
            } catch (JWTDecodeException e) {
                throw new AuthorizationException(403, "token解析异常");
            }

            // 验证是否是该用户
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
            try {
                jwtVerifier.verify(accessToken);
            } catch (JWTVerificationException e) {
                throw new AuthorizationException(403, "token验证失败");
            }
            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
