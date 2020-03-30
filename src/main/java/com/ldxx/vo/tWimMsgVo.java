package com.ldxx.vo;

import com.ldxx.bean.tWimMsg;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

public class tWimMsgVo extends tWimMsg  implements Serializable{
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

    private String imgprefixurl;//图片url

    private String deviceNo;//称重检测设备编号 编码规则 6 位行政区 划码+4 位点编号
    private Integer vehicleWeight;//车货总质量单位: 千克 数字类型
    private String plateImg1;//车牌图片base64二进制码
    private String plateImg2;//车牌正面照图片base64二进制码

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public Integer getVehicleWeight() {
        return vehicleWeight;
    }

    public void setVehicleWeight(Integer vehicleWeight) {
        this.vehicleWeight = vehicleWeight;
    }

    public String getPlateImg1() {
        return plateImg1;
    }

    public void setPlateImg1(String plateImg1) {
        this.plateImg1 = plateImg1;
    }

    public String getPlateImg2() {
        return plateImg2;
    }

    public void setPlateImg2(String plateImg2) {
        this.plateImg2 = plateImg2;
    }

    public String getImgprefixurl() {
        return imgprefixurl;
    }

    public void setImgprefixurl(String imgprefixurl) {
        this.imgprefixurl = imgprefixurl;
    }

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

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String evtTime = sdf.format(getEvtTime());
        return stationName+","+laneNoName+
                ","+getPlate()+","+licensePlateColor+","+getLength()+","+getSpeed()+","+getAxlesCount()+
                ","+getTotalWeight()+","+getTemperature()+","+overWeightRatioName+","+evtTime;
    }
}
