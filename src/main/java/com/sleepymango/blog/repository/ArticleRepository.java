package com.sleepymango.blog.repository;

import com.sleepymango.blog.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description
 * @Author sleepymango
 * @Date 2021/3/23 17:47
 **/

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {
}
