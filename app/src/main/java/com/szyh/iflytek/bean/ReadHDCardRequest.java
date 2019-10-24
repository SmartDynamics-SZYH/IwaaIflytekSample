package com.szyh.iflytek.bean;

import com.szyh.iflytek.define.MessageDefine;

public class ReadHDCardRequest extends Message {

    private byte startAddress;

    private int count;

    public ReadHDCardRequest(byte startAddress, int count) {
        this.startAddress = startAddress;
        this.count = count;
        setCmd(MessageDefine.RequestCmd.READ_HD_CARD);
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
