package com.sleepymango.blog.service;

import com.sleepymango.blog.dto.CategoryDTO;
import com.sleepymango.blog.entity.Category;

import java.util.List;

/**
 * @Description
 * @Author sleepymango
 * @Date 2021-03-24 01:48:15
 */
public interface CategoryService {

    /**
     * 获取分类列表
     * @return
     */
    List<CategoryDTO> findAll();

    Category findById(Long id);

    Long save(Category category);

    void delete(Long id);
}