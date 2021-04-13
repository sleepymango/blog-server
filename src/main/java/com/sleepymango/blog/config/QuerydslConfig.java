package com.sleepymango.blog.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

/**
 * @Description QuerydslConfig
 * @Author sleepymango
 * @Date 2021/4/4 0:53
 **/

@Configuration
public class QuerydslConfig {
    private final EntityManager entityManager;

    public QuerydslConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
