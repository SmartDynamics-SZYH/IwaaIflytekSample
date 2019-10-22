package com.szyh.iflytek.bean;

import com.szyh.iflytek.define.MessageDefine;

public class WriteHDCardRequest extends Message {

    private byte startAddress;

    private int count;

    private String content;

    public WriteHDCardRequest(byte startAddress, int count, String content) {
        this.startAddress = startAddress;
        this.count = count;
        this.content = content;
        setCmd(MessageDefine.RequestCmd.WRITE_HD_CARD);
    }

    public byte getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(byte startAddress) {
        this.startAddress = startAddress;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
