package com.sleepymango.blog.service.impl;

import com.sleepymango.blog.entity.User;
import com.sleepymango.blog.repository.UserRepository;
import com.sleepymango.blog.service.UserService;
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

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }
}