package com.michealyang.commons.utils;

import com.michealyang.commons.dto.RespCodeEnum;
import com.michealyang.commons.dto.ResultDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by michealyang on 17/5/1.
 */
public class ResultUtil {
    private static final Logger logger = LoggerFactory.getLogger(ResultUtil.class);

    public static ResultDto genSuccessResult(){
        return new ResultDto.Builder()
                .setData(null)
                .setSuccess(true)
                .setCode(RespCodeEnum.SUCCESS.getCode())
                .setMsg(RespCodeEnum.SUCCESS.getDesc())
                .build();
    }

    public static ResultDto genSuccessResult(Object data){
        return new ResultDto.Builder()
                .setData(data)
                .setSuccess(true)
                .setCode(RespCodeEnum.SUCCESS.getCode())
                .setMsg(RespCodeEnum.SUCCESS.getDesc())
                .build();
    }

    public static ResultDto genSuccessResult(String msg, Object data){
        return new ResultDto.Builder()
                .setData(data)
                .setSuccess(true)
                .setCode(RespCodeEnum.SUCCESS.getCode())
                .setMsg(msg)
                .build();
    }

    public static ResultDto genFailResult(RespCodeEnum codeEnum){
        logger.warn("[genFailResult] codeEnum=#{}", codeEnum);
        return new ResultDto.Builder()
                .setData(null)
                .setSuccess(false)
                .setCode(codeEnum.getCode())
                .setMsg(codeEnum.getDesc())
                .build();
    }

    public static ResultDto genFailResult(RespCodeEnum codeEnum, String msg){
        logger.warn("[genFailResult] codeEnum=#{}, msg=#{}", codeEnum, msg);
        return new ResultDto.Builder()
                .setData(null)
                .setSuccess(false)
                .setCode(codeEnum.getCode())
                .setMsg(msg)
                .build();
    }

    public static ResultDto genFailResult(RespCodeEnum codeEnum, String msg, Object data){
        logger.warn("[genFailResult] codeEnum=#{}, msg=#{}, data=#{}", codeEnum, msg, data);
        return new ResultDto.Builder()
                .setData(data)
                .setSuccess(false)
                .setCode(codeEnum.getCode())
                .setMsg(msg)
                .build();
    }


    public static ResultDto genFailResult(String msg, Object data){
        logger.warn("[genFailResult] msg=#{}, data=#{}", msg, data);
        return new ResultDto(false, msg, data);
    }
}
