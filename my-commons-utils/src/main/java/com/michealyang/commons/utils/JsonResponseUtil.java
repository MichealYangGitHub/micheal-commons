package com.michealyang.commons.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by michealyang on 17/4/14.
 */
public class JsonResponseUtil {
    public static JSONObject successResp(String msg, Object data) {
        JSONObject object = new JSONObject();
        object.put("code", 1);
        object.put("msg", msg);
        object.put("data", data);
        return object;
    }

    public static JSONObject failureResp(String msg, Object data){
        JSONObject object = new JSONObject();
        object.put("code", 0);
        object.put("msg", msg);
        object.put("data", data);
        return object;
    }
}
