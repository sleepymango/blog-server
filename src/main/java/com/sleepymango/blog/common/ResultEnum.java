package com.sleepymango.blog.common;

/**
 * @Description TODO
 * @Author sleepymango
 * @Date 2021/3/25 20:14
 **/

public enum ResultEnum {
    // 成功状态码
    SUCCESS("200","成功"),
    // 未登录
    AUTHENTICATED_NOT_LOGIN("401","未登录"),
    // 登录过期
    AUTHENTICATED_EXPIRED("401","登录过期"),
    // 没有权限
    PERMISSION_DENIED("403","没有权限"),
    // 找不到资源
    NOT_FOUND("404","404 not found");

    private final String statusCode;
    private final String message;

    ResultEnum(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}
