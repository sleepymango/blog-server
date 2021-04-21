package com.sleepymango.blog.controller;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.sleepymango.blog.common.Result;
import com.sleepymango.blog.common.ResultCode;
import com.sleepymango.blog.dto.CommentDTO;
import com.sleepymango.blog.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    @GetMapping("/comments/{articleId}")
    public Result findAll(@PathVariable Long articleId) {
       List<CommentDTO> comments =  commentService.findAll(articleId);
       return new Result(ResultCode.SUCCESS.getStatusCode(), ResultCode.SUCCESS.getMessage(), comments);
    }

    /**
     * @param
     */
    @PostMapping("/comments")
    public Result save(@RequestBody CommentDTO commentDTO, HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        UserAgent agent = UserAgentUtil.parse(userAgent);
        String browser = agent.getBrowser().toString()+" "+agent.getVersion();
        if (agent.isMobile()){
            commentDTO.setFrom("mobile");
        }else {
            commentDTO.setFrom(browser);
        }
        commentService.save(commentDTO);
        return new Result(ResultCode.SUCCESS.getStatusCode(), ResultCode.SUCCESS.getMessage(), null);
    }

    @PutMapping("/comment")
    public void update() {
    }


    @DeleteMapping("/comment")
    public void delete(@PathVariable Integer id) {
    }

}
