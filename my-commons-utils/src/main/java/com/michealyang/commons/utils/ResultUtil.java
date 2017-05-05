package com.michealyang.commons.utils;

import com.michealyang.commons.dto.ResultDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by michealyang on 17/5/1.
 */
public class ResultUtil {
    private static final Logger logger = LoggerFactory.getLogger(ResultUtil.class);

    public static ResultDto genSuccessResult(String msg, Object data){
        return new ResultDto(true, msg, data);
    }

    public static ResultDto genFailResult(String msg, Object data){
        logger.warn("[genFailResult] msg=#{}, data=#{}", msg, data);
        return new ResultDto(false, msg, data);
    }
}
