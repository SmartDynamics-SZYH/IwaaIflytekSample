package com.szyh.iflytek.bean;

import com.szyh.iflytek.define.MessageDefine;

/**
 * Created by Administrator on 2018/5/16.
 */

public class HairpinMachineFrontReadCardRequest extends Message {
    public HairpinMachineFrontReadCardRequest() {
        setCmd(MessageDefine.RequestCmd.HAIRPIN_MACHINE_FRONT_READ_CARD);
    }
}
