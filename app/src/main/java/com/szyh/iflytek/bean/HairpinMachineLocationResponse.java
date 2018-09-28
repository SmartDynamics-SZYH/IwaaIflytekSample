package com.szyh.iflytek.bean;

/**
 * Created by Administrator on 2018/5/16.
 */

public class HairpinMachineLocationResponse extends DefaultResponse {

    /**
     * 0X30 移动卡到读卡器
     * 0X31 移动卡到IC位置
     * 0X32 移动卡到前端持卡位
     * 0X33 移动卡到后端持卡位
     * 0X34 移动卡到前端不持卡位
     * 0X35 从后端弹出卡片（回收）
     * 0X36 从前端弹出
     */
    private int location;

    public HairpinMachineLocationResponse() {
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }
}
