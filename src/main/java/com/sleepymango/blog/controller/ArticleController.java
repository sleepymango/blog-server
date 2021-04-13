package com.sleepymango.blog.controller;

import com.sleepymango.blog.common.Result;
import com.sleepymango.blog.common.ResultEnum;
import com.sleepymango.blog.dto.Archives;
import com.sleepymango.blog.dto.ArticleDTO;
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
     * @param page 页码
     * @param pageSize 页面数据量
     * @return
     */
    @GetMapping("/articles")
    public Result findAll(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                          @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                          @RequestParam(value = "like", required = false, defaultValue = "") String like) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);
        PageImpl<ArticleDTO> pageInfo = articleService.findPage(pageRequest, like);
        return new Result(ResultEnum.SUCCESS.getStatusCode(), ResultEnum.SUCCESS.getMessage(), pageInfo);
    }

    /**
     * 文章根据发布日期归档
     * @return
     */
    @GetMapping("/articles/publish")
    public Result  findAllByPublish(){
        List<Archives> archives = articleService.findAllByPublish();
        return new Result(ResultEnum.SUCCESS.getStatusCode(), ResultEnum.SUCCESS.getMessage(), archives);
    }

    /**
     * 根据id获取文章
     * @param id
     * @return
     */
    @GetMapping("/articles/{id}")
    public Result findOne(@PathVariable("id") Long id) {
        ArticleDTO articleDTO= articleService.findById(id);
        return new Result(ResultEnum.SUCCESS.getStatusCode(), ResultEnum.SUCCESS.getMessage(), articleDTO);
    }

    /**
     * 发布文章
     *
     * @param articleDTO
     */
    @PostMapping("/articles")
    public Result save(@RequestBody ArticleDTO articleDTO) {
        long articleId = articleService.save(articleDTO);
        return new Result(ResultEnum.SUCCESS.getStatusCode(), ResultEnum.SUCCESS.getMessage(), articleId);
    }

    /**
     * 更新文章
     *
     * @param articleDTO
     * @return
     */
    @PutMapping("/articles")
    public Result update(@RequestBody ArticleDTO articleDTO) {
        long articleId = articleService.save(articleDTO);
        return new Result(ResultEnum.SUCCESS.getStatusCode(), ResultEnum.SUCCESS.getMessage(), articleId);
    }

    /**
     * 彻底删除文章
     * @param id
     * @return
     */
    @DeleteMapping("/articles/{id}")
    public Result delete(@PathVariable Long id) {
        articleService.deleteById(id);
        return new Result(ResultEnum.SUCCESS.getStatusCode(), ResultEnum.SUCCESS.getMessage(), null);
    }

}
