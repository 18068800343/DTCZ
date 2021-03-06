package com.ldxx.bean;

/**
 * Xxxx称重系统设备更换记录表
 */
public class tMaintenanceReplaceLog {
    private String logReplaceId;
    private String equipmentName;
    private String equipmentPositionNumber;
    private String replaceTime;
    private String replaceReason;
    private int delState;//1存在0删除
    private String logReplaceUser;//维护人

    private String stationIP;//站点端口号
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

    public String getLogReplaceUser() {
        return logReplaceUser;
    }

    public void setLogReplaceUser(String logReplaceUser) {
        this.logReplaceUser = logReplaceUser;
    }

    public int getDelState() {
        return delState;
    }

    public void setDelState(int delState) {
        this.delState = delState;
    }

    public String getLogReplaceId() {
        return logReplaceId;
    }

    public void setLogReplaceId(String logReplaceId) {
        this.logReplaceId = logReplaceId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentPositionNumber() {
        return equipmentPositionNumber;
    }

    public void setEquipmentPositionNumber(String equipmentPositionNumber) {
        this.equipmentPositionNumber = equipmentPositionNumber;
    }

    public String getReplaceTime() {
        return replaceTime;
    }

    public void setReplaceTime(String replaceTime) {
        this.replaceTime = replaceTime;
    }

    public String getReplaceReason() {
        return replaceReason;
    }

    public void setReplaceReason(String replaceReason) {
        this.replaceReason = replaceReason;
    }
}
