package com.szyh.iflytek.bean;

import com.szyh.iflytek.define.MessageDefine;

/**
 * author  ruanhouli
 * email   ruanhouli@szyh-smart.com
 * created 2018/11/8 10:04
 * remark  发卡机传感器状态请求
 */

public class HairpinMachineSensorStatusRequest extends Message {
    public HairpinMachineSensorStatusRequest() {
        setCmd(MessageDefine.RequestCmd.HAIRPIN_MACHINE_SENSOR_STATUS);
    }
}
