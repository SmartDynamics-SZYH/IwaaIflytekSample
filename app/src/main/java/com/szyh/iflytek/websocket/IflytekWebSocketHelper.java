package com.szyh.iflytek.websocket;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.szyh.iflytek.bean.DefaultResponse;
import com.szyh.iflytek.bean.HairpinMachineLocationResponse;
import com.szyh.iflytek.bean.HairpinMachineReadCardResponse;
import com.szyh.iflytek.bean.HairpinMachineStatusResponse;
import com.szyh.iflytek.bean.HighBeatRodPhotoResponse;
import com.szyh.iflytek.bean.Message;
import com.szyh.iflytek.define.MessageDefine;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2018/5/16.
 * IflytekWebSocketHelper
 */

public class IflytekWebSocketHelper {

    private static final String WEB_SOCKET_URI = "ws://192.168.5.88:5000/WebSocketMessager";

    private static IflytekWebSocketHelper instance;

    private IflytekWebSocketClient webSocketClient;

    private List<HighBeatRodPhotoListener> highBeatRodPhotoListeners = new ArrayList<>();

    private IflytekWebSocketHelper() {

    }

    public static IflytekWebSocketHelper getInstance() {
        if (instance == null) {
            synchronized (IflytekWebSocketHelper.class) {
                if (instance == null) {
                    instance = new IflytekWebSocketHelper();
                }
            }
        }
        return instance;
    }

    public void createWebSocketClient() {
        try {
            webSocketClient = new IflytekWebSocketClient(new URI(WEB_SOCKET_URI), new Draft_17());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接webSocket
     */
    public void connect() {
        if (webSocketClient != null) {
            webSocketClient.connect();
        }
    }

    public boolean isOpen() {
        return webSocketClient != null && webSocketClient.getConnection() != null && webSocketClient.getConnection().isOpen();
    }

    /**
     * 断开连接webSocket
     */
    public void disconnect() {
        if (webSocketClient != null) {
            webSocketClient.close();
        }
    }

    public void addHighBeatRodPhotoListener(HighBeatRodPhotoListener highBeatRodPhotoListener) {
        highBeatRodPhotoListeners.add(highBeatRodPhotoListener);
    }

    public void removeHighBeatRodPhotoListener(HighBeatRodPhotoListener highBeatRodPhotoListener) {
        highBeatRodPhotoListeners.remove(highBeatRodPhotoListener);
    }

    private static final AtomicInteger ID_ATOMIC = new AtomicInteger();

    private Hashtable<Integer, WebSocketCallback> webSocketCallbackHashtable = new Hashtable<>();

    /**
     * 发送消息
     *
     * @param message 消息体
     */
    public void sendMessage(Message message, WebSocketCallback webSocketCallback) {
        if (!isOpen()) {
            Log.e(TAG, "WebSocket未连接成功，发送消息失败！");
            return;
        }
        message.setMessageID(ID_ATOMIC.getAndIncrement());
        if (webSocketCallback != null) {
            webSocketCallbackHashtable.put(message.getMessageID(), webSocketCallback);
        }
        webSocketClient.send(JSON.toJSONString(message));
    }

    class IflytekWebSocketClient extends WebSocketClient {
        public IflytekWebSocketClient(URI serverUri, Draft draft) {
            super(serverUri, draft);
        }

        @Override
        public void onOpen(ServerHandshake serverHandshake) {

        }

        @Override
        public void onMessage(String result) {
            int cmd = (Integer) JSON.parseObject(result).get("cmd");
            Message message = null;
            switch (cmd) {
                case MessageDefine.ResponseCmd.HIGH_BEAT_ROD_SWITCH:
                case MessageDefine.ResponseCmd.HIGH_BEAT_ROD_FOCUS:
                    message = JSON.parseObject(result, DefaultResponse.class);
                    break;
                case MessageDefine.ResponseCmd.HIGH_BEAT_ROD_SNAP:
                    message = JSON.parseObject(result, HighBeatRodPhotoResponse.class);
                    break;
                case MessageDefine.ResponseCmd.HIGH_BEAT_ROD_IMAGE:
                    HighBeatRodPhotoResponse photoResponse = JSON.parseObject(result, HighBeatRodPhotoResponse.class);
                    if (photoResponse.getMessageID() < 0) {//主动推送，messageID值为-1
                        for (HighBeatRodPhotoListener photoListener : highBeatRodPhotoListeners) {
                            photoListener.onHighBeatRodPhoto(photoResponse.getPhoto());
                        }
                    }
                    break;
                case MessageDefine.ResponseCmd.HAIRPIN_MACHINE_STATUS:
                    message = JSON.parseObject(result, HairpinMachineStatusResponse.class);
                    break;
                case MessageDefine.ResponseCmd.HAIRPIN_MACHINE_FRONT_READ_CARD:
                case MessageDefine.ResponseCmd.HAIRPIN_MACHINE_BACK_READ_CARD:
                case MessageDefine.ResponseCmd.HAIRPIN_MACHINE_READ_CARD:
                    message = JSON.parseObject(result, HairpinMachineReadCardResponse.class);
                    break;
                case MessageDefine.ResponseCmd.HAIRPIN_MACHINE_LOCATION:
                    message = JSON.parseObject(result, HairpinMachineLocationResponse.class);
                    break;
            }
            if (message != null) {
                int messageID = message.getMessageID();
                if (messageID >= 0 && webSocketCallbackHashtable.containsKey(messageID)) {
                    webSocketCallbackHashtable.remove(messageID).onWebSocketCallback(message);
                }
            }
        }

        @Override
        public void onClose(int i, String s, boolean b) {

        }

        @Override
        public void onError(Exception e) {

        }
    }
}
