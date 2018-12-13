package com.szyh.iflytek.bean;


/**
 * 上报的扩展消息
 */
public class ExtUploadResponse extends DefaultResponse {

    private String extUploadMsg;

    public ExtUploadResponse() {
    }

    public String getExtUploadMsg() {
        return extUploadMsg;
    }

    public void setExtUploadMsg(String extUploadMsg) {
        this.extUploadMsg = extUploadMsg;
    }
}
