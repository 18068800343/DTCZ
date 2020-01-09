package com.ldxx.service.Impl;

import com.ldxx.bean.VehicleType;
import com.ldxx.dao.VehicleTypeDao;
import com.ldxx.service.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleTypeServiceImpl implements VehicleTypeService {

    @Autowired
    private VehicleTypeDao dao;

    @Override
    public List<VehicleType> getVehicleType() {
        return dao.getVehicleType();
    }

    @Override
    public int addVehicleType(VehicleType VehicleType) {
        return dao.addVehicleType(VehicleType);
    }

    @Override
    public int updVehicleType(VehicleType VehicleType) {
        return dao.updVehicleType(VehicleType);
    }

    @Override
    public int delLicensePlate(String id) {
        return dao.delLicensePlate(id);
    }

    @Override
    public int iscountvehicleTypeName(String name) {
        return dao.iscountvehicleTypeName(name);
    }

    @Override
    public int xgiscountvehicleTypeName(String name, String id) {
        return dao.xgiscountvehicleTypeName(name,id);
    }

    @Override
    public int iscountvehicleTypeNo(String no) {
        return dao.iscountvehicleTypeNo(no);
    }

    @Override
    public int xgiscountvehicleTypeNo(String no, String id) {
        return dao.xgiscountvehicleTypeNo(no,id);
    }
}
