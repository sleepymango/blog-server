package com.sleepymango.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Description  
 * @Author  linmengmeng
 * @Date 2021-03-31 00:49:36 
 */

@Entity
@Data
@ToString
@Table ( name ="comment")
public class Comment  implements Serializable {

	private static final long serialVersionUID =  3978783489892079309L;

	/**
	 * 评论的ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "comment_id" )
	private Long id;

	/**
	 * 评论内容
	 */
   	@Column(name = "comment_content" )
	private String content;

	/**
	 * 评论点赞数
	 */
   	@Column(name = "comment_like" )
	private Long like;

	/**
	 * 评论时间
	 */
   	@Column(name = "comment_publish" )
	private Date publish;

	/**
	 * 父评论  many
	 */
	@JsonIgnore
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
   	@JoinColumn(name = "parent_id")
	private Comment parent;

	/**
	 * 子评论 ，关系维护方 one
	 */
	@OneToMany(mappedBy = "parent",cascade = CascadeType.ALL)
	private List<Comment> children;

	/**
	 * 评论的用户
	 */
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "user_id" )
	private User user;

	/**
	 * 评论的文章
	 */
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "article_id" )
	private Article article;

}
