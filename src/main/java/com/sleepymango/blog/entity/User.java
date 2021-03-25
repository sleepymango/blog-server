package com.sleepymango.blog.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Author sleepymango
 * @Date 2021-03-23 17:53:44
 */

@Entity
@Data
@ToString
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 490842700338594450L;

    /**
     * 用户ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自定义主键生成策略，IDENTITY 主键由数据库生成
    @Column(name = "user_id")
    private Long userId;

    /**
     * 用户登录名
     */
    @Column(name = "user_login")
    private String userLogin;

    /**
     * 用户密码
     */
    @Column(name = "user_pass")
    private String userPass;

    /**
     * 用户邮箱
     */
    @Column(name = "user_email")
    private String userEmail;

    /**
     * 用户昵称
     */
    @Column(name = "user_nickname")
    private String userNickname;

    /**
     * 注册时间
     */
    @Column(name = "user_registered")
    private Date userRegistered;

    /**
     * 用户头像
     */
    @Column(name = "user_avatar")
    private String userAvatar;

    /**
     * 用户IP
     */
    @Column(name = "user_ip")
    private String userIp;

    /**
     * 用户状态 "0"删除 ”1“正常
     */
    @Column(name = "user_status")
    private Long userStatus;
}
