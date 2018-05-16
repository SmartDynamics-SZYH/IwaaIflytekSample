package com.szyh.iflytek.bean;

import com.szyh.iflytek.define.MessageDefine;

/**
 * Created by Administrator on 2018/5/16.
 */

public class HairpinMachineStatusRequest extends Message {
    public HairpinMachineStatusRequest() {
        setCmd(MessageDefine.RequestCmd.HAIRPIN_MACHINE_STATUS);
    }
}
