package com.sleepymango.blog.repository;

import com.sleepymango.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description
 * @Author sleepymango
 * @Date 2021/3/23 18:10
 **/
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    User findByUsername(String username);
}
