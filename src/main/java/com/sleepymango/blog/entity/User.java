package com.sleepymango.blog.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: User
 * @Description:
 * @Author: sleepymango
 * @Date: 2021-03-28 20:25:40
 */

@Entity
@Data
@ToString
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1845302188817185889L;

    /**
     * 用户ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    /**
     * 用户登录名
     */
    @Column(name = "username")
    private String username;

    /**
     * 用户密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 用户邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 用户昵称
     */
    @Column(name = "nickname")
    private String nickname;

    /**
     * 注册时间
     */
    @Column(name = "registered")
    private Date registered;

    /**
     * 用户头像
     */
    @Column(name = "avatar")
    private String avatar;

    /**
     * 用户IP
     */
    @Column(name = "ip")
    private String ip;

    /**
     * 用户状态 "0"删除 ”1“正常
     */
    @Column(name = "status")
    private Long status;

    /**
     * //级联保存、更新、删除、刷新;延迟加载。当删除用户，会级联删除该用户的所有文章
     * //拥有mappedBy注解的实体类为关系被维护端
     * //mappedBy="author"中的author是Article中的author属性
     */
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Article> articles;

    /**
     * 用户评论
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

}
