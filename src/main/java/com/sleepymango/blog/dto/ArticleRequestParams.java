package com.sleepymango.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description ArticleRequestParams
 * @Author sleepymango
 * @Date 2021/4/14 22:48
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArticleRequestParams {
    private Integer page;

    private Integer pageSize;

    private String like;

    private Long categoryId;
}
