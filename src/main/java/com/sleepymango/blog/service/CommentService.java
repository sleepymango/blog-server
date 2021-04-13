package com.sleepymango.blog.service;

import com.sleepymango.blog.entity.Comment;

/**
 * @Description:
 * @Author sleepymango
 * @Date 2021-03-31 02:54:17
 */
public interface CommentService {

    void save(Comment comment);
}