package com.sleepymango.blog.service.impl;

import com.sleepymango.blog.entity.Category;
import com.sleepymango.blog.repository.CategoryRepository;
import com.sleepymango.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description
 * @Author sleepymango
 * @Date 2021-03-24 01:48:15
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAllByParentNull();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(1L).orElse(null);
    }
}