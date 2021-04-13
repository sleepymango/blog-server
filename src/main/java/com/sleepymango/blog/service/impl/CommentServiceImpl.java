package com.sleepymango.blog.service.impl;

import com.sleepymango.blog.entity.Comment;
import com.sleepymango.blog.repository.CommentRepository;
import com.sleepymango.blog.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @Author sleepymango
 * @Date 2021-03-31 02:54:17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }
}