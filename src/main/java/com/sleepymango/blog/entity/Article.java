package com.sleepymango.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
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
    private Long userId;

    /**
     * 分类ID
     */
    @Column(name = "category_id")
    private Long categoryId;

    /**
     * 文章标签, 多对多，article为主表 ,序列化Label时忽略articles字段 ，由文章维护关系,不设置级联删除
     */
    @JsonIgnoreProperties(value = "articles")
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "article_label", joinColumns = @JoinColumn(name = "article_article_id"),
            inverseJoinColumns = @JoinColumn(name = "label_label_id"))
    private List<Label> labels;
//    /**
//     * 作者，optional=false,表示author不能为空。删除文章，不影响用户
//     */
//    @JsonIgnoreProperties(value = {"articles","comments"})
//    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.LAZY,optional = false)
//    @JoinColumn(name = "user_id")
//    private User author;
//
//    /**
//     * 文章分类，维护方
//     */
//    @JsonIgnoreProperties(value = {"articles","children"})
//    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.LAZY,optional = false)
//    @JoinColumn(name = "category_id")
//    private Category category;

}
