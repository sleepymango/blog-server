package com.sleepymango.blog.repository;

import com.sleepymango.blog.entity.Article;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author sleepymango
 * @Date 2021/3/23 17:47
 **/

@Repository
public interface ArticleRepository extends BaseRepository<Article,Long> {
    /**
     * 查询状态不为0的文章
     * @param status
     * @return
     */
    List<Article> findAllByStatusNot(int status);
}
