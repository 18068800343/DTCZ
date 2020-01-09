package com.ldxx.service;

import com.ldxx.bean.VehicleType;

import java.util.List;

public interface VehicleTypeService {

    List<VehicleType> getVehicleType();

    int addVehicleType(VehicleType VehicleType);

    int updVehicleType(VehicleType VehicleType);

    int delLicensePlate(String id);

    int iscountvehicleTypeName(String name);

    int xgiscountvehicleTypeName(String name,String id);

    int iscountvehicleTypeNo(String no);

    int xgiscountvehicleTypeNo(String no,String id);
}
