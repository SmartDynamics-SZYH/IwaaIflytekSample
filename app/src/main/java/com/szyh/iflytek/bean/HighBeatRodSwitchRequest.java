package com.szyh.iflytek.bean;

import com.szyh.iflytek.define.MessageDefine;

/**
 * Created by Administrator on 2018/5/16.
 * 高拍杆开关 请求
 */

public class HighBeatRodSwitchRequest extends Message {

    /**
     * 1 开 0 关闭
     */
    private int cameraSwitch;

    public HighBeatRodSwitchRequest() {
        this.setCmd(MessageDefine.RequestCmd.HIGH_BEAT_ROD_SWITCH);
    }

    public int getCameraSwitch() {
        return cameraSwitch;
    }

    public void setCameraSwitch(int cameraSwitch) {
        this.cameraSwitch = cameraSwitch;
    }
}
