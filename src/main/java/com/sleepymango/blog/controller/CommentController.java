package com.sleepymango.blog.controller;

import com.sleepymango.blog.service.CommentService;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author:
 * @Date: 2021-03-31 02:54:17
 */
@RestController
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     *
     */
    @GetMapping("/comment")
    public void findAll() {
    }


    /**
     * @param
     */
    @PostMapping("/comment")
    public void save() {
    }

    @PutMapping("/comment")
    public void update() {
    }


    @DeleteMapping("/comment")
    public void delete(@PathVariable Integer id) {
    }

}
