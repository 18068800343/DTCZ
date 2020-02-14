package com.ldxx.vo;

import com.ldxx.bean.tWimMsg;

import java.math.BigDecimal;
import java.util.List;

public class tWimMsgVo extends tWimMsg {
    private String stationNameRouteName;//站点名称+路线名称
    private String directionName;//行驶方向名称
    private String isStraddleName;//跨道
    private String stationName;//站点名称
    private String routeName;//路线名称
    private String licensePlateColor;//车牌颜色
    private String vehicleTypeName;//车型
    private String laneNoName;//车道名称  1:超车道 ，2:行车道,3:应急车道
    private  String overWeightRatioName;//是否超重 如果 >0显示 超重 else  不超重
    private Integer totalCount;

    private List<Integer> count;//车流量数量
    private List<String> name;

    public String getOverWeightRatioName() {
        return overWeightRatioName;
    }

    public void setOverWeightRatioName(String overWeightRatioName) {
        this.overWeightRatioName = overWeightRatioName;
    }

    public String getLaneNoName() {
        return laneNoName;
    }

    public void setLaneNoName(String laneNoName) {
        this.laneNoName = laneNoName;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<Integer> getCount() {
        return count;
    }

    public void setCount(List<Integer> count) {
        this.count = count;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
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
