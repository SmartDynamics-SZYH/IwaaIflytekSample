package com.szyh.iflytek.define;

/**
 * Created by Administrator on 2018/5/16.
 */

public class MessageDefine {

    public class RequestCmd {
        //高拍杆-开关
        public static final int HIGH_BEAT_ROD_SWITCH = 0xE001;
        //高拍杆-焦距
        public static final int HIGH_BEAT_ROD_FOCUS = 0xE002;
        //高拍杆-抓拍
        public static final int HIGH_BEAT_ROD_SNAP = 0xE003;
        //发卡机-获取设备状态
        public static final int HAIRPIN_MACHINE_STATUS = 0xE011;
        //发卡机-从前端进卡并读卡
        public static final int HAIRPIN_MACHINE_FRONT_READ_CARD = 0xE012;
        //发卡机-从后端进卡并读卡
        public static final int HAIRPIN_MACHINE_BACK_READ_CARD = 0xE013;
        //发卡机-移动卡位置
        public static final int HAIRPIN_MACHINE_LOCATION = 0xE014;
        //发卡机-读卡
        public static final int HAIRPIN_MACHINE_READ_CARD = 0xE015;
        //发卡机-复位
        public static final int HAIRPIN_MACHINE_RESET = 0xE016;
        //发卡机传感器状态
        public static final int HAIRPIN_MACHINE_SENSOR_STATUS = 0xE017;
        //打印二维码
        public static final int QR_CODE_PRINT = 0xE021;
        //打印文件
        public static final int FILE_PRINT = 0xE031;
    }

    public class ResponseCmd {
        //高拍杆-开关
        public static final int HIGH_BEAT_ROD_SWITCH = 0XF001;
        //高拍杆-焦距
        public static final int HIGH_BEAT_ROD_FOCUS = 0XF002;
        //高拍杆-抓拍
        public static final int HIGH_BEAT_ROD_SNAP = 0xF003;
        //高拍杆-摄像头图片的定时上报
        public static final int HIGH_BEAT_ROD_IMAGE = 0xF0A1;

        //发卡机-获取设备状态
        public static final int HAIRPIN_MACHINE_STATUS = 0XF011;
        //发卡机-从前端进卡并读卡
        public static final int HAIRPIN_MACHINE_FRONT_READ_CARD = 0xF012;
        //发卡机-从后端进卡并读卡
        public static final int HAIRPIN_MACHINE_BACK_READ_CARD = 0xF013;
        //发卡机-移动卡位置
        public static final int HAIRPIN_MACHINE_LOCATION = 0xF014;
        //发卡机-读卡
        public static final int HAIRPIN_MACHINE_READ_CARD = 0xF015;
        //发卡机-复位
        public static final int HAIRPIN_MACHINE_RESET = 0xF016;
        //发卡机传感器状态
        public static final int HAIRPIN_MACHINE_SENSOR_STATUS = 0xF017;
        //打印二维码
        public static final int QR_CODE_PRINT = 0xF021;
        //打印文件
        public static final int FILE_PRINT = 0xF031;
    }
}
