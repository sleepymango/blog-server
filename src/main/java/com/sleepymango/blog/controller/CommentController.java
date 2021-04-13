package com.sleepymango.blog.controller;

import com.sleepymango.blog.common.Result;
import com.sleepymango.blog.common.ResultEnum;
import com.sleepymango.blog.entity.Comment;
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
    @GetMapping("/comments")
    public void findAll() {
    }


    /**
     * @param
     */
    @PostMapping("/comments")
    public Result save(@RequestBody Comment comment) {
        commentService.save(comment);
        return new Result(ResultEnum.SUCCESS.getStatusCode(), ResultEnum.SUCCESS.getMessage(), null);
    }

    @PutMapping("/comment")
    public void update() {
    }


    @DeleteMapping("/comment")
    public void delete(@PathVariable Integer id) {
    }

}
