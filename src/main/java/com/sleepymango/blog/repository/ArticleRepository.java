package com.sleepymango.blog.repository;

import com.sleepymango.blog.entity.Article;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

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

    /**
     * 将文章状态改为已发布状态
     * @param ids
     */
    @Modifying
    @Query("update Article a set a.status = 1 where a.id in (:ids)")
    int updateStatus(@Param("ids") Set<Long> ids);
}
