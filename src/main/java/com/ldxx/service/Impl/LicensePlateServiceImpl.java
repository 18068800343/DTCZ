package com.ldxx.service.Impl;

import com.ldxx.bean.LicensePlate;
import com.ldxx.dao.LicensePlateDao;
import com.ldxx.service.LicensePlateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LicensePlateServiceImpl implements LicensePlateService {

    @Autowired
    private LicensePlateDao dao;

    @Override
    public List<LicensePlate> getLicensePlate() {
        return dao.getLicensePlate();
    }

    @Override
    public int addLicensePlate(LicensePlate LicensePlate) {
        return dao.addLicensePlate(LicensePlate);
    }

    @Override
    public int updLicensePlate(LicensePlate LicensePlate) {
        return dao.updLicensePlate(LicensePlate);
    }

    @Override
    public int delLicensePlate(String id) {
        return dao.delLicensePlate(id);
    }

    @Override
    public int iscountLicensePlateColor(String color) {
        return dao.iscountLicensePlateColor(color);
    }

    @Override
    public int xgiscountLicensePlateColor(String color, String id) {
        return dao.xgiscountLicensePlateColor(color,id);
    }
}
