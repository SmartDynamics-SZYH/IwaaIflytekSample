package com.szyh.iflytek.bean;

/**
 * Created by Administrator on 2018/5/16.
 * 默认的返回消息
 */

public class DefaultResponse extends Message {
    private int responseCode;

    public DefaultResponse() {
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
}
