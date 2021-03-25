package com.sleepymango.blog.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @ClassName Category
 * @Description
 * @Author  sleepymango
 * @Date 2021-03-24 01:46:44
 */

@Entity
@Data
@Table ( name ="category" )
public class Category  implements Serializable {

	private static final long serialVersionUID =  6883086858105167771L;

	/**
	 * 分类ID
	 */
	@Id
   	@Column(name = "category_id" )
	private Long categoryId;

	/**
	 * 分类名
	 */
   	@Column(name = "category_name" )
	private String categoryName;

	/**
	 * 分类别名
	 */
   	@Column(name = "category_alias" )
	private String categoryAlias;

	/**
	 * 分类描述
	 */
   	@Column(name = "category_description" )
	private String categoryDescription;

	/**
	 * 父分类ID
	 */
   	@Column(name = "parent_id" )
	private Long parentId;
}
