package com.sleepymango.blog.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description LabelDTO
 * @Author sleepymango
 * @Date 2021/4/10 23:40
 **/

@Data
public class LabelDTO implements Serializable {
    private Long id;

    private String name;

    private String alias;

    private Integer count;
}
