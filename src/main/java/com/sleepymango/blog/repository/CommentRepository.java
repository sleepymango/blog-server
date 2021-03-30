package com.sleepymango.blog.repository;

import com.sleepymango.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Author sleepymango
 * @Date 2021-03-31 02:54:17
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}