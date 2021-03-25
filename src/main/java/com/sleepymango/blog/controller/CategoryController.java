package com.sleepymango.blog.controller;

import com.sleepymango.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Author
 * @Date 2021-03-24 01:48:15
 */
@RestController
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     *
     */
    @GetMapping("/category")
    public void findAll() {
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
