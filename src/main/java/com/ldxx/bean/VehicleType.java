package com.ldxx.bean;

/**
 * 车型编码字典
 */
public class VehicleType {

    private String vehicleTypeId;
    private String vehicleTypeNo;
    //车型
    private String vehicleTypeName;
    //最大承载重量
    private Integer vehicleTypeMaxWeight;

    private Integer delState;

    public String getVehicleTypeNo() {
        return vehicleTypeNo;
    }

    public void setVehicleTypeNo(String vehicleTypeNo) {
        this.vehicleTypeNo = vehicleTypeNo;
    }

    public Integer getDelState() {
        return delState;
    }

    public void setDelState(Integer delState) {
        this.delState = delState;
    }

    public String getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(String vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

    public Integer getVehicleTypeMaxWeight() {
        return vehicleTypeMaxWeight;
    }

    public void setVehicleTypeMaxWeight(Integer vehicleTypeMaxWeight) {
        this.vehicleTypeMaxWeight = vehicleTypeMaxWeight;
    }
}
