package com.sleepymango.blog.service;

import com.sleepymango.blog.entity.Visitor;

/**
 * @Description:
 * @Author sleepymango
 * @Date 2021-04-19 16:16:40
 */
public interface VisitorService {

    Visitor findByNameAndEmail(String name,String email);
}