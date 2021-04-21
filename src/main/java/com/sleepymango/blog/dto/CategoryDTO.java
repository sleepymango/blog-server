package com.sleepymango.blog.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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

//    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnore
    private Long parentId;

    private Integer count;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<CategoryDTO> children;
}
