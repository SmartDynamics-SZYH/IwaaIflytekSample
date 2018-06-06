package com.szyh.iflytek.websocket;

/**
 * Created by Administrator on 2018/6/6.
 */

public interface WebSocketStatusListener {

    void onOpen();

    void onClose();

    void onError();
}
