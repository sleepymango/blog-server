package com.sleepymango.blog.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName Category
 * @Description
 * @Author sleepymango
 * @Date 2021-03-24 01:46:44
 */
@Entity
@Data
@ToString  //(exclude = "parent")
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
     * 父ID
     */
    @Column(name = "parent_id")
    private Long parentId;

//    /**
//     * 父分类
//     */
//    @JsonInclude(value = JsonInclude.Include.NON_NULL)
//    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
//    @JoinColumn(name = "parent_id")
//    private Category parent;

//    /**
//     * 子分类  @JsonInclude(value = JsonInclude.Include.NON_EMPTY) 忽略空值
//     */
//    @JsonIgnoreProperties(value = "parent")
//    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
//    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Category> children;
}
