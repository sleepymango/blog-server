package com.sleepymango.blog.controller;

import com.sleepymango.blog.common.Result;
import com.sleepymango.blog.common.ResultEnum;
import com.sleepymango.blog.entity.Menu;
import com.sleepymango.blog.service.MenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Author:
 * @Date: 2021-03-29 23:30:51
 */
@RestController
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    /**
     *
     */
    @GetMapping("/menus")
    public Result findAll() {
        List<Menu> menuList = menuService.findAll();
        return new Result(ResultEnum.SUCCESS.getStatusCode(), ResultEnum.SUCCESS.getMessage(), menuList);
    }


    /**
     * @param
     */
    @PostMapping("/menu")
    public void save() {
    }

    @PutMapping("/menu")
    public void update() {
    }


    @DeleteMapping("/menu")
    public void delete(@PathVariable Integer id) {
    }

}
