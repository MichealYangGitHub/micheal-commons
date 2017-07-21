package com.michealyang.commons.utils;

/**
 * Created by michealyang on 16/3/19.
 */
public class LongUtil {
    /**
     * 判断一个Long类型是否为Null或者0
     * @param v
     * @return
     */
    public static boolean isNullOrZero(Long v){
        if(v == null || v == 0) return true;
        return false;
    }
}
