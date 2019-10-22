package com.szyh.iflytek.bean;

import com.szyh.iflytek.define.MessageDefine;

public class FindAndReadHDCardRequest extends Message {

    public FindAndReadHDCardRequest() {
        setCmd(MessageDefine.RequestCmd.FIND_READ_HD_CARD);
    }
}
