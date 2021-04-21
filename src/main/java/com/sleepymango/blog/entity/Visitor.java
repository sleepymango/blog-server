package com.sleepymango.blog.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description  
 * @Author  linmengmeng
 * @Date 2021-04-17 22:47:47 
 */

@Entity
@Data
@ToString
@Table ( name ="visitor")
public class Visitor  implements Serializable {

	private static final long serialVersionUID =  8618339539577218614L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "visitor_id" )
	private Long id;

   	@Column(name = "visitor_name" )
	private String name;

   	@Column(name = "visitor_email" )
	private String email;

   	@Column(name = "visitor_status" )
	private Integer status;

   	@Column(name = "visitor_link" )
	private String link;
}
