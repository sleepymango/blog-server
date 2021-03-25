package com.sleepymango.blog.service.impl;

import com.sleepymango.blog.repository.ArticleRepository;
import com.sleepymango.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description
 * @Author sleepymango
 * @Date 2021-03-24 01:50:51
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
}