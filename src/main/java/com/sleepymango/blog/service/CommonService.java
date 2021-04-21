package com.sleepymango.blog.service;

import com.sleepymango.blog.dto.Mine;

/**
 * @Description
 * @Author sleepymango
 * @Date 2021/4/11 2:19
 **/

public interface CommonService {
    /**
     * 获取文章数，分类数，标签数
     * @return
     */
    Mine getMineInfo();
}
