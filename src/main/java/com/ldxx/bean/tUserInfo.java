package com.ldxx.bean;

/**
 * 人员用户表
 */
public class tUserInfo {
    private String usrId;
    private String usrUname;
    private String usrName;
    private String usrPwd;
    private String usrSex;
    private String usrPhone;
    //用户公司单位
    private String usrRole;
    //站点名字
    private String stationPort;
    //站点名称
    private String stationName;
    //上次登录监测的站点
    private String lastMonitoringSite;
    //1:在编人员；0：已删人员
    private Integer delState;
    //权限编码
    private String UsrPersmissionCoding;

    public String getUsrUname() {
        return usrUname;
    }

    public void setUsrUname(String usrUname) {
        this.usrUname = usrUname;
    }

    public String getUsrPersmissionCoding() {
        return UsrPersmissionCoding;
    }

    public void setUsrPersmissionCoding(String usrPersmissionCoding) {
        UsrPersmissionCoding = usrPersmissionCoding;
    }

    public String getLastMonitoringSite() {
        return lastMonitoringSite;
    }

    public void setLastMonitoringSite(String lastMonitoringSite) {
        this.lastMonitoringSite = lastMonitoringSite;
    }

    public String getUsrId() {
        return usrId;
    }

    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String getUsrPwd() {
        return usrPwd;
    }

    public void setUsrPwd(String usrPwd) {
        this.usrPwd = usrPwd;
    }

    public String getUsrSex() {
        return usrSex;
    }

    public void setUsrSex(String usrSex) {
        this.usrSex = usrSex;
    }

    public String getUsrPhone() {
        return usrPhone;
    }

    public void setUsrPhone(String usrPhone) {
        this.usrPhone = usrPhone;
    }

    public String getUsrRole() {
        return usrRole;
    }

    public void setUsrRole(String usrRole) {
        this.usrRole = usrRole;
    }

    public String getStationPort() {
        return stationPort;
    }

    public void setStationPort(String stationPort) {
        this.stationPort = stationPort;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Integer getDelState() {
        return delState;
    }

    public void setDelState(Integer delState) {
        this.delState = delState;
    }
}
