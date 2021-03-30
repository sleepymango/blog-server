package com.sleepymango.blog.service.impl;

import com.sleepymango.blog.repository.CommentRepository;
import com.sleepymango.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CommentRepository commentRepository;
}