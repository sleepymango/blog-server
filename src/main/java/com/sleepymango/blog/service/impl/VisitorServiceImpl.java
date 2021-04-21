package com.sleepymango.blog.service.impl;

import com.sleepymango.blog.entity.Visitor;
import com.sleepymango.blog.repository.VisitorRepository;
import com.sleepymango.blog.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @Author sleepymango
 * @Date 2021-04-19 16:16:40
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class VisitorServiceImpl implements VisitorService {

    @Autowired
    private VisitorRepository visitorRepository;

    @Override
    public Visitor findByNameAndEmail(String name,String email) {
        return visitorRepository.findByNameAndEmail(name,email);
    }
}