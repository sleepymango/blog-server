package com.sleepymango.blog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Description Archives
 * @Author sleepymango
 * @Date 2021/4/11 0:21
 **/

@Data
public class Archives {
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy年MM月")
    private Date publish;

    private Integer count;
}
