package com.sleepymango.blog.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Author sleepymango
 * @Date 2021-03-23 17:28:36
 */

@Entity
@Data
@ToString
@Table(name = "article")
public class Article implements Serializable {

    private static final long serialVersionUID = 2628365592322942349L;

    /**
     * 文章ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long articleId;

    /**
     * 文章标题
     */
    @Column(name = "article_title")
    private String articleTitle;

    /**
     * 文章内容
     */
    @Column(name = "article_content")
    private String articleContent;

    /**
     * 文章封面
     */
    @Column(name = "article_banner")
    private String articleBanner;

    /**
     * 文章标签
     */
    @Column(name = "article_tag")
    private String articleTag;

    /**
     * 浏览量
     */
    @Column(name = "article_view")
    private Long articleView;

    /**
     * 评论数
     */
    @Column(name = "article_comment")
    private Long articleComment;

    /**
     * 点赞数
     */
    @Column(name = "article_like")
    private Long articleLike;

    /**
     * 发布时间
     */
    @Column(name = "article_publish")
    private Date articlePublish;

    /**
     * 文章状态 "0"删除 ”1“正常
     */
    @Column(name = "article_status")
    private String articleStatus;

    /**
     * 关联的发布用户ID
     */
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * 分类ID
     */
    @Column(name = "category_id")
    private Long categoryId;
}
