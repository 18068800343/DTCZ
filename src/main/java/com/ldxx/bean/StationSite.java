package com.ldxx.bean;

import java.util.List;

/**
 * 测点
 */
public class StationSite {
    private String sId;
    //站点Id
    private String stationId;
    //站点名称
    private String stationName;
    //路线名称
    private String routeName;
    //ip地址
    private String stationIp;
    //端口号
    private String stationPort;
    //经度
    private String stationLongitude;
    //纬度
    private String stationLatitude;
    //1:在编；0：已删
    private Integer delState;
    //单位
    private String company;
    //省界站名称
    private String provinceStationName;
    //省界站
    private String provinceStation;
    //掩码
    private String mask;
    //网关
    private String gateway;
    //车道数
    private int lanes;
    //省界站桩号
    private String provinceStationStakeMark;
    //系统位置桩号
    private String systemLocationStakeMark;
    //建成日期
    private String completionDate;
    //站点信息
    private String stationInfo;

    public String getProvinceStation() {
        return provinceStation;
    }

    public void setProvinceStation(String provinceStation) {
        this.provinceStation = provinceStation;
    }

    private List<CompanySite> companyList;
    private Accessory file;

    public Accessory getFile() {
        return file;
    }

    public void setFile(Accessory file) {
        this.file = file;
    }

    public List<CompanySite> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<CompanySite> companyList) {
        this.companyList = companyList;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getProvinceStationName() {
        return provinceStationName;
    }

    public void setProvinceStationName(String provinceStationName) {
        this.provinceStationName = provinceStationName;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public int getLanes() {
        return lanes;
    }

    public void setLanes(int lanes) {
        this.lanes = lanes;
    }

    public String getProvinceStationStakeMark() {
        return provinceStationStakeMark;
    }

    public void setProvinceStationStakeMark(String provinceStationStakeMark) {
        this.provinceStationStakeMark = provinceStationStakeMark;
    }

    public String getSystemLocationStakeMark() {
        return systemLocationStakeMark;
    }

    public void setSystemLocationStakeMark(String systemLocationStakeMark) {
        this.systemLocationStakeMark = systemLocationStakeMark;
    }

    public String getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(String completionDate) {
        this.completionDate = completionDate;
    }

    public String getStationInfo() {
        return stationInfo;
    }

    public void setStationInfo(String stationInfo) {
        this.stationInfo = stationInfo;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getsId() {
        return sId;
    }
    public void setsId(String sId) {
        this.sId = sId;
    }
    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationIp() {
        return stationIp;
    }

    public void setStationIp(String stationIp) {
        this.stationIp = stationIp;
    }

    public String getStationPort() {
        return stationPort;
    }

    public void setStationPort(String stationPort) {
        this.stationPort = stationPort;
    }

    public String getStationLongitude() {
        return stationLongitude;
    }

    public void setStationLongitude(String stationLongitude) {
        this.stationLongitude = stationLongitude;
    }

    public String getStationLatitude() {
        return stationLatitude;
    }

    public void setStationLatitude(String stationLatitude) {
        this.stationLatitude = stationLatitude;
    }

    public Integer getDelState() {
        return delState;
    }

    public void setDelState(Integer delState) {
        this.delState = delState;
    }
}
