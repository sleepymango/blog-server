package com.sleepymango.blog.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Description  
 * @Author  linmengmeng
 * @Date 2021-03-31 01:07:11 
 */

@Entity
@Data
@ToString
@Table ( name ="article")
public class Article  implements Serializable {

    private static final long serialVersionUID = 6479207600191544602L;

    /**
     * 文章ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    /**
     * 文章标题
     */
    @Column(name = "article_title")
    private String title;

    /**
     * 文章内容
     */
    @Column(name = "article_content")
    private String content;

    /**
     * 文章封面
     */
    @Column(name = "article_banner")
    private String banner;

    /**
     * 浏览量
     */
    @Column(name = "article_view")
    private Long view;

    /**
     * 评论数
     */
    @Column(name = "article_comment")
    private Long comment;

    /**
     * 点赞数
     */
    @Column(name = "article_like")
    private Long like;

    /**
     * 发布时间
     */
    @Column(name = "article_publish")
    private Date publish;

    /**
     * 文章状态 "0"删除 ”1“正常
     */
    @Column(name = "article_status")
    private String status;

    /**
     * 作者，optional=false,表示author不能为空。删除文章，不影响用户
     */
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id")
    private User author;

    /**
     * 文章分类，维护方
     */
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

    /**
     * 文章标签, article为主表
     */
    @ManyToMany
    private List<Label> labels;

    /**
     * 文章评论
     */
    @OneToMany(mappedBy = "article",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Comment> comments;
}
