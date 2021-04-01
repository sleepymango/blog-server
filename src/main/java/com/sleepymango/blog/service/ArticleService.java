package com.sleepymango.blog.service;

import com.sleepymango.blog.entity.Article;

import java.util.List;

/**
 * @Description
 * @Author sleepymango
 * @Date 2021-03-24 01:50:51
 */
public interface ArticleService {

    List<Article> findAll();
}