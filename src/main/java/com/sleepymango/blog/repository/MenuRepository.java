package com.sleepymango.blog.repository;

import com.sleepymango.blog.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author sleepymango
 * @Date 2021-03-29 23:30:51
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    /**
     * 查找没有parentId的分类
     * @return List<Menu>
     */
    List<Menu> findAllByParentNullAndStatusIsNot(Integer status);
}