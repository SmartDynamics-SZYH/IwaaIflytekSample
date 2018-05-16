package com.szyh.iflytek.bean;

import com.szyh.iflytek.define.MessageDefine;

/**
 * Created by Administrator on 2018/5/16.
 */

public class HairpinMachineReadCardRequest extends Message {
    public HairpinMachineReadCardRequest() {
        setCmd(MessageDefine.RequestCmd.HAIRPIN_MACHINE_READ_CARD);
    }
}
