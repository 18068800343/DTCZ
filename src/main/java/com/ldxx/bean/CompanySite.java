package com.ldxx.bean;

/**
 * 单位管理
 */
public class CompanySite {
    private Integer id;
    private String companyName;
    private String stationPort;
    private String stationName;
    private String groups;

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
}
