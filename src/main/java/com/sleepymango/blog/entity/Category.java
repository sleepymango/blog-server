package com.sleepymango.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    /**
     * 分类名
     */
    @Column(name = "category_name")
    private String name;

    /**
     * 父分类
     */
    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "parent_id")
    private Category parent;

    /**
     * 子分类  @JsonInclude(value = JsonInclude.Include.NON_EMPTY) 忽略空值
     */
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Category> children;

}
