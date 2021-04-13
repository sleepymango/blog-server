package com.sleepymango.blog.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @Description Page
 * @Author sleepymango
 * @Date 2021/4/9 11:54
 **/

public class Page<T> implements Serializable {
    private List<T> data;

    private Integer currPage;

    private Integer pageSize;

    private Integer total;
}
