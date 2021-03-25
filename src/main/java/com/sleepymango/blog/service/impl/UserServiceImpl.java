package com.sleepymango.blog.service.impl;

import com.sleepymango.blog.entity.User;
import com.sleepymango.blog.repository.UserRepository;
import com.sleepymango.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @Author sleepymango
 * @Date 2021-03-24 13:54:51
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Long userId){
        return userRepository.findById(userId).orElse(null);
    }
}