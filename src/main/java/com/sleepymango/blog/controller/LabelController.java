package com.sleepymango.blog.controller;

import com.sleepymango.blog.service.LabelService;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/label")
    public void findAll() {
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
