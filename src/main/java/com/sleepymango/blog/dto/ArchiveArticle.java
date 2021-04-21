package com.sleepymango.blog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description ArchiveArticle
 * @Author sleepymango
 * @Date 2021/4/16 16:51
 **/

@Data
public class ArchiveArticle {
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy")
    private Date publish;

    @JsonIgnoreProperties(value = {"labels"})
    private List<ArticleDTO> articles;
}
