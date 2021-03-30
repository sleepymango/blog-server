package com.sleepymango.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName Category
 * @Description
 * @Author sleepymango
 * @Date 2021-03-24 01:46:44
 */

@Entity
@Data
@ToString
@Table(name = "category")
public class Category implements Serializable {

    private static final long serialVersionUID = 6883086858105167771L;

    /**
     * 分类ID
     */
    @Id
    @Column(name = "category_id")
    private Long id;

    /**
     * 分类名
     */
    @Column(name = "category_name")
    private String name;

    /**
     * 分类别名
     */
    @Column(name = "category_alias")
    private String alias;

    /**
     * 分类描述
     */
    @Column(name = "category_description")
    private String description;

    /**
     * 父分类
     */
    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "parent_id")
    private Category parent;

    /**
     * 子分类
     */
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Category> children;

    /**
     * 文章列表
     */
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Article> articles;
}
