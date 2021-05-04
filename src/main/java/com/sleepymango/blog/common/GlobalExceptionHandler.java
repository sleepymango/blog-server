package com.sleepymango.blog.common;

import com.sleepymango.blog.exception.AuthorizationException;
import com.sleepymango.blog.exception.RedisException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description 统一异常处理
 * @Author sleepymango
 * @Date 2021/3/26 0:47
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AuthorizationException.class)
    @ResponseBody
    public Result authorizationExceptionHandle(AuthorizationException e, HttpServletResponse response){
        response.setStatus(e.getStatus());
        return new Result(e.getStatus(),e.getMessage(),null);
    }

    @ExceptionHandler(value = RedisException.class)
    @ResponseBody
    public Result redisExceptionHandle(RedisException e,HttpServletResponse response){
        response.setStatus(e.getStatus());
        return new Result(e.getStatus(),e.getMessage(),null);
    }
}
