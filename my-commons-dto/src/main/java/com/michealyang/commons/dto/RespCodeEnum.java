package com.michealyang.commons.dto;

/**
 * @author michealyang
 * @version 1.0
 * @created 17/7/23
 * 开始眼保健操： →_→  ↑_↑  ←_←  ↓_↓
 */
public enum RespCodeEnum {
    SUCCESS(1, "成功"),
    FAIL(0, "失败"),
    FORBIDDEN(403, "没有权限"),
    NOT_FOUND(404, "没有找到"),
    SYS_ERROR(500, "系统错误"),
    PRARAM_ERROR(600, "参数错误"),
    DUPLICATION(610, "重复"),
    UNDEFINED(900, "未定义的错误");

    RespCodeEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    private int code;
    private String desc;

    public int getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

    public static RespCodeEnum codeOf(int code) {
        for (RespCodeEnum codeEnum : RespCodeEnum.values()) {
            if (codeEnum.getCode() == code) {
                return codeEnum;
            }
        }
        return UNDEFINED;
    }
}
