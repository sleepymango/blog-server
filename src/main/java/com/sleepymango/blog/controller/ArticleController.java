package com.sleepymango.blog.controller;

import com.sleepymango.blog.common.Result;
import com.sleepymango.blog.common.ResultCode;
import com.sleepymango.blog.dto.ArchiveArticle;
import com.sleepymango.blog.dto.Archives;
import com.sleepymango.blog.dto.ArticleDTO;
import com.sleepymango.blog.entity.Article;
import com.sleepymango.blog.service.ArticleService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
     * 分页查询
     *
     * @return
     */
    @GetMapping("/articles")
    public Result findAll(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                          @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                          @RequestParam(value = "like", required = false, defaultValue = "") String like,
                          @RequestParam(value = "categoryId", required = false) Long categoryId) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);
        PageImpl<ArticleDTO> pageInfo = articleService.findPage(pageRequest, like, categoryId);
        return new Result(ResultCode.SUCCESS.getStatusCode(), ResultCode.SUCCESS.getMessage(), pageInfo);
    }

    /**
     * 文章根据发布日期归档
     *
     * @return
     */
    @GetMapping("/articles/yearMonth")
    public Result groupByYearMonth() {
        List<Archives> archives = articleService.groupByYearMonth();
        return new Result(ResultCode.SUCCESS.getStatusCode(), ResultCode.SUCCESS.getMessage(), archives);
    }

    /**
     * 文章根据发布年份归档
     *
     * @return
     */
    @GetMapping("/articles/year")
    public Result groupByYear() {
        List<ArchiveArticle> archives = articleService.groupByYear();
        return new Result(ResultCode.SUCCESS.getStatusCode(), ResultCode.SUCCESS.getMessage(), archives);
    }

    @GetMapping("/articles/recent")
    public Result findRecentArticles() {
        List<ArticleDTO> archives = articleService.findRecent();
        return new Result(ResultCode.SUCCESS.getStatusCode(), ResultCode.SUCCESS.getMessage(), archives);
    }


    /**
     * 根据id获取文章
     *
     * @param id
     * @return
     */
    @GetMapping("/articles/{id}")
    public Result findOne(@PathVariable("id") Long id) {
        ArticleDTO articleDTO = articleService.findById(id);
        return new Result(ResultCode.SUCCESS.getStatusCode(), ResultCode.SUCCESS.getMessage(), articleDTO);
    }


    /**
     * 根据id获取文章内容
     *
     * @param id
     * @return
     */
    @GetMapping("/articles/content/{id}")
    public Result findContentById(@PathVariable("id") Long id) {
        String content = articleService.findContentById(id);
        return new Result(ResultCode.SUCCESS.getStatusCode(), ResultCode.SUCCESS.getMessage(), content);
    }

    /**
     * 发布文章
     *
     * @param article
     */
    @PostMapping("/articles")
    public Result save(@RequestBody Article article) {
        long articleId = articleService.save(article);
        return new Result(ResultCode.SUCCESS.getStatusCode(), ResultCode.SUCCESS.getMessage(), articleId);
    }

    /**
     * 更新文章
     *
     * @param article
     * @return
     */
    @PutMapping("/articles")
    public Result update(@RequestBody Article article) {
        long articleId = articleService.save(article);
        return new Result(ResultCode.SUCCESS.getStatusCode(), ResultCode.SUCCESS.getMessage(), articleId);
    }

    /**
     * 彻底删除文章
     *
     * @param id
     * @return
     */
    @DeleteMapping("/articles/{id}")
    public Result delete(@PathVariable Long id) {
        articleService.deleteById(id);
        return new Result(ResultCode.SUCCESS.getStatusCode(), ResultCode.SUCCESS.getMessage(), null);
    }

}
