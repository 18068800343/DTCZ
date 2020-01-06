package com.ldxx.bean;

/**
 * 测点
 */
public class StationSite {
    private String StationId;
    private String StationName;
    //ip地址
    private String StationIp;
    //端口号
    private String StationPort;
    //经度
    private String StationLongitude;
    //纬度
    private String StationLatitude;

    private String DelState;

    public String getDelState() {
        return DelState;
    }

    public void setDelState(String delState) {
        DelState = delState;
    }

    public String getStationId() {
        return StationId;
    }

    public void setStationId(String stationId) {
        StationId = stationId;
    }

    public String getStationName() {
        return StationName;
    }

    public void setStationName(String stationName) {
        StationName = stationName;
    }

    public String getStationIp() {
        return StationIp;
    }

    public void setStationIp(String stationIp) {
        StationIp = stationIp;
    }

    public String getStationPort() {
        return StationPort;
    }

    public void setStationPort(String stationPort) {
        StationPort = stationPort;
    }

    public String getStationLongitude() {
        return StationLongitude;
    }

    public void setStationLongitude(String stationLongitude) {
        StationLongitude = stationLongitude;
    }

    public String getStationLatitude() {
        return StationLatitude;
    }

    public void setStationLatitude(String stationLatitude) {
        StationLatitude = stationLatitude;
    }
}
