package com.sleepymango.blog.service.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sleepymango.blog.dto.ArchiveArticle;
import com.sleepymango.blog.dto.Archives;
import com.sleepymango.blog.dto.ArticleDTO;
import com.sleepymango.blog.entity.*;
import com.sleepymango.blog.repository.ArticleRepository;
import com.sleepymango.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author sleepymango
 * @Date 2021-03-24 01:50:51
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;


    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PageImpl<ArticleDTO> findPage(Pageable pageable, String like, Long categoryId) {
        System.out.println(like);

        QArticle qArticle = QArticle.article;
        QUser qUser = QUser.user;
        QCategory qCategory = QCategory.category;
        Set<Long> ids = new HashSet<>();
        if (null != categoryId) {
            Category category = jpaQueryFactory.select(qCategory).from(qCategory).where(qCategory.id.eq(categoryId)).fetchOne();
            ids.add(categoryId);
            if (null == category.getParentId()) {
                List<Long> list = jpaQueryFactory.select(qCategory.id).from(qCategory).where(qCategory.parentId.eq(categoryId)).fetch();
                ids.addAll(list);
            }

        }
        JPAQuery<Tuple> query = jpaQueryFactory.select(
                qArticle, qUser, qCategory
        ).from(qArticle);

        if (null != ids && !ids.isEmpty()) {
            query
                    .leftJoin(qUser).on(qArticle.authorId.eq(qUser.id))
                    .leftJoin(qCategory).on(qArticle.categoryId.eq(qCategory.id))
                    .where(qArticle.title.contains(like).and(qArticle.categoryId.in(ids)))
                    .offset(pageable.getOffset()).limit(pageable.getPageSize());
        } else {
            query.leftJoin(qUser).on(qArticle.authorId.eq(qUser.id))
                    .leftJoin(qCategory).on(qArticle.categoryId.eq(qCategory.id))
                    .where(qArticle.title.contains(like))
                    .offset(pageable.getOffset()).limit(pageable.getPageSize());
        }

        QueryResults<Tuple> queryResults = query.fetchResults();
        List<ArticleDTO> list = queryResults.getResults().stream().map(tuple -> {
            ArticleDTO articleDTO = new ArticleDTO();
            articleDTO.setId(Objects.requireNonNull(tuple.get(qArticle)).getId());
            articleDTO.setTitle(Objects.requireNonNull(tuple.get(qArticle)).getTitle());
            // 截取文章内容第一段
            String[] split = Objects.requireNonNull(tuple.get(qArticle)).getContent().split("[\r\n]");
            articleDTO.setContent(split[0]);
            articleDTO.setBanner(Objects.requireNonNull(tuple.get(qArticle)).getBanner());
            articleDTO.setPublish(Objects.requireNonNull(tuple.get(qArticle)).getPublish());
            articleDTO.setStatus(Objects.requireNonNull(tuple.get(qArticle)).getStatus());
            articleDTO.setAuthor(Objects.requireNonNull(tuple.get(qUser)).getName());
            articleDTO.setAuthorId(Objects.requireNonNull(tuple.get(qUser)).getId());
            articleDTO.setCategoryId(Objects.requireNonNull(tuple.get(qCategory)).getId());
            articleDTO.setCategory(Objects.requireNonNull(tuple.get(qCategory)).getName());
            articleDTO.setLabels(Objects.requireNonNull(tuple.get(qArticle)).getLabels());
            return articleDTO;
        }).collect(Collectors.toList());
        long total = queryResults.getTotal();

        return new PageImpl<>(list, pageable, total);
    }

    @Override
    public String findContentById(Long id) {
        QArticle qArticle = QArticle.article;
        return jpaQueryFactory.select(qArticle.content).from(qArticle).where(qArticle.id.eq(id)).fetchOne();
    }

    @Override
    public List<ArticleDTO> findRecent() {
        QArticle qArticle = QArticle.article;
        QCategory qCategory = QCategory.category;

        return jpaQueryFactory.select(Projections.bean(
                ArticleDTO.class,
                qArticle.title, qArticle.banner, qArticle.publish, qCategory.name.as("category")
        )).from(qArticle).leftJoin(qCategory).on(qArticle.categoryId.eq(qCategory.id))
                .orderBy(qArticle.publish.desc()).limit(5).offset(0).fetch();
    }

    @Override
    public ArticleDTO findById(Long id) {
        QArticle qArticle = QArticle.article;
        QUser qUser = QUser.user;
        QCategory qCategory = QCategory.category;
        return jpaQueryFactory.select(qArticle, qUser, qCategory)
                .from(qArticle).leftJoin(qUser).on(qArticle.authorId.eq(qUser.id))
                .leftJoin(qCategory).on(qArticle.categoryId.eq(qCategory.id))
                .where(qArticle.id.eq(id))
                .fetchResults().getResults().stream().map(tuple -> {
                    ArticleDTO articleDTO = new ArticleDTO();
                    articleDTO.setId(Objects.requireNonNull(tuple.get(qArticle)).getId());
                    articleDTO.setTitle(Objects.requireNonNull(tuple.get(qArticle)).getTitle());
                    articleDTO.setContent(Objects.requireNonNull(tuple.get(qArticle)).getContent());
                    articleDTO.setBanner(Objects.requireNonNull(tuple.get(qArticle)).getBanner());
                    articleDTO.setPublish(Objects.requireNonNull(tuple.get(qArticle)).getPublish());
                    articleDTO.setStatus(Objects.requireNonNull(tuple.get(qArticle)).getStatus());
                    articleDTO.setAuthor(Objects.requireNonNull(tuple.get(qUser)).getName());
                    articleDTO.setCategoryId(Objects.requireNonNull(tuple.get(qCategory)).getId());
                    articleDTO.setCategory(Objects.requireNonNull(tuple.get(qCategory)).getName());
                    articleDTO.setLabels(Objects.requireNonNull(tuple.get(qArticle)).getLabels());
                    return articleDTO;
                }).findFirst().orElse(null);
    }

    @Override
    public List<Archives> groupByYearMonth() {
        QArticle qArticle = QArticle.article;
        return jpaQueryFactory.from(qArticle)
                .orderBy(qArticle.publish.desc())
                .groupBy(qArticle.publish.yearMonth())
                .transform(GroupBy.groupBy(qArticle.publish.yearMonth()).list(Projections.bean(
                        Archives.class,
                        qArticle.publish, qArticle.count().intValue().as("count")
                )));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateStatus(Set<Long> articleId) {
        return articleRepository.updateStatus(articleId);
    }

    @Override
    public List<ArchiveArticle> groupByYear() {
        QArticle qArticle = QArticle.article;
        QCategory qCategory = QCategory.category;
        return jpaQueryFactory.from(qArticle)
                .leftJoin(qCategory).on(qArticle.categoryId.eq(qCategory.id))
                .orderBy(qArticle.publish.desc())
//                .groupBy(qArticle.publish.yearMonth())
                .transform(GroupBy.groupBy(qArticle.publish.year()).list(Projections.bean(
                        ArchiveArticle.class,
                        qArticle.publish,
                        GroupBy.list(Projections.bean(
                                ArticleDTO.class,
                                qArticle.title,qArticle.banner,qArticle.publish,qCategory.name.as("category")
                        )).as("articles")
                )));
    }

    @Override
    public void deleteById(Long id) {
        articleRepository.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Article save(Article article) {
        return articleRepository.save(article);
    }

    public void test() {
//        QArticle article = QArticle.article;
//        QUser user = QUser.user;
//        QCategory category = QCategory.category;
//        QArticleLabel articleLabel = QArticleLabel.articleLabel;
//        QLabel label = QLabel.label;
//        List<ArticleDTO> list = jpaQueryFactory.selectFrom
//                (article
////                article,user,
////                Projections.bean(
////                ArticleDTO.class,
////                article.id, article.title, article.content, article.banner, article.publish, article.status,
////                user.name.as("author"),
////                category.id.as("categoryId"), category.name.as("category")
////        )
//                )
////                .from(article)
//                .leftJoin(user).on(article.authorId.eq(user.id))
//                .leftJoin(category).on(article.categoryId.eq(category.id))
//                .leftJoin(articleLabel).on(article.id.eq(articleLabel.articleArticleId))
//                .leftJoin(label).on(articleLabel.labelLabelId.eq(label.id))
//                .orderBy(article.publish.desc())
////                .offset(0).limit(3)
//                .fetchAll()
//                // 结果集映射
//                .transform(GroupBy.groupBy(article.id).list(Projections.bean(
//                        ArticleDTO.class,
//                        article.id, article.title, article.content, article.banner, article.publish, article.status,
////                        Projections.bean(Label.class, GroupBy.list(label)).as("labels"),
////                        GroupBy.groupBy(label.id)
//                        GroupBy.list(label).as("labels"),
////                        GroupBy.list(article.labels).as("labels"),
////                        article.labels.as("labels"),
//                        user.name.as("author"),
//                        category.id.as("categoryId"), category.name.as("category")
//                )));
//
//        for (ArticleDTO articleDTO : list) {
//            System.out.println(articleDTO);
//        }

//        Group group = groupList.get(1);
//        Set<Article> list = group.getSet(article);
//        System.out.println(list);
//        Set<Map.Entry<Long, List<Label>>> entries = map.entrySet();
//        for (Map.Entry<Long, List<Label>> entry : entries) {
//            System.out.println(entry.getKey());
//            System.out.println(entry.getValue());
//        }
    }
}