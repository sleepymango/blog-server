package com.sleepymango.blog.controller;

import com.sleepymango.blog.common.Result;
import com.sleepymango.blog.common.ResultCode;
import com.sleepymango.blog.dto.ArchiveArticle;
import com.sleepymango.blog.dto.Archives;
import com.sleepymango.blog.dto.ArticleDTO;
import com.sleepymango.blog.entity.Article;
import com.sleepymango.blog.exception.RedisException;
import com.sleepymango.blog.service.ArticleService;
import com.sleepymango.blog.utils.RedisDelayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Description:
 * @Author:
 * @Date: 2021-03-31 13:19:58
 */
@RestController
public class ArticleController {
    private final static int TO_BE_RELEASED = 2;

    private final ArticleService articleService;

    @Autowired
    private RedisDelayUtil redisDelayUtil;

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
        Date publish = article.getPublish();
        Date now = new Date();
        if (null != publish) {
            if (publish.before(new Date()) || 0 == publish.compareTo(now)) {
                article.setStatus(1);
            }
            if (publish.after(new Date())) {
                article.setStatus(2);
            }
        } else {
            article.setPublish(new Date());
        }

        Article save = articleService.save(article);
        // 将文章信息存入redis
        if (TO_BE_RELEASED == article.getStatus()) {
            try {
                redisDelayUtil.push(save);
            } catch (Exception e) {
                throw new RedisException(500, "服务器发生错误，定时发布文章失败");
            }
        }

        return new Result(ResultCode.SUCCESS.getStatusCode(), ResultCode.SUCCESS.getMessage(), save.getId());
    }

    /**
     * 每秒轮询一次，将到期的文章状态修改为已发布
     */
    @Scheduled(cron = "*/1 * * * * ?")
    public void publish() {
        Set<Long> articleIds = redisDelayUtil.pull();
        if (null != articleIds && !articleIds.isEmpty()) {
            int rows = articleService.updateStatus(articleIds);
            if (0 < rows) {
                for (Long articleId : articleIds) {
                    redisDelayUtil.remove(articleId);
                }
            }
        }
    }

    /**
     * 更新文章
     *
     * @param article
     * @return
     */
    @PutMapping("/articles")
    public Result update(@RequestBody Article article) {
        long articleId = articleService.save(article).getId();
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
