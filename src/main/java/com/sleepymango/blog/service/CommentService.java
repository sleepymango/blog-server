package com.sleepymango.blog.service;

import com.sleepymango.blog.dto.CommentDTO;

import java.util.List;

/**
 * @Description:
 * @Author sleepymango
 * @Date 2021-03-31 02:54:17
 */
public interface CommentService {

    void save(CommentDTO commentDTO);

    List<CommentDTO> findAll(Long articleId);
}