package com.szyh.iflytek;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.szyh.iflytek.bean.DefaultResponse;
import com.szyh.iflytek.bean.HairpinMachineBackReadCardRequest;
import com.szyh.iflytek.bean.HairpinMachineFrontReadCardRequest;
import com.szyh.iflytek.bean.HairpinMachineLocationRequest;
import com.szyh.iflytek.bean.HairpinMachineLocationResponse;
import com.szyh.iflytek.bean.HairpinMachineReadCardRequest;
import com.szyh.iflytek.bean.HairpinMachineReadCardResponse;
import com.szyh.iflytek.bean.HairpinMachineStatusRequest;
import com.szyh.iflytek.bean.HairpinMachineStatusResponse;
import com.szyh.iflytek.bean.HighBeatRodFocusRequest;
import com.szyh.iflytek.bean.HighBeatRodSnapRequest;
import com.szyh.iflytek.bean.HighBeatRodSwitchRequest;
import com.szyh.iflytek.bean.HighBeatRodPhotoResponse;
import com.szyh.iflytek.bean.Message;
import com.szyh.iflytek.define.MessageDefine;
import com.szyh.iflytek.websocket.HighBeatRodPhotoListener;
import com.szyh.iflytek.websocket.IflytekWebSocketHelper;
import com.szyh.iflytek.websocket.WebSocketCallback;

public class MainActivity extends AppCompatActivity implements HighBeatRodPhotoListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IflytekWebSocketHelper.getInstance().addHighBeatRodPhotoListener(this);
    }

    /**
     * 开始连接
     *
     * @param view
     */
    public void connect(View view) {
        IflytekWebSocketHelper.getInstance().createWebSocketClient();
        IflytekWebSocketHelper.getInstance().connect();
    }

    /**
     * 断开连接
     *
     * @param view
     */
    public void disconnect(View view) {
        IflytekWebSocketHelper.getInstance().disconnect();
    }

    /**
     * 高拍杆开关
     *
     * @param view
     */
    public void high_beat_rod_switch(View view) {
        HighBeatRodSwitchRequest switchRequest = new HighBeatRodSwitchRequest();
        switchRequest.setCameraSwitch(1);//1 开 0 关闭
        IflytekWebSocketHelper.getInstance().sendMessage(switchRequest, new WebSocketCallback() {
            @Override
            public void onWebSocketCallback(Message message) {
                if (message.getCmd() == MessageDefine.ResponseCmd.HIGH_BEAT_ROD_SWITCH) {
                    DefaultResponse defaultResponse = (DefaultResponse) message;
                    //TODO
                }
            }
        });
    }


    /**
     * 高拍杆焦距
     *
     * @param view
     */
    public void high_beat_rod_focus(View view) {
        HighBeatRodFocusRequest focusRequest = new HighBeatRodFocusRequest();
        focusRequest.setFocuse(1);//1 放大 2缩小
        IflytekWebSocketHelper.getInstance().sendMessage(focusRequest, new WebSocketCallback() {
            @Override
            public void onWebSocketCallback(Message message) {
                if (message.getCmd() == MessageDefine.ResponseCmd.HIGH_BEAT_ROD_FOCUS) {
                    DefaultResponse defaultResponse = (DefaultResponse) message;
                    //TODO
                }
            }
        });
    }

    /**
     * 高拍杆抓拍
     *
     * @param view
     */
    public void high_beat_rod_snap(View view) {
        HighBeatRodSnapRequest snapRequest = new HighBeatRodSnapRequest();
        IflytekWebSocketHelper.getInstance().sendMessage(snapRequest, new WebSocketCallback() {
            @Override
            public void onWebSocketCallback(Message message) {
                if (message.getCmd() == MessageDefine.ResponseCmd.HIGH_BEAT_ROD_SNAP) {
                    HighBeatRodPhotoResponse photoResponse = (HighBeatRodPhotoResponse) message;
                    String photo = photoResponse.getPhoto();//抓拍图片的URL地址
                    //TODO
                }
            }
        });
    }


    /**
     * 摄像头图片的定时上报
     *
     * @param photoBase64 图片的BASE64
     */
    @Override
    public void onHighBeatRodPhoto(String photoBase64) {
        //摄像头图片的定时上报, 图片的BASE64
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isFinishing()) {
            IflytekWebSocketHelper.getInstance().removeHighBeatRodPhotoListener(this);
        }
    }

    /**
     * 发卡机-获取设备状态
     *
     * @param view
     */
    public void hairpin_machine_status(View view) {
        HairpinMachineStatusRequest statusRequest = new HairpinMachineStatusRequest();
        IflytekWebSocketHelper.getInstance().sendMessage(statusRequest, new WebSocketCallback() {
            @Override
            public void onWebSocketCallback(Message message) {
                if (message.getCmd() == MessageDefine.ResponseCmd.HAIRPIN_MACHINE_STATUS) {
                    HairpinMachineStatusResponse statusResponse = (HairpinMachineStatusResponse) message;
                    int status = statusResponse.getStatus();
                    //TODO
                }
            }
        });
    }

    /**
     * 发卡机-从前端进卡并读卡
     *
     * @param view
     */
    public void hairpin_machine_front_read_card(View view) {
        HairpinMachineFrontReadCardRequest frontReadCardRequest = new HairpinMachineFrontReadCardRequest();
        IflytekWebSocketHelper.getInstance().sendMessage(frontReadCardRequest, new WebSocketCallback() {
            @Override
            public void onWebSocketCallback(Message message) {
                if (message.getCmd() == MessageDefine.ResponseCmd.HAIRPIN_MACHINE_FRONT_READ_CARD) {
                    HairpinMachineReadCardResponse readCardResponse = (HairpinMachineReadCardResponse) message;
                    //TODO
                }
            }
        });
    }

    /**
     * 发卡机-从后端进卡并读卡
     */
    public void hairpin_machine_back_read_card(View view) {
        HairpinMachineBackReadCardRequest backReadCardRequest = new HairpinMachineBackReadCardRequest();
        IflytekWebSocketHelper.getInstance().sendMessage(backReadCardRequest, new WebSocketCallback() {
            @Override
            public void onWebSocketCallback(Message message) {
                if (message.getCmd() == MessageDefine.ResponseCmd.HAIRPIN_MACHINE_BACK_READ_CARD) {
                    HairpinMachineReadCardResponse readCardResponse = (HairpinMachineReadCardResponse) message;
                    //TODO
                }
            }
        });
    }

    /**
     * 发卡机-移动卡位置
     *
     * @param view
     */
    public void hairpin_machine_location(View view) {
        HairpinMachineLocationRequest locationRequest = new HairpinMachineLocationRequest();
        IflytekWebSocketHelper.getInstance().sendMessage(locationRequest, new WebSocketCallback() {
            @Override
            public void onWebSocketCallback(Message message) {
                if (message.getCmd() == MessageDefine.ResponseCmd.HAIRPIN_MACHINE_LOCATION) {
                    HairpinMachineLocationResponse locationResponse = (HairpinMachineLocationResponse) message;
                    //TODO
                }
            }
        });
    }

    /**
     * 发卡机-读卡
     *
     * @param view
     */
    public void hairpin_machine_read_card(View view) {
        HairpinMachineReadCardRequest readCardRequest = new HairpinMachineReadCardRequest();
        IflytekWebSocketHelper.getInstance().sendMessage(readCardRequest, new WebSocketCallback() {
            @Override
            public void onWebSocketCallback(Message message) {
                if (message.getCmd() == MessageDefine.ResponseCmd.HAIRPIN_MACHINE_READ_CARD) {
                    HairpinMachineReadCardResponse readCardResponse = (HairpinMachineReadCardResponse) message;
                    //TODO
                }
            }
        });
    }
}
