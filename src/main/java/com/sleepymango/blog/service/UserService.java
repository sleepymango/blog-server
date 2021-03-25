package com.sleepymango.blog.service;

import com.sleepymango.blog.entity.User;

/**
 * @Description:
 * @Author sleepymango
 * @Date 2021-03-24 13:54:51
 */
public interface UserService {
    /**
     * 通过userId查找用户
     * @param userId
     * @return
     */
    User findById(Long userId);
}