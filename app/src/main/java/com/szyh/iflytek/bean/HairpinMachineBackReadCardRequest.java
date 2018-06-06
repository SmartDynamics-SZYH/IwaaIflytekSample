package com.szyh.iflytek.bean;

import com.szyh.iflytek.define.MessageDefine;

/**
 * Created by Administrator on 2018/5/16.
 */

public class HairpinMachineBackReadCardRequest extends Message {

    public HairpinMachineBackReadCardRequest() {
        setCmd(MessageDefine.RequestCmd.HAIRPIN_MACHINE_BACK_READ_CARD);
    }
}
