package com.sleepymango.blog.service;

import com.sleepymango.blog.dto.ArchiveArticle;
import com.sleepymango.blog.dto.Archives;
import com.sleepymango.blog.dto.ArticleDTO;
import com.sleepymango.blog.entity.Article;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Description
 * @Author sleepymango
 * @Date 2021-03-24 01:50:51
 */
public interface ArticleService {

    /**
     * 查找所有文章
     * @return 文章列表
     */
    List<Article> findAll();

    /**
     * 保存文章
     * @param article 接收参数
     * @return 文章id
     */
    long save(Article article);

    /**
     * 查找分页,
     * @param pageable 分页信息
     * @param like 模糊查询
     * @return 分页数据
     */
    PageImpl<ArticleDTO> findPage(Pageable pageable, String like,Long categoryId);

    /**
     * 根据ID删除文章
     * @param id 文章id
     */
    void deleteById(Long id);

    /**
     * 归档
     * @return
     */
    List<Archives> groupByYearMonth();

    /**
     * 根据id获取文章
     * @param id
     * @return
     */
    ArticleDTO findById(Long id);

    List<ArticleDTO> findRecent();

    String findContentById(Long id);

    List<ArchiveArticle> groupByYear();
}