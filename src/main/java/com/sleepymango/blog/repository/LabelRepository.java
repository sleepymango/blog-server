package com.sleepymango.blog.repository;

import com.sleepymango.blog.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Author sleepymango
 * @Date 2021-03-31 02:54:37
 */
@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {
}