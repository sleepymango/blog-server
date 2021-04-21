package com.sleepymango.blog.controller;

import com.sleepymango.blog.common.Result;
import com.sleepymango.blog.common.ResultCode;
import com.sleepymango.blog.dto.LabelDTO;
import com.sleepymango.blog.entity.Label;
import com.sleepymango.blog.service.LabelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Author:
 * @Date: 2021-03-31 02:54:37
 */
@RestController
public class LabelController {
    private final LabelService labelService;

    public LabelController(LabelService labelService) {
        this.labelService = labelService;
    }

    /**
     * 查找所有标签，并返回标签对应的文章数量
     * @return
     */
    @GetMapping("/labels/count")
    public Result findAllAndCount() {
        List<LabelDTO> labelList = labelService.findAllAndCount();
        return new Result(ResultCode.SUCCESS.getStatusCode(), ResultCode.SUCCESS.getMessage(), labelList);
    }

    /**
     * 查找所有标签
     * @return
     */
    @GetMapping("/labels")
    public Result findAll() {
        List<Label> labelList = labelService.findAll();
        return new Result(ResultCode.SUCCESS.getStatusCode(), ResultCode.SUCCESS.getMessage(), labelList);
    }

    /**
     * 根据id查找标签
     * @return
     */
    @GetMapping("/labels/{id}")
    public Result findById(@PathVariable("id") Long id) {
        Label label = labelService.findById(id);
        return new Result(ResultCode.SUCCESS.getStatusCode(), ResultCode.SUCCESS.getMessage(), label);
    }


    /**
     * @param
     */
    @PostMapping("/label")
    public void save() {
    }

    @PutMapping("/label")
    public void update() {
    }


    @DeleteMapping("/label")
    public void delete(@PathVariable Integer id) {
    }

}
