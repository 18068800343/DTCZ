package com.ldxx.vo;

import com.ldxx.bean.tUserInfo;

public class tUserInfoVo extends tUserInfo {

    private String lnglat;

    private  String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLnglat() {
        return lnglat;
    }

    public void setLnglat(String lnglat) {
        this.lnglat = lnglat;
    }
}
