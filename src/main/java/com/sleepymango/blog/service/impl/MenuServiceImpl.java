package com.sleepymango.blog.service.impl;

import com.sleepymango.blog.entity.Menu;
import com.sleepymango.blog.repository.MenuRepository;
import com.sleepymango.blog.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Author sleepymango
 * @Date 2021-03-29 23:30:51
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<Menu> findAll() {
        return menuRepository.findAllByParentNull();
    }
}