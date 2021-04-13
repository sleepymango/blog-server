package com.sleepymango.blog.controller;

import com.sleepymango.blog.common.Result;
import com.sleepymango.blog.common.ResultEnum;
import com.sleepymango.blog.dto.LabelDTO;
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
     *
     */
    @GetMapping("/labels")
    public Result findAll() {
        List<LabelDTO> labelList = labelService.findAll();
        return new Result(ResultEnum.SUCCESS.getStatusCode(), ResultEnum.SUCCESS.getMessage(), labelList);
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
