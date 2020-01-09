package com.ldxx.vo;

import com.ldxx.bean.tWimMsg;

public class tWimMsgVo extends tWimMsg {
    private String stationNameRouteName;//站点名称+路线名称
    private String directionName;//行驶方向名称
    private String isStraddleName;//跨道
    private String stationName;//站点名称
    private String routeName;//路线名称
    private String licensePlateColor;//车牌颜色
    private String vehicleTypeName;//车型
    private double overWeightRatio2;


    public double getOverWeightRatio2() {
        return overWeightRatio2;
    }

    public void setOverWeightRatio2(double overWeightRatio2) {
        this.overWeightRatio2 = overWeightRatio2;
    }

    public String getStationNameRouteName() {
        return stationNameRouteName;
    }

    public void setStationNameRouteName(String stationNameRouteName) {
        this.stationNameRouteName = stationNameRouteName;
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

    public String getLicensePlateColor() {
        return licensePlateColor;
    }

    public void setLicensePlateColor(String licensePlateColor) {
        this.licensePlateColor = licensePlateColor;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }
}
