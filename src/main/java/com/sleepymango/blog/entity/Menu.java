package com.sleepymango.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: Menu
 * @Description:
 * @Author: sleepymango
 * @Date: 2021-03-29 23:19:20
 */

@Entity
@Data
@ToString(exclude = "parent") // 需要排除父或子项属性，否则会栈溢出报错
@Table(name = "menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 2127528730387115543L;

    /**
     * 菜单ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long id;

    /**
     * 菜单名
     */
    @Column(name = "menu_name")
    private String name;

    /**
     * 菜单URL
     */
    @Column(name = "menu_url")
    private String url;

    /**
     * 菜单状态
     */
    @Column(name = "menu_status")
    private int status;

    /**
     * 父菜单,option默认为true，允许为null，  @JsonIgnore // 防止无限循环
     */
    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "parent_id")
    private Menu parent;

    /**
     * 子菜单
     */
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Menu> children;

}
