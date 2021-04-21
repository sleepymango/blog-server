package com.sleepymango.blog.service.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sleepymango.blog.dto.Mine;
import com.sleepymango.blog.entity.QArticle;
import com.sleepymango.blog.entity.QCategory;
import com.sleepymango.blog.entity.QLabel;
import com.sleepymango.blog.entity.QUser;
import com.sleepymango.blog.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description CommonServiceImpl
 * @Author sleepymango
 * @Date 2021/4/11 2:21
 **/
@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Mine getMineInfo() {
        QArticle qArticle = QArticle.article;
        QCategory qCategory = QCategory.category;
        QLabel qLabel = QLabel.label;
        QUser qUser = QUser.user;
        Integer articles = jpaQueryFactory.select(qArticle.count().intValue()).from(qArticle).fetchOne();
        Integer categories = jpaQueryFactory.select(qCategory.count().intValue()).from(qCategory).fetchOne();
        Integer labels = jpaQueryFactory.select(qLabel.count().intValue()).from(qLabel).fetchOne();
        String name = jpaQueryFactory.select(qUser.name).from(qUser).where(qUser.id.eq(2L)).fetchOne();

        Mine mine = new Mine();
        mine.setArticles(articles);
        mine.setCategories(categories);
        mine.setLabels(labels);
        mine.setName(name);
//        Mine mine = jpaQueryFactory
//                .select(Projections.bean(
//                        Mine.class,
//                        qArticle.count().intValue().as("articles"),
//                        qCategory.count().intValue().as("categories"),
//                        qLabel.count().intValue().as("labels")
//                ))
//                .from(qArticle, qCategory, qLabel).where(qCategory.parentId.isNotNull()).fetchOne();
        return mine;
    }
}
