package com.ldxx.dao;

import com.ldxx.bean.LicensePlate;

import java.util.List;

public interface LicensePlateDao {

    List<LicensePlate> getLicensePlate();

    int addLicensePlate(LicensePlate LicensePlate);

    int updLicensePlate(LicensePlate LicensePlate);

    int delLicensePlate(String id);

    int iscountLicensePlateColor(String color);

    int xgiscountLicensePlateColor(String color,String id);
}
