package com.szyh.iflytek.bean;

import com.szyh.iflytek.define.MessageDefine;

/**
 * Created by Administrator on 2018/5/16.
 * 高拍杆焦距 请求
 */

public class HighBeatRodFocusRequest extends Message {

    /**
     * 1 放大 2缩小
     */
    private int focuse;

    public HighBeatRodFocusRequest() {
        this.setCmd(MessageDefine.RequestCmd.HIGH_BEAT_ROD_FOCUS);
    }

    public int getFocuse() {
        return focuse;
    }

    public void setFocuse(int focuse) {
        this.focuse = focuse;
    }
}
