package com.sleepymango.blog.service.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sleepymango.blog.dto.CategoryDTO;
import com.sleepymango.blog.entity.Category;
import com.sleepymango.blog.entity.QArticle;
import com.sleepymango.blog.entity.QCategory;
import com.sleepymango.blog.repository.CategoryRepository;
import com.sleepymango.blog.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author sleepymango
 * @Date 2021-03-24 01:48:15
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final JPAQueryFactory jpaQueryFactory;

    public CategoryServiceImpl(CategoryRepository categoryRepository, JPAQueryFactory jpaQueryFactory) {
        this.categoryRepository = categoryRepository;
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<CategoryDTO> findAll() {
        QCategory category = QCategory.category;
        QArticle article = QArticle.article;
        return jpaQueryFactory
                .select(
                category.id,
                category.name,
                category.parentId,
                article.id.count().intValue())
                .from(category)
                .leftJoin(article)
                .on(category.id.eq(article.categoryId))
                .groupBy(category.id)
                .fetch().stream().map(tuple -> {
                    CategoryDTO categoryDTO = new CategoryDTO();
                    categoryDTO.setId(tuple.get(category.id));
                    categoryDTO.setName(tuple.get(category.name));
                    categoryDTO.setParentId(tuple.get(category.parentId));
                    categoryDTO.setCount(tuple.get(article.id.count().intValue()));
                    return categoryDTO;
                }).collect(Collectors.toList());
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(1L).orElse(null);
    }
}