package com.sleepymango.blog.repository;

import com.sleepymango.blog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author sleepymango
 * @Date 2021-03-24 01:48:15
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    /**
     * 查找没有parentId的分类
     * @return
     */
    List<Category> findAllByParentNull();
}