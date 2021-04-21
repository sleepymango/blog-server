package com.sleepymango.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.BetweenFormatter;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sleepymango.blog.dto.CommentDTO;
import com.sleepymango.blog.entity.Comment;
import com.sleepymango.blog.entity.QComment;
import com.sleepymango.blog.entity.QVisitor;
import com.sleepymango.blog.entity.Visitor;
import com.sleepymango.blog.exception.AuthorizationException;
import com.sleepymango.blog.repository.CommentRepository;
import com.sleepymango.blog.repository.VisitorRepository;
import com.sleepymango.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author sleepymango
 * @Date 2021-03-31 02:54:17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final int PUBLISH_BETWEEN_SECOND = 60;
    private final int PUBLISH_BETWEEN_MINUTE = 60;
    private final int PUBLISH_BETWEEN_HOUR = 24;
    private final int PUBLISH_BETWEEN_DAY = 365;
    private final String PUBLISH_BETWEEN_FORMAT = "刚刚发布";
    private final String PUBLISH_BETWEEN_FORMAT_SUFFIX_MINUTE = "钟前";
    private final String PUBLISH_BETWEEN_FORMAT_SUFFIX_HOUR = "前";
    private final String PUBLISH_BETWEEN_FORMAT_SUFFIX_DAY = "前";
    private final String PUBLISH_BETWEEN_FORMAT_SUFFIX_YEAR = "年前";

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private VisitorRepository visitorRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<CommentDTO> findAll(Long articleId) {
        QComment qComment = QComment.comment;
        QVisitor qVisitor = QVisitor.visitor;

        List<CommentDTO> list = jpaQueryFactory.select(Projections.bean(
                CommentDTO.class,
                qComment.id, qComment.parentId, qComment.publish, qComment.content, qComment.from, qComment.like, qVisitor.name
        ))
                .from(qComment)
                .leftJoin(qVisitor).on(qComment.visitorId.eq(qVisitor.id))
                .where(qComment.articleId.eq(articleId))
                .orderBy(qComment.publish.desc())
                .fetch();

        return list.stream()
                .peek(d -> {
                            // 计算时间差
                            Date publish = d.getPublish();
                            Date now = new Date();
                            long betweenSecond = DateUtil.between(publish, now, DateUnit.SECOND);
                            long betweenMinute = DateUtil.between(publish, now, DateUnit.MINUTE);
                            long betweenHour = DateUtil.between(publish, now, DateUnit.HOUR);
                            long betweenDay = DateUtil.between(publish, now, DateUnit.DAY);
                            // 小于60秒时
                            if (PUBLISH_BETWEEN_SECOND > betweenSecond) {
                                d.setPublishFormat(PUBLISH_BETWEEN_FORMAT);
                            }
                            // 小于60分钟时
                            else if (PUBLISH_BETWEEN_MINUTE > betweenMinute) {
                                d.setPublishFormat(DateUtil.formatBetween(publish, now,
                                        BetweenFormatter.Level.MINUTE) + PUBLISH_BETWEEN_FORMAT_SUFFIX_MINUTE);
                            }
                            // 小于24小时时
                            else if (PUBLISH_BETWEEN_HOUR > betweenHour) {
                                d.setPublishFormat(DateUtil.formatBetween(publish, now,
                                        BetweenFormatter.Level.HOUR) + PUBLISH_BETWEEN_FORMAT_SUFFIX_HOUR);
                            }
                            // 小于365天时
                            else if (PUBLISH_BETWEEN_DAY > betweenDay) {
                                d.setPublishFormat(DateUtil.formatBetween(publish, now,
                                        BetweenFormatter.Level.DAY) + PUBLISH_BETWEEN_FORMAT_SUFFIX_DAY);
                            } else {
                                d.setPublishFormat(betweenDay / PUBLISH_BETWEEN_DAY + PUBLISH_BETWEEN_FORMAT_SUFFIX_YEAR);
                            }
                        }
                ).filter(d -> null == d.getParentId())
                .peek(d -> d.setChildren(getChildren(list, d)))
                .collect(Collectors.toList());

    }

    /**
     * stream  递归 , map peek 区别是map 需要返回值
     *
     * @param list
     * @param dto
     * @return
     */
    private List<CommentDTO> getChildren(List<CommentDTO> list, CommentDTO dto) {
        return list.stream()
                .filter(d -> Objects.equals(dto.getId(), d.getParentId()))
                .peek(d -> d.setChildren(getChildren(list, d)))
                .collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(CommentDTO commentDTO) throws RuntimeException {
        // TODO 验证邮箱和昵称
        commentDTO.setPublish(new Date());
        commentDTO.setLike(0);
        String name = commentDTO.getName();
        String email = commentDTO.getEmail();

        Visitor byName = visitorRepository.findByName(name);
        if (null != byName && !commentDTO.getEmail().equals(byName.getEmail())) {
            throw new AuthorizationException(409, "该昵称已存在");
        }
        Visitor visitor = visitorRepository.findByNameAndEmail(name, email);
        Long visitorId;
        if (null == visitor) {
            Visitor visitor1 = new Visitor();
            BeanUtil.copyProperties(commentDTO, visitor1);
            visitor1.setStatus(1);
            visitorId = visitorRepository.save(visitor1).getId();
        } else {
            visitorId = visitor.getId();
        }
        Comment comment = new Comment();
        BeanUtil.copyProperties(commentDTO, comment);
        comment.setVisitorId(visitorId);
        commentRepository.save(comment);
    }
}