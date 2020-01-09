package com.ldxx.controller;

import com.alibaba.fastjson.JSONObject;
import com.ldxx.bean.LicensePlate;
import com.ldxx.bean.VehicleType;
import com.ldxx.service.VehicleTypeService;
import com.ldxx.util.LDXXUtils;
import com.ldxx.util.MsgFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("VehicleType")
public class VehicleTypeController {

    @Autowired
    private VehicleTypeService service;

    @RequestMapping("/getVehicleType")
    public List<VehicleType> getVehicleType() {
        return service.getVehicleType();
    }

    @RequestMapping("/addVehicleType")
    public String addVehicleType(@RequestBody VehicleType VehicleType) {
        JSONObject jsonObject=new JSONObject();
        String id = LDXXUtils.getUUID12();
        int i=0;
        int iscountvehicleTypeName=service.iscountvehicleTypeName(VehicleType.getVehicleTypeName());
        int iscountvehicleTypeNo=service.iscountvehicleTypeNo(VehicleType.getVehicleTypeNo());
        if(iscountvehicleTypeName>0){
            i=-1;
        }else if(iscountvehicleTypeNo>0){
            i=-2;
        }else{
            VehicleType.setVehicleTypeId(id);
            VehicleType.setDelState(1);
            i=service.addVehicleType(VehicleType);
        }
        String daoMsg = MsgFormatUtils.getMsgByResult(i, "新增");
        jsonObject.put("resultMsg",daoMsg);
        jsonObject.put("daoMsg",i);
        jsonObject.put("obj",VehicleType);
        return jsonObject.toJSONString();
    }

    @RequestMapping("/updVehicleType")
    public String updVehicleType(@RequestBody VehicleType VehicleType) {
        JSONObject jsonObject=new JSONObject();
        int i=0;
        int xgiscountvehicleTypeName=service.xgiscountvehicleTypeName(VehicleType.getVehicleTypeName(),VehicleType.getVehicleTypeId());
        int xgiscountvehicleTypeNo=service.xgiscountvehicleTypeNo(VehicleType.getVehicleTypeNo(),VehicleType.getVehicleTypeId());
        if(xgiscountvehicleTypeName>0){
            i=-1;
        }else if(xgiscountvehicleTypeNo>0){
            i=-2;
        }else{
            i=service.updVehicleType(VehicleType);
        }
        String daoMsg = MsgFormatUtils.getMsgByResult(i, "更新");
        jsonObject.put("resultMsg",daoMsg);
        jsonObject.put("daoMsg",i);
        jsonObject.put("obj",VehicleType);
        return jsonObject.toJSONString();
    }

    @RequestMapping("/delLicensePlate")
    public int delLicensePlate(String id) {
        return service.delLicensePlate(id);
    }
}
