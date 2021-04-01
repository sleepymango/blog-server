package com.sleepymango.blog.controller;
import com.sleepymango.blog.common.Result;
import com.sleepymango.blog.common.ResultEnum;
import com.sleepymango.blog.entity.Article;
import com.sleepymango.blog.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Author:
 * @Date: 2021-03-31 13:19:58
 */
@RestController
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     *
     */
    @GetMapping("/articles")
    public Result findAll() {
        List<Article> articleList = articleService.findAll();
        return new Result(ResultEnum.SUCCESS.getStatusCode(), ResultEnum.SUCCESS.getMessage(), articleList);
    }


    /**
     * @param
     */
    @PostMapping("/articles")
    public void save() {
    }

    @PutMapping("/article")
    public void update() {
    }


    @DeleteMapping("/article")
    public void delete(@PathVariable Long id) {
    }

}
