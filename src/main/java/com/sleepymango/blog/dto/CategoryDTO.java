package com.sleepymango.blog.dto;

import lombok.Data;

import java.util.List;

/**
 * @Description CategoryDTO
 * @Author sleepymango
 * @Date 2021/4/6 23:31
 **/

@Data
public class CategoryDTO {
    private Long id;

    private String name;

    private Long parentId;

    private Integer count;

    private List<CategoryDTO> children;
}
