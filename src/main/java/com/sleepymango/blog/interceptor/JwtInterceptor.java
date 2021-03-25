package com.sleepymango.blog.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.sleepymango.blog.annotation.PassVerifyToken;
import com.sleepymango.blog.annotation.VerifyToken;
import com.sleepymango.blog.entity.User;
import com.sleepymango.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Description TODO
 * @Author sleepymango
 * @Date 2021/3/25 21:59
 **/

public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        String token = request.getHeader("token");
        // 如果不是映射方法则放行
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();

        // 该方法是否被放行
        if (method.isAnnotationPresent(PassVerifyToken.class)){
            PassVerifyToken passVerifyToken = method.getAnnotation(PassVerifyToken.class);
            if (passVerifyToken.required()){
                return true;
            }
        }

        // 该方法需要验证
        if (method.isAnnotationPresent(VerifyToken.class)){
            VerifyToken verifyToken = method.getAnnotation(VerifyToken.class);
            if (!verifyToken.required()){
                return true;
            }

            if (null==token){
                throw new RuntimeException("token is null, 重新登录");
            }
            // 从token中获取ID
            String userId;
            try {
                userId = JWT.decode(token).getAudience().get(0);
            } catch (JWTDecodeException e) {
                throw new RuntimeException("token解析异常");
            }
            // 用户是否存在
            User user = userService.findById(Long.valueOf(userId));
            if (null==user){
                throw new RuntimeException("用户不存在");
            }
            // 验证是否是该用户
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getUserPass())).build();
            try {
                jwtVerifier.verify(token);
            } catch (JWTVerificationException e) {
                throw new RuntimeException("token验证失败");
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
