package com.szyh.iflytek.bean;

import com.szyh.iflytek.define.MessageDefine;

/**
 * 打印二维码--请求
 */

public class QRCodePrintRequest extends Message {

    /**
     * 文本内容
     */
    private String text;

    /**
     * 打印浓度 0--30
     */
    private byte darkness;

    /**
     * 文本横坐标 英寸
     */
    private double dXPos;
    /**
     * 文本纵坐标 英寸
     */
    private double dYPos;
    /**
     * 二维码边长 英寸
     */
    private double qrSize;

    public QRCodePrintRequest() {
        setCmd(MessageDefine.RequestCmd.QR_CODE_PRINT);
    }

    public QRCodePrintRequest(String text, byte darkness, double dXPos, double dYPos, double qrSize) {
        this.text = text;
        this.darkness = darkness;
        this.dXPos = dXPos;
        this.dYPos = dYPos;
        this.qrSize = qrSize;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte getDarkness() {
        return darkness;
    }

    public void setDarkness(byte darkness) {
        this.darkness = darkness;
    }

    public double getdXPos() {
        return dXPos;
    }

    public void setdXPos(double dXPos) {
        this.dXPos = dXPos;
    }

    public double getdYPos() {
        return dYPos;
    }

    public void setdYPos(double dYPos) {
        this.dYPos = dYPos;
    }

    public double getQrSize() {
        return qrSize;
    }

    public void setQrSize(double qrSize) {
        this.qrSize = qrSize;
    }
}
