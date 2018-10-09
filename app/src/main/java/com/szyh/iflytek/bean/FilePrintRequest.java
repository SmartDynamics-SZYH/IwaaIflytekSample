package com.szyh.iflytek.bean;

import com.szyh.iflytek.define.MessageDefine;

/**
 * 打印文件--请求
 */

public class FilePrintRequest extends Message {

    /**
     * 文本内容
     */
    private String text;

    /**
     * 文件名称
     */
    private String fileName;


    public FilePrintRequest() {
        setCmd(MessageDefine.RequestCmd.FILE_PRINT);
    }


    public FilePrintRequest(String text, String fileName) {
        this();
        this.text = text;
        this.fileName = fileName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
