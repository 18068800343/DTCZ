package com.ldxx.vo;

import com.ldxx.bean.tUserInfo;

public class tUserInfoVo extends tUserInfo {

    private Double lng;
    private Double lat;

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }
}
