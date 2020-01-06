package com.ldxx.controller;

import com.alibaba.fastjson.JSONObject;
import com.ldxx.bean.LicensePlate;
import com.ldxx.bean.StationSite;
import com.ldxx.service.LicensePlateService;
import com.ldxx.util.LDXXUtils;
import com.ldxx.util.MsgFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("LicensePlate")
public class LicensePlateController {

    @Autowired
    private LicensePlateService service;

    @RequestMapping("/getLicensePlate")
    public List<LicensePlate> getLicensePlate(){
        return service.getLicensePlate();
    }

    @RequestMapping("/addLicensePlate")
    public String addLicensePlate(@RequestBody LicensePlate LicensePlate) {
        JSONObject jsonObject=new JSONObject();
        String id = LDXXUtils.getUUID12();
        int i=0;
        int iscountLicensePlateColor=service.iscountLicensePlateColor(LicensePlate.getLicensePlateColor());
        if(iscountLicensePlateColor>0){
            i=-1;
        }else{
            LicensePlate.setLicensePlateId(id);
            LicensePlate.setDelState(1);
            i=service.addLicensePlate(LicensePlate);
        }
        String daoMsg = MsgFormatUtils.getMsgByResult(i, "新增");
        jsonObject.put("resultMsg",daoMsg);
        jsonObject.put("daoMsg",i);
        jsonObject.put("obj",LicensePlate);
        return jsonObject.toJSONString();
    }

    @RequestMapping("/updLicensePlate")
    public String updLicensePlate(@RequestBody LicensePlate LicensePlate) {
        JSONObject jsonObject=new JSONObject();
        int i=0;
        int xgiscountLicensePlateColor=service.xgiscountLicensePlateColor(LicensePlate.getLicensePlateColor(),LicensePlate.getLicensePlateId());
        if(xgiscountLicensePlateColor>0){
            i=-1;
        }else{
            i=service.updLicensePlate(LicensePlate);
        }
        String daoMsg = MsgFormatUtils.getMsgByResult(i, "更新");
        jsonObject.put("resultMsg",daoMsg);
        jsonObject.put("daoMsg",i);
        jsonObject.put("obj",LicensePlate);
        return jsonObject.toJSONString();
    }

    @RequestMapping("/delLicensePlate")
    public int delLicensePlate(String id) {
        return service.delLicensePlate(id);
    }
}
