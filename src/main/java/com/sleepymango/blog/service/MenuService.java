package com.sleepymango.blog.service;

import com.sleepymango.blog.entity.Menu;

import java.util.List;

/**
 * @Description:
 * @Author sleepymango
 * @Date 2021-03-29 23:30:51
 */
public interface MenuService {
    List<Menu> findMenus();
}