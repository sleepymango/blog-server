package com.sleepymango.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @Description BaseRepository
 * @Author sleepymango
 * @Date 2021/4/4 0:40
 **/

@NoRepositoryBean
public interface BaseRepository<T,ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T>,QuerydslPredicateExecutor<T> {
}
