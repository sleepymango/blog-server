package com.sleepymango.blog.repository;

import com.sleepymango.blog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description
 * @Author sleepymango
 * @Date 2021-03-24 01:48:15
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    void deleteByParentId(Long id);
}