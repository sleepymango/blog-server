package com.sleepymango.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: User
 * @Description:
 * @Author: sleepymango
 * @Date: 2021-03-28 20:25:40
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
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
    private Long id;

    /**
     * 用户登录名
     */
    @Column(name = "user_name")
    private String name;

    /**
     * 用户密码
     */
    @Column(name = "user_password")
    private String password;

    /**
     * 用户邮箱
     */
    @Column(name = "user_email")
    private String email;

    /**
     * 用户昵称
     */
    @Column(name = "user_nickname")
    private String nickname;

    /**
     * 注册时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "user_registered")
    private Date registered;

    /**
     * 用户头像
     */
    @Column(name = "user_avatar")
    private String avatar;

    /**
     * 用户IP
     */
    @Column(name = "user_ip")
    private String ip;

    /**
     * 用户状态 "0"删除 ”1“正常
     */
    @Column(name = "user_status")
    private int status;

}
