package com.sleepymango.blog.controller;

import com.sleepymango.blog.common.Result;
import com.sleepymango.blog.common.ResultEnum;
import com.sleepymango.blog.entity.Category;
import com.sleepymango.blog.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description
 * @Author
 * @Date 2021-03-24 01:48:15
 */
@RestController
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * 分类列表
     * @return Result
     */
    @GetMapping("/categories")
    public Result findAll() {
        List<Category> categoryList = categoryService.findAll();
        return new Result(ResultEnum.SUCCESS.getStatusCode(),ResultEnum.SUCCESS.getMessage(), categoryList);
    }

    @GetMapping("/categories/{id}")
    public Result findOne(@PathVariable("id") Long id) {
        Category category = categoryService.findById(id);
        return new Result(ResultEnum.SUCCESS.getStatusCode(),ResultEnum.SUCCESS.getMessage(), category);
    }


    /**
     * @param
     */
    @PostMapping("/category")
    public void save() {
    }

    @PutMapping("/category")
    public void update() {
    }


    @DeleteMapping("/category")
    public void delete(@PathVariable Integer id) {
    }

}
