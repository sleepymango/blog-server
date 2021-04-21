package com.sleepymango.blog.repository;

import com.sleepymango.blog.entity.Visitor;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Author sleepymango
 * @Date 2021-04-17 23:25:53
 */
@Repository
public interface VisitorRepository extends BaseRepository<Visitor, Long> {
    Visitor findByNameAndEmail(String name,String email);

    Visitor findByName(String name);
}