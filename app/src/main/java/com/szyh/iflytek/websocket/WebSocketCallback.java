package com.szyh.iflytek.websocket;

import com.szyh.iflytek.bean.Message;

/**
 * Created by Administrator on 2018/5/16.
 */

public interface WebSocketCallback {
    void onWebSocketCallback(Message message);
}
