package com.szyh.iflytek.bean;

/**
 * author  ruanhouli
 * email   ruanhouli@szyh-smart.com
 * created 2018/11/8 10:04
 * remark  发卡机传感器状态响应
 */

public class HairpinMachineSensorStatusResponse extends DefaultResponse {
    /**
     * 0x31, 有卡；=0x30无卡
     */
    private int pss1;
    /**
     * 0x31, 有卡；=0x30无卡
     */
    private int pss2;
    /**
     * 0x31, 有卡；=0x30无卡
     */
    private int pss3;
    /**
     * 0x31, 有卡；=0x30无卡
     */
    private int pss4;
    /**
     * 0x31, 有卡；=0x30无卡
     */
    private int pss5;
    /**
     * 闸门传感器  未使用
     */
    private int pss6;
    /**
     * 0x31, 卡箱到位；=0x30卡箱未到位
     * 卡箱传感器
     */
    private int pss7;
    /**
     * 0x31, 回收箱到位；=0x30回收箱未到位
     * 回收箱传感
     */
    private int pss8;
    /**
     * 0x31, 有卡；=0x30少卡
     * 卡预空传感器[新增传感器状态 2018年 11月7日]
     */
    private int pss9;

    public HairpinMachineSensorStatusResponse() {

    }

    public int getPss1() {
        return pss1;
    }

    public void setPss1(int pss1) {
        this.pss1 = pss1;
    }

    public int getPss2() {
        return pss2;
    }

    public void setPss2(int pss2) {
        this.pss2 = pss2;
    }

    public int getPss3() {
        return pss3;
    }

    public void setPss3(int pss3) {
        this.pss3 = pss3;
    }

    public int getPss4() {
        return pss4;
    }

    public void setPss4(int pss4) {
        this.pss4 = pss4;
    }

    public int getPss5() {
        return pss5;
    }

    public void setPss5(int pss5) {
        this.pss5 = pss5;
    }

    public int getPss6() {
        return pss6;
    }

    public void setPss6(int pss6) {
        this.pss6 = pss6;
    }

    public int getPss7() {
        return pss7;
    }

    public void setPss7(int pss7) {
        this.pss7 = pss7;
    }

    public int getPss8() {
        return pss8;
    }

    public void setPss8(int pss8) {
        this.pss8 = pss8;
    }

    public int getPss9() {
        return pss9;
    }

    public void setPss9(int pss9) {
        this.pss9 = pss9;
    }
}
