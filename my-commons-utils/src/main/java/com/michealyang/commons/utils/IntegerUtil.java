package com.michealyang.commons.utils;

/**
 * Created by michealyang on 16/3/19.
 */
public class IntegerUtil {
    /**
     * 判断一个Integer类型是否为Null或者0
     * @param v
     * @return
     */
    public static boolean isNullOrZero(Integer v){
        if(v == null || v == 0) return true;
        return false;
    }
}
