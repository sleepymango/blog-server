package com.sleepymango.blog.controller;

import com.sleepymango.blog.annotation.VerifyToken;
import com.sleepymango.blog.common.Result;
import com.sleepymango.blog.common.ResultCode;
import com.sleepymango.blog.dto.CategoryDTO;
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
     *
     * @return Result
     */

    @GetMapping("/categories")
    public Result findAll() {
        List<CategoryDTO> list = categoryService.findAll();
        return new Result(ResultCode.SUCCESS.getStatusCode(), ResultCode.SUCCESS.getMessage(), list);
    }

    /**
     * 根据id查询分类
     * @param id
     * @return
     */
    @GetMapping("/categories/{id}")
    public Result findOne(@PathVariable("id") Long id) {
        Category category = categoryService.findById(id);
        return new Result(ResultCode.SUCCESS.getStatusCode(), ResultCode.SUCCESS.getMessage(), category);
    }

    /**
     * 添加分类
     * @param category
     * @return
     */
    @VerifyToken
    @PostMapping("/categories")
    public Result save(@RequestBody Category category) {
        Long id = categoryService.sava(category);
        return new Result(ResultCode.SUCCESS.getStatusCode(), ResultCode.SUCCESS.getMessage(), id);
    }

    @PutMapping("/category")
    public void update() {
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @VerifyToken
    @DeleteMapping("/categories/{id}")
    public Result delete(@PathVariable Long id) {
        categoryService.delete(id);
        return new Result(ResultCode.SUCCESS.getStatusCode(), ResultCode.SUCCESS.getMessage(), null);
    }

}
