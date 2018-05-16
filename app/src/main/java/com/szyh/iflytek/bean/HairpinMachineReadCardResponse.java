package com.szyh.iflytek.bean;

/**
 * Created by Administrator on 2018/5/16.
 */

public class HairpinMachineReadCardResponse extends DefaultResponse {
    private String cardNo1;
    private String cardNo2;
    private String cardNo3;

    public HairpinMachineReadCardResponse() {

    }

    public String getCardNo1() {
        return cardNo1;
    }

    public void setCardNo1(String cardNo1) {
        this.cardNo1 = cardNo1;
    }

    public String getCardNo2() {
        return cardNo2;
    }

    public void setCardNo2(String cardNo2) {
        this.cardNo2 = cardNo2;
    }

    public String getCardNo3() {
        return cardNo3;
    }

    public void setCardNo3(String cardNo3) {
        this.cardNo3 = cardNo3;
    }
}
