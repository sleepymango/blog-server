package com.sleepymango.blog.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description RedisException
 * @Author sleepymango
 * @Date 2021/4/21 17:42
 **/

@Data
@AllArgsConstructor
public class RedisException extends RuntimeException{
    private int status;

    private String message;
}
