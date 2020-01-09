package com.ldxx.vo;

import com.ldxx.bean.tWimMsg;

public class tWimMsgVo extends tWimMsg {
    private String stationNameRouteName;//站点名称+路线名称
    private String devTypeName;//称重设备类型名称
    private String directionName;//行驶方向名称
    private String isStraddleName;//跨道
    private String drawBarTrailerName;//牵引杆挂车
    private String stationName;//站点名称
    private String routeName;//路线名称

    public String getStationNameRouteName() {
        return stationNameRouteName;
    }

    public void setStationNameRouteName(String stationNameRouteName) {
        this.stationNameRouteName = stationNameRouteName;
    }

    public String getDevTypeName() {
        return devTypeName;
    }

    public void setDevTypeName(String devTypeName) {
        this.devTypeName = devTypeName;
    }

    public String getDirectionName() {
        return directionName;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }

    public String getIsStraddleName() {
        return isStraddleName;
    }

    public void setIsStraddleName(String isStraddleName) {
        this.isStraddleName = isStraddleName;
    }

    public String getDrawBarTrailerName() {
        return drawBarTrailerName;
    }

    public void setDrawBarTrailerName(String drawBarTrailerName) {
        this.drawBarTrailerName = drawBarTrailerName;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }
}
