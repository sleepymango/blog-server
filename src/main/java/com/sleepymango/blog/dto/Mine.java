package com.sleepymango.blog.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description Mine
 * @Author sleepymango
 * @Date 2021/4/11 2:14
 **/

@Data
public class Mine implements Serializable {
    private Integer articles;

    private Integer categories;

    private Integer labels;
}
