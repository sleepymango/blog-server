package com.sleepymango.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @Author sleepymango
 * @Date 2021-03-31 01:10:33
 */

@Entity
@Data
@ToString
@Table(name = "label")
public class Label implements Serializable {

    private static final long serialVersionUID = 5994973623776930980L;

    /**
     * 标签ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "label_id")
    private Long id;

    /**
     * 标签名
     */
    @Column(name = "label_name")
    private String name;

    /**
     * 标签别名
     */
    @Column(name = "label_alias")
    private String alias;

    /**
     * 标签对应的文章列表
     */
    @JsonIgnore
    @ManyToMany(mappedBy = "labels", cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private List<Article> articles;
}
