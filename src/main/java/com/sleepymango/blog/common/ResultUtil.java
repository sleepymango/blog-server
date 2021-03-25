package com.sleepymango.blog.common;

/**
 * @Description TODO
 * @Author sleepymango
 * @Date 2021/3/25 20:33
 **/

public class ResultUtil {
    /**
     * 成功有返回
     * @param data
     * @return Result
     */
    public static Result success(Object data){
        return new Result(ResultEnum.SUCCESS.getStatusCode(),ResultEnum.SUCCESS.getMessage(),data);
    }

    /**
     * 成功无返回
     * @return Result
     */
    public static Result success(){
        return ResultUtil.success(null);
    }


}
