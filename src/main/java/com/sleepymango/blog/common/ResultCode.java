package com.sleepymango.blog.common;

/**
 * @Description
 * @Author sleepymango
 * @Date 2021/3/25 20:14
 **/

public enum ResultCode {
    // 成功状态码
    SUCCESS(200,"成功"),
    // 未登录
    AUTHENTICATED_NOT_LOGIN(401,"未登录"),
    // 验证失败
    AUTHENTICATION_FAILED(401,"验证失败，账号或密码错误"),
    // 登录过期
    AUTHENTICATED_EXPIRED(401,"登录过期"),
    // 没有权限
    PERMISSION_DENIED(403,"没有权限"),
    // 找不到资源
    NOT_FOUND(404,"404 not found"),
    // 用户名已存在
    NAME_EXIST(409,"用户名已存在"),
    // 邮箱已存在
    EMAIL_EXIST(409,"邮箱已存在");


    private final int statusCode;
    private final String message;

    ResultCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}
