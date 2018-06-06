package com.szyh.iflytek.bean;

import com.szyh.iflytek.define.MessageDefine;

/**
 * Created by Administrator on 2018/5/16.
 */

public class HairpinMachineLocationRequest extends Message {

    private int location;

    public HairpinMachineLocationRequest() {
        setCmd(MessageDefine.RequestCmd.HAIRPIN_MACHINE_LOCATION);
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }
}
