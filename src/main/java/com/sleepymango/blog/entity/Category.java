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
     * 父ID
     */
    @Column(name = "parent_id")
    private Long parentId;

}
