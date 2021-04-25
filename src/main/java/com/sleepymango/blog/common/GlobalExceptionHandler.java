package com.sleepymango.blog.common;

import com.sleepymango.blog.exception.AuthorizationException;
import com.sleepymango.blog.exception.RedisException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Description 统一异常处理
 * @Author sleepymango
 * @Date 2021/3/26 0:47
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AuthorizationException.class)
    public Result authorizationExceptionHandle(AuthorizationException e){
        return new Result(e.getStatus(),e.getMessage(),null);
    }

    @ExceptionHandler(value = RedisException.class)
    public Result redisExceptionHandle(RedisException e){
        return new Result(e.getStatus(),e.getMessage(),null);
    }
}
