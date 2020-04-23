package com.ldxx.bean;

import java.util.List;

/**
 * 系统维护日志
 */
public class tMaintenanceLog {

    private String logId;
    private String faultTime;//故障时间
    private String logTime;//维护时间
    private String logUser;//维护人
    private String logContent;//维护内容
    private int delState;//1存在0删除
    private String isChangeEquipment;//是否更换设备 1是，0否

    private String logRemarks;//备注
    private String stationIP;//站点端口号

    private List<Accessory> accessory;

    private String stationName;//站点

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationIP() {
        return stationIP;
    }

    public void setStationIP(String stationIP) {
        this.stationIP = stationIP;
    }



    public String getFaultTime() {
        return faultTime;
    }

    public void setFaultTime(String faultTime) {
        this.faultTime = faultTime;
    }

    public String getIsChangeEquipment() {
        return isChangeEquipment;
    }

    public void setIsChangeEquipment(String isChangeEquipment) {
        this.isChangeEquipment = isChangeEquipment;
    }

    public String getLogRemarks() {
        return logRemarks;
    }

    public void setLogRemarks(String logRemarks) {
        this.logRemarks = logRemarks;
    }



    public int getDelState() {
        return delState;
    }

    public void setDelState(int delState) {
        this.delState = delState;
    }

    public List<Accessory> getAccessory() {
        return accessory;
    }

    public void setAccessory(List<Accessory> accessory) {
        this.accessory = accessory;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public String getLogUser() {
        return logUser;
    }

    public void setLogUser(String logUser) {
        this.logUser = logUser;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }


}
