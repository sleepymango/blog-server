package com.sleepymango.blog.service;

import com.sleepymango.blog.dto.LabelDTO;
import com.sleepymango.blog.entity.Label;

import java.util.List;

/**
 * @Description:
 * @Author sleepymango
 * @Date 2021-03-31 02:54:37
 */
public interface LabelService {

    /**
     * 查询所有标签
     * @return
     */
    List<LabelDTO> findAllAndCount();

    List<Label> findAll();

    Label findById(Long id);
}