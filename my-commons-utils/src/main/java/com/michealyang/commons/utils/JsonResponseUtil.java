package com.michealyang.commons.utils;

import com.alibaba.fastjson.JSONObject;
import com.michealyang.commons.dto.RespCodeEnum;

/**
 * Created by michealyang on 17/4/14.
 */
public class JsonResponseUtil {
    public static JSONObject successResp() {
        JSONObject object = new JSONObject();
        object.put("code", RespCodeEnum.SUCCESS.getCode());
        object.put("msg", RespCodeEnum.SUCCESS.getDesc());
        object.put("data", null);
        return object;
    }

    public static JSONObject successResp(String msg, Object data) {
        JSONObject object = new JSONObject();
        object.put("code", RespCodeEnum.SUCCESS.getCode());
        object.put("msg", msg);
        object.put("data", data);
        return object;
    }

    public static JSONObject successResp(Object data) {
        JSONObject object = new JSONObject();
        object.put("code", RespCodeEnum.SUCCESS.getCode());
        object.put("msg", RespCodeEnum.SUCCESS.getDesc());
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

    public static JSONObject failureResp(RespCodeEnum codeEnum) {
        JSONObject object = new JSONObject();
        object.put("code", codeEnum.getCode());
        object.put("msg", codeEnum.getDesc());
        return object;
    }

    public static JSONObject failureResp(RespCodeEnum codeEnum, String msg) {
        JSONObject object = new JSONObject();
        object.put("code", codeEnum.getCode());
        object.put("msg", msg);
        return object;
    }

    public static JSONObject failureResp(int code) {
        RespCodeEnum codeEnum = RespCodeEnum.codeOf(code);
        JSONObject object = new JSONObject();
        object.put("code", code);
        object.put("msg", codeEnum.getDesc());
        return object;
    }

    public static JSONObject failureResp(int code, String msg) {
        JSONObject object = new JSONObject();
        object.put("code", code);
        object.put("msg", msg);
        return object;
    }
}
