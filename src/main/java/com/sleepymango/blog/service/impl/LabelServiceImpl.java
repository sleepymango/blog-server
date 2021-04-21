package com.sleepymango.blog.service.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sleepymango.blog.dto.LabelDTO;
import com.sleepymango.blog.entity.Label;
import com.sleepymango.blog.entity.QLabel;
import com.sleepymango.blog.repository.LabelRepository;
import com.sleepymango.blog.service.LabelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    private final JPAQueryFactory jpaQueryFactory;

    public LabelServiceImpl(LabelRepository labelRepository, JPAQueryFactory jpaQueryFactory) {
        this.labelRepository = labelRepository;
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<LabelDTO> findAllAndCount() {
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
    public Label findById(Long id) {
        return labelRepository.findById(id).orElse(null);
    }

    @Override
    public List<Label> findAll() {
        QLabel qLabel = QLabel.label;
        return jpaQueryFactory.select(
                Projections.bean(
                        Label.class,
                        qLabel.id,
                        qLabel.name,
                        qLabel.alias
                )
        ).from(qLabel).fetch();

//        return labelRepository.findAll();
    }
}