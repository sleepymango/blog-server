package com.sleepymango.blog.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description token认证异常
 * @Author sleepymango
 * @Date 2021/3/26 15:20
 **/
@Data
@AllArgsConstructor
public class AuthorizationException extends Exception{
    private int status;
    private String message;

    public AuthorizationException(String message) {
        this.message = message;
    }
}
