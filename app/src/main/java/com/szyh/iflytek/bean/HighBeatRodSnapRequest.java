package com.szyh.iflytek.bean;

import com.szyh.iflytek.define.MessageDefine;

/**
 * Created by Administrator on 2018/5/16.
 * 高拍杆抓拍 请求
 */

public class HighBeatRodSnapRequest extends Message {


    public HighBeatRodSnapRequest() {
        this.setCmd(MessageDefine.RequestCmd.HIGH_BEAT_ROD_SNAP);
    }

}
