package com.szyh.iflytek.bean;

/**
 * Created by Administrator on 2018/5/16.
 */

public class Message {
    private int cmd;
    private int messageID;

    public Message() {
    }

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }
}
