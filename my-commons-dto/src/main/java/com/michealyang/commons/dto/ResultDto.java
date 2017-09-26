package com.michealyang.commons.dto;

/**
 * Created by michealyang on 17/3/7.
 */
public class ResultDto<T> {

    public ResultDto(Builder builder) {
        this.code = builder.getCode();
        this.msg = builder.getMsg();
        this.success = builder.isSuccess();
        this.data = (T)builder.getData();
    }

    public ResultDto(boolean success, String msg){
        this.success = success;
        this.msg = msg;
        this.data = null;
    }

    public ResultDto(boolean success, String msg, T data){
        this.success = success;
        this.msg = msg;
        this.data = data;
    }


    private int code;

    private boolean success;

    private String msg;

    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static class Builder{
        private int code;
        private String msg;
        private boolean success;
        private Object data;

        public int getCode() {
            return code;
        }

        public Builder setCode(int code) {
            this.code = code;
            return this;
        }

        public String getMsg() {
            return msg;
        }

        public Builder setMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public boolean isSuccess() {
            return success;
        }

        public Builder setSuccess(boolean success) {
            this.success = success;
            return this;
        }

        public Object getData() {
            return data;
        }

        public Builder setData(Object data) {
            this.data = data;
            return this;
        }

        public ResultDto build() {
            return new ResultDto(this);
        }
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ResultDto{");
        sb.append("code=").append(code);
        sb.append(", success=").append(success);
        sb.append(", msg='").append(msg).append('\'');
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
