package com.ldxx.bean;

/**
 * 车牌颜色字典表
 */
public class LicensePlate {

    private String licensePlateId;
    private String licensePlateNo;
    //车牌颜色
    private String licensePlateColor;

    private Integer delState;

    public String getLicensePlateNo() {
        return licensePlateNo;
    }

    public void setLicensePlateNo(String licensePlateNo) {
        this.licensePlateNo = licensePlateNo;
    }

    public Integer getDelState() {
        return delState;
    }

    public void setDelState(Integer delState) {
        this.delState = delState;
    }

    public String getLicensePlateId() {
        return licensePlateId;
    }

    public void setLicensePlateId(String licensePlateId) {
        this.licensePlateId = licensePlateId;
    }

    public String getLicensePlateColor() {
        return licensePlateColor;
    }

    public void setLicensePlateColor(String licensePlateColor) {
        this.licensePlateColor = licensePlateColor;
    }
}
