package com.sleepymango.blog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description CommentDTO
 * @Author sleepymango
 * @Date 2021/4/17 23:03
 **/

@Data
public class CommentDTO {
    private Long id;

    private Long parentId;

    private String name;

    private String email;

    private String content;

    private String from;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date publish;

    @JsonIgnore
    private String link;

    private String publishFormat;

    private Integer like;

    private Long articleId;

    private List<CommentDTO> children;
}
