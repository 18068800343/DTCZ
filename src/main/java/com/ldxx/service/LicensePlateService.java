package com.ldxx.service;

import com.ldxx.bean.LicensePlate;

import java.util.List;

public interface LicensePlateService {

    List<LicensePlate> getLicensePlate();

    int addLicensePlate(LicensePlate LicensePlate);

    int updLicensePlate(LicensePlate LicensePlate);

    int delLicensePlate(String id);

    int iscountLicensePlateColor(String color);

    int iscountLicensePlateNo(String no);

    int xgiscountLicensePlateColor(String color,String id);

    int xgiscountLicensePlateNo(String no,String id);
}
