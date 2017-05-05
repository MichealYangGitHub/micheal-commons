package com.michealyang.commons.utils;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by michealyang on 17/4/21.
 */
public class EncryptUtil {
    public static String MD5(String v){
        if(StringUtils.isBlank(v)) return v;
        return Hashing.md5().newHasher().putString(v, Charsets.UTF_8).hash().toString();
    }

    public static void main(String[] args) {
        System.out.println(EncryptUtil.MD5("123"));
    }
}
