package com.ldxx.bean;

public class CheLiuLiangEchartsList {

    private String nums;//站点超载数量
    private String stationPorts;
    private String stationNames;
    private String numCount;//站点总车流量
    private String numsBili;//站点超载比例
    private String nums2;//站点总车流量-站点超载数量
    private String cnt2;//关键车辆二级预警数量
    private String cnt3;//关键车辆三级预警数量

    public String getNumsBili() {
        return numsBili;
    }

    public void setNumsBili(String numsBili) {
        this.numsBili = numsBili;
    }

    public String getCnt3() {
        return cnt3;
    }

    public void setCnt3(String cnt3) {
        this.cnt3 = cnt3;
    }

    public String getCnt2() {
        return cnt2;
    }

    public void setCnt2(String cnt2) {
        this.cnt2 = cnt2;
    }

    public String getNumCount() {
        return numCount;
    }

    public void setNumCount(String numCount) {
        this.numCount = numCount;
    }

    public String getNums2() {
        return nums2;
    }

    public void setNums2(String nums2) {
        this.nums2 = nums2;
    }

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }

    public String getStationPorts() {
        return stationPorts;
    }

    public void setStationPorts(String stationPorts) {
        this.stationPorts = stationPorts;
    }

    public String getStationNames() {
        return stationNames;
    }

    public void setStationNames(String stationNames) {
        this.stationNames = stationNames;
    }
}
