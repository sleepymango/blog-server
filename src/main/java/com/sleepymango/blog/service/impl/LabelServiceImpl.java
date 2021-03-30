package com.sleepymango.blog.service.impl;

import com.sleepymango.blog.repository.LabelRepository;
import com.sleepymango.blog.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @Author sleepymango
 * @Date 2021-03-31 02:54:37
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LabelServiceImpl implements LabelService {

    @Autowired
    private LabelRepository labelRepository;
}