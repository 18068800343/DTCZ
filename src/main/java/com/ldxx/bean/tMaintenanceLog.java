package com.ldxx.bean;

import java.util.List;

/**
 * 系统维护日志
 */
public class tMaintenanceLog {

    private String logId;
    private String logTime;
    private String logUser;
    private String logContent;
    private String delState;//1存在0删除

    private List<Accessory> accessory;
    private String usrName;

    public String getDelState() {
        return delState;
    }

    public void setDelState(String delState) {
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

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }
}
