package com.sleepymango.blog.service;

import com.sleepymango.blog.entity.Label;

import java.util.List;

/**
 * @Description:
 * @Author sleepymango
 * @Date 2021-03-31 02:54:37
 */
public interface LabelService {

    List<Label> findAll();
}