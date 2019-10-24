package com.szyh.iflytek;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.szyh.iflytek.bean.DefaultResponse;
import com.szyh.iflytek.bean.FilePrintRequest;
import com.szyh.iflytek.bean.FindAndReadHDCardRequest;
import com.szyh.iflytek.bean.FindAndReadHDCardResponse;
import com.szyh.iflytek.bean.HairpinMachineBackReadCardRequest;
import com.szyh.iflytek.bean.HairpinMachineFrontReadCardRequest;
import com.szyh.iflytek.bean.HairpinMachineLocationRequest;
import com.szyh.iflytek.bean.HairpinMachineLocationResponse;
import com.szyh.iflytek.bean.HairpinMachineReadCardRequest;
import com.szyh.iflytek.bean.HairpinMachineReadCardResponse;
import com.szyh.iflytek.bean.HairpinMachineResetRequest;
import com.szyh.iflytek.bean.HairpinMachineSensorStatusRequest;
import com.szyh.iflytek.bean.HairpinMachineSensorStatusResponse;
import com.szyh.iflytek.bean.HairpinMachineStatusRequest;
import com.szyh.iflytek.bean.HairpinMachineStatusResponse;
import com.szyh.iflytek.bean.HighBeatRodFocusRequest;
import com.szyh.iflytek.bean.HighBeatRodSnapRequest;
import com.szyh.iflytek.bean.HighBeatRodSwitchRequest;
import com.szyh.iflytek.bean.HighBeatRodPhotoResponse;
import com.szyh.iflytek.bean.Message;
import com.szyh.iflytek.bean.QRCodePrintRequest;
import com.szyh.iflytek.bean.ReadHDCardRequest;
import com.szyh.iflytek.bean.ReadHDCardResponse;
import com.szyh.iflytek.bean.WriteHDCardRequest;
import com.szyh.iflytek.define.MessageDefine;
import com.szyh.iflytek.websocket.ExtUploadListener;
import com.szyh.iflytek.websocket.HighBeatRodPhotoListener;
import com.szyh.iflytek.websocket.IflytekWebSocketHelper;
import com.szyh.iflytek.websocket.WebSocketCallback;
import com.szyh.iflytek.websocket.WebSocketStatusListener;

public class MainActivity extends AppCompatActivity implements HighBeatRodPhotoListener, WebSocketStatusListener, ExtUploadListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    TextView infoText;

    String[] locations = new String[]{
            "卡在前端不持卡位置",
            "卡在前端持卡位",
            "卡在射频位置",
            "卡在IC位置",
            "卡在后端持卡位",
            "机内无卡",
            "卡不再标准位置",
            "卡在重读卡位",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        infoText = findViewById(R.id.id_info_text);
        IflytekWebSocketHelper.getInstance().addHighBeatRodPhotoListener(this);
        IflytekWebSocketHelper.getInstance().addWebSocketStatusListener(this);
        IflytekWebSocketHelper.getInstance().addExtUploadListener(this);
    }

    private void setInfoText(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                infoText.setText(text);
            }
        });
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
                    setInfoText("高拍杆打开成功");
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
                    setInfoText("高拍杆焦距放大");
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
                    setInfoText("高拍杆抓拍成功：url地址是" + photo);
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
            IflytekWebSocketHelper.getInstance().removeWebSocketStatusListener(this);
            IflytekWebSocketHelper.getInstance().removeExtUploadListener(this);
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
                    int index = status - 0x30;
                    index = index > 0 ? index : 0;
                    StringBuffer sb = new StringBuffer();
                    sb.append("发卡机-获取设备状态：\n");
                    sb.append(locations[index]);
                    setInfoText(sb.toString());
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
                    StringBuffer sb = new StringBuffer();
                    sb.append("发卡机-从前端进卡并读卡：\n");
                    sb.append("轨道1：" + readCardResponse.getCardNo1() + "\n");
                    sb.append("轨道2：" + readCardResponse.getCardNo2() + "\n");
                    sb.append("轨道3：" + readCardResponse.getCardNo3() + "\n");
                    setInfoText(sb.toString());
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
                    StringBuffer sb = new StringBuffer();
                    sb.append("发卡机-从后端进卡并读卡：\n");
                    sb.append("轨道1：" + readCardResponse.getCardNo1() + "\n");
                    sb.append("轨道2：" + readCardResponse.getCardNo2() + "\n");
                    sb.append("轨道3：" + readCardResponse.getCardNo3() + "\n");
                    setInfoText(sb.toString());
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
        locationRequest.setLocation(0x32);
        IflytekWebSocketHelper.getInstance().sendMessage(locationRequest, new WebSocketCallback() {
            @Override
            public void onWebSocketCallback(Message message) {
                if (message.getCmd() == MessageDefine.ResponseCmd.HAIRPIN_MACHINE_LOCATION) {
                    HairpinMachineLocationResponse locationResponse = (HairpinMachineLocationResponse) message;
                    //TODO
                    setInfoText(" 移动卡到前端持卡位  已打开，请送卡入卡槽！");
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
                    Log.e("MainActivity", "onWebSocketCallback: " + JSON.toJSONString(readCardResponse));
                    //TODO
                    StringBuffer sb = new StringBuffer();
                    sb.append("发卡机-读卡信息：\n");
                    sb.append("轨道1：" + readCardResponse.getCardNo1() + "\n");
                    sb.append("轨道2：" + readCardResponse.getCardNo2() + "\n");
                    sb.append("轨道3：" + readCardResponse.getCardNo3() + "\n");
                    setInfoText(sb.toString());
                }
            }
        });
    }

    /**
     * 发卡机复位
     *
     * @param view
     */
    public void hairpin_machine_reset(View view) {
        /**
         * 复位动作
         * 0x30 复位 不移动卡片
         * 0x31 复位 卡片到前端不持卡位
         * 0x32 复位 从后端弹出卡
         * 0x33 复位 移动卡到前端持卡位
         */
        final HairpinMachineResetRequest resetRequest = new HairpinMachineResetRequest(0x30);
        IflytekWebSocketHelper.getInstance().sendMessage(resetRequest, new WebSocketCallback() {
            @Override
            public void onWebSocketCallback(Message message) {
                if (message.getCmd() == MessageDefine.ResponseCmd.HAIRPIN_MACHINE_RESET) {
                    DefaultResponse defaultResponse = (DefaultResponse) message;
                    Log.e("MainActivity", "onWebSocketCallback: " + JSON.toJSONString(defaultResponse));
                    StringBuffer sb = new StringBuffer();
                    sb.append("发卡机-复位成功(不移动卡片)\n");
                    setInfoText(sb.toString());
                }
            }
        });
    }

    /**
     * 获取传感器状态
     *
     * @param view
     */
    public void hairpin_machine_sensor_status(View view) {
        HairpinMachineSensorStatusRequest hmssr = new HairpinMachineSensorStatusRequest();
        IflytekWebSocketHelper.getInstance().sendMessage(hmssr, new WebSocketCallback() {
            @Override
            public void onWebSocketCallback(Message message) {
                if (message.getCmd() == MessageDefine.ResponseCmd.HAIRPIN_MACHINE_SENSOR_STATUS) {
                    HairpinMachineSensorStatusResponse res = (HairpinMachineSensorStatusResponse) message;
                    // TODO: 2018/11/8  
                }
            }
        });
    }

    /**
     * 打印二维码
     *
     * @param view
     */
    public void print_qr_code(View view) {
        QRCodePrintRequest qrCodePrintRequest = new QRCodePrintRequest("二维码", (byte) 10, 2, 3, 4);
        IflytekWebSocketHelper.getInstance().sendMessage(qrCodePrintRequest, new WebSocketCallback() {
            @Override
            public void onWebSocketCallback(Message message) {
                if (message.getCmd() == MessageDefine.ResponseCmd.QR_CODE_PRINT) {
                    DefaultResponse defaultResponse = (DefaultResponse) message;
                    Log.e("MainActivity", "onWebSocketCallback print_qr_code: " + JSON.toJSONString(defaultResponse));
                    if (defaultResponse.getResponseCode() > 0) {
                        setInfoText("打印成功！");
                    } else {
                        setInfoText("打印失败！");
                    }
                }
            }
        });
    }

    /**
     * 打印文件
     *
     * @param view
     */
    public void print_file(View view) {
        FilePrintRequest filePrintRequest = new FilePrintRequest("文本内容", "文件名称");
        IflytekWebSocketHelper.getInstance().sendMessage(filePrintRequest, new WebSocketCallback() {
            @Override
            public void onWebSocketCallback(Message message) {
                if (message.getCmd() == MessageDefine.ResponseCmd.FILE_PRINT) {
                    DefaultResponse defaultResponse = (DefaultResponse) message;
                    Log.e("MainActivity", "onWebSocketCallback print_file: " + JSON.toJSONString(defaultResponse));
                    if (defaultResponse.getResponseCode() > 0) {
                        setInfoText("打印成功！");
                    } else {
                        setInfoText("打印失败！");
                    }
                }
            }
        });
    }

    @Override
    public void onOpen() {
        setInfoText("与工控机连接成功！");
    }

    @Override
    public void onClose() {
        setInfoText("与工控机连接失败！");
    }

    @Override
    public void onError() {
        setInfoText("与工控机连接出错！");
    }

    @Override
    public void onExtUpload(String json) {
        Log.i(TAG, "onExtUpload: " + json);
    }

    public void hd_find_read_card(View view) {
        FindAndReadHDCardRequest farhdcr = new FindAndReadHDCardRequest();
        IflytekWebSocketHelper.getInstance().sendMessage(farhdcr, new WebSocketCallback() {
            @Override
            public void onWebSocketCallback(Message message) {
                if (message.getCmd() == MessageDefine.ResponseCmd.FIND_READ_HD_CARD) {
                    FindAndReadHDCardResponse res = (FindAndReadHDCardResponse) message;
                    Log.e("MainActivity", "onWebSocketCallback hd_find_read_card: " + JSON.toJSONString(res));
                    if (res.getResponseCode() == 0) {
                        setInfoText("HD100读卡器寻卡和读卡信息:" + res.getContent());
                    } else {
                        setInfoText("HD100读卡器寻卡和读卡信息失败！");
                    }
                }
            }
        });
    }

    public void hd_read_card(View view) {
        ReadHDCardRequest readHDCardRequest = new ReadHDCardRequest((byte) 0, 10);
        IflytekWebSocketHelper.getInstance().sendMessage(readHDCardRequest, new WebSocketCallback() {
            @Override
            public void onWebSocketCallback(Message message) {
                if (message.getCmd() == MessageDefine.ResponseCmd.READ_HD_CARD) {
                    ReadHDCardResponse res = (ReadHDCardResponse) message;
                    Log.e("MainActivity", "onWebSocketCallback hd_read_card: " + JSON.toJSONString(res));
                    if (res.getResponseCode() == 0) {
                        setInfoText("HD100读卡器读卡信息:" + res.getContent());
                    } else {
                        setInfoText("HD100读卡器读卡信息失败！");
                    }
                }
            }
        });
    }

    public void hd_write_card(View view) {
        WriteHDCardRequest writeHDCardRequest = new WriteHDCardRequest((byte) 8, 2, "8888888899999999");
        IflytekWebSocketHelper.getInstance().sendMessage(writeHDCardRequest, new WebSocketCallback() {
            @Override
            public void onWebSocketCallback(Message message) {
                if (message.getCmd() == MessageDefine.ResponseCmd.WRITE_HD_CARD) {
                    DefaultResponse res = (DefaultResponse) message;
                    Log.e("MainActivity", "onWebSocketCallback hd_write_card: " + JSON.toJSONString(res));
                    if (res.getResponseCode() ==0) {
                        setInfoText("HD100读卡器写卡信息成功！");
                    } else {
                        setInfoText("HD100读卡器写卡信息失败！");
                    }
                }
            }
        });
    }
}
