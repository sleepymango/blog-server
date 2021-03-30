package com.sleepymango.blog.controller;

import com.sleepymango.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author sleepymango
 * @Date 2021/3/23 22:10
 **/

@RestController
@CrossOrigin
public class ArticleController {

    @Autowired
    private ArticleService articleService;


}
