package com.sleepymango.blog.controller;

import com.sleepymango.blog.common.Result;
import com.sleepymango.blog.common.ResultCode;
import com.sleepymango.blog.dto.Mine;
import com.sleepymango.blog.service.CommonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description CommanController
 * @Author sleepymango
 * @Date 2021/4/11 2:18
 **/

@RestController
public class CommonController {

    private final CommonService commonService;

    public CommonController(CommonService commonService) {
        this.commonService = commonService;
    }

    @GetMapping("/mine")
    public Result getMineInfo(){
        Mine mine = commonService.getMineInfo();
        return new Result(ResultCode.SUCCESS.getStatusCode(), ResultCode.SUCCESS.getMessage(), mine);
    }
}
