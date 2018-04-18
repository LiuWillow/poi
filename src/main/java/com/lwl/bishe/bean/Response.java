package com.lwl.bishe.bean;

/**
 * date  2018/3/18
 * author liuwillow
 **/
public class Response {
    private String msg;
    private Object data;

    public Response() {
    }

    public Response(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
