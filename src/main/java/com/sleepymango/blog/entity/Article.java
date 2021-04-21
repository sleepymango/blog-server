package com.sleepymango.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author sleepymango
 * @Date 2021-03-31 01:07:11
 */
@Entity
@Data
@ToString
@Table(name = "article")
public class Article implements Serializable {

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
    private int view;

    /**
     * 评论数
     */
    @Column(name = "article_comment")
    private int comment;

    /**
     * 点赞数
     */
    @Column(name = "article_like")
    private int like;

    /**
     * 发布时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "article_publish")
    private Date publish;

    /**
     * 文章状态 "0"删除 ”1“正常
     */
    @Column(name = "article_status")
    private int status;

    /**
     * 作者ID
     */
    @Column(name = "user_id")
    private Long authorId;

    /**
     * 分类ID
     */
    @Column(name = "category_id")
    private Long categoryId;

    /**
     * 文章标签
     * 注意：添加文章时如果设置的标签是存在于数据库的，那么cascade不能是PERSIST，应该是MERGE和REFRESH
     * 反之如果标签时新建的，应该用PERSIST,不能同时用
     */
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "article_label", joinColumns = @JoinColumn(name = "article_article_id"),
            inverseJoinColumns = @JoinColumn(name = "label_label_id"))
    private List<Label> labels;

}
