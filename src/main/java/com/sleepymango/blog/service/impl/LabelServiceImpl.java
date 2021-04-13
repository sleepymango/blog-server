package com.sleepymango.blog.service.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sleepymango.blog.dto.LabelDTO;
import com.sleepymango.blog.entity.Label;
import com.sleepymango.blog.entity.QLabel;
import com.sleepymango.blog.repository.LabelRepository;
import com.sleepymango.blog.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author sleepymango
 * @Date 2021-03-31 02:54:37
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LabelServiceImpl implements LabelService {

    private final LabelRepository labelRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    public LabelServiceImpl(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    @Override
    public List<LabelDTO> findAll() {
        QLabel qLabel = QLabel.label;
        return jpaQueryFactory.selectFrom(qLabel).fetch().stream().map(label -> {
            LabelDTO labelDTO = new LabelDTO();
            labelDTO.setId(label.getId());
            labelDTO.setName(label.getName());
            labelDTO.setAlias(label.getAlias());
            labelDTO.setCount(label.getArticles().size());
            return labelDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public void save(Set<Label> labels) {
        labelRepository.saveAll(labels);
    }
}