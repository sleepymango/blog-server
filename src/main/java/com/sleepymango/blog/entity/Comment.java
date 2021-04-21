package com.sleepymango.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Author sleepymango
 * @Date 2021-03-31 00:49:36
 */

@Entity
@Data
@ToString(exclude = "parent")
@Table(name = "comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 3978783489892079309L;

    /**
     * 评论的ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    /**
     * 评论内容
     */
    @Column(name = "comment_content")
    private String content;

    /**
     * 评论点赞数
     */
    @Column(name = "comment_like")
    private int like;

    /**
     * 评论时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "comment_publish")
    private Date publish;

    /**
     * 评论时间
     */
    @Column(name = "comment_from")
    private String from;

    /**
     * 评论的用户
     */
    @Column(name = "visitor_id")
    private Long visitorId;

    /**
     * 评论的文章
     */
    @Column(name = "article_id")
    private Long articleId;

    /**
     * 父Id
     */
    @Column(name = "parent_id")
    private Long parentId;
//    /**
//     * 父评论  many
//     */
//    @JsonIgnore
//    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
//    @JoinColumn(name = "parent_id")
//    private Comment parent;
//
//    /**
//     * 子评论 ，mappedBy关系被维护方 one
//     */
//    @JsonInclude(JsonInclude.Include.NON_EMPTY)
//    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
//    private List<Comment> children;
}
