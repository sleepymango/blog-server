package com.sleepymango.blog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sleepymango.blog.entity.Label;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Description ArticleDTO
 * @Author sleepymango
 * @Date 2021/4/4 1:44
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArticleDTO implements Serializable {
    private Long id;

    private String title;

    private String content;

    private String banner;

//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date publish;

    private Integer status;

    @JsonIgnoreProperties(value = "articles")
    private List<Label> labels;

    private Long authorId;

    private String author;

    private Long categoryId;

    private String category;

}
