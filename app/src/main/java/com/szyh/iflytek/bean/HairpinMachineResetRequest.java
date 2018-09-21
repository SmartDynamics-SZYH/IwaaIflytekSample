package com.szyh.iflytek.bean;

import com.szyh.iflytek.define.MessageDefine;

/**
 * Created by Administrator on 2018/5/16.
 */

public class HairpinMachineResetRequest extends Message {

    /**
     * 复位动作
     * 0x30 复位 不移动卡片
     * 0x31 复位 卡片到前端不持卡位
     * 0x32 复位 从后端弹出卡
     * 0x33 复位 移动卡到前端持卡位
     */
    private int action;

    public HairpinMachineResetRequest() {
        setCmd(MessageDefine.RequestCmd.HAIRPIN_MACHINE_RESET);
    }

    public HairpinMachineResetRequest(int action) {
        this();
        this.action = action;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }
}
