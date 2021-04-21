package com.sleepymango.blog.service.impl;

import com.querydsl.core.types.Projections;
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
import java.util.Objects;
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

    /**
     * 获取树状分类列表
     * @return
     */
    @Override
    public List<CategoryDTO> findAll() {
        QCategory c1 = new QCategory("c1");
        QCategory c2 = new QCategory("c2");
        QArticle qArticle = QArticle.article;

        List<CategoryDTO> list = jpaQueryFactory.select(Projections.bean(
                CategoryDTO.class,
                c1.id, c1.name, c1.parentId, qArticle.categoryId.count().intValue().as("count")
        ))
                .from(c1)
                .leftJoin(c2).on(c1.parentId.eq(c2.id))
                .leftJoin(qArticle).on(c1.id.eq(qArticle.categoryId))
                .groupBy(c1.id).fetch();

        // 返回给页面的list
        return list.stream()
                .filter(d -> null == d.getParentId())
                .peek(d -> {
                    List<CategoryDTO> children = getChildren(list, d);
                    int count = 0;
                    for (CategoryDTO child : children) {
                        count+=child.getCount();
                    }
                    d.setChildren(children);
                    d.setCount(count);
                })
                .collect(Collectors.toList());
    }

    /**
     * 删除分类，先删除子分类再删除该分类
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Long id) {
        categoryRepository.deleteByParentId(id);
        categoryRepository.deleteById(id);
    }

    @Override
    public Long sava(Category category) {
        return categoryRepository.save(category).getId();
    }

    /**
     * stream  递归 , map peek 区别是map 需要返回值
     *
     * @param list
     * @param dto
     * @return
     */
    private List<CategoryDTO> getChildren(List<CategoryDTO> list, CategoryDTO dto) {
        return list.stream()
                .filter(d -> Objects.equals(dto.getId(), d.getParentId()))
                .peek(d -> d.setChildren(getChildren(list, d))).collect(Collectors.toList());
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(1L).orElse(null);
    }

}