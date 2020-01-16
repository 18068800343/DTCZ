package com.ldxx.controller;

import com.alibaba.fastjson.JSONObject;
import com.ldxx.bean.CruxWeight;
import com.ldxx.bean.VehicleType;
import com.ldxx.service.CruxWeightService;
import com.ldxx.util.LDXXUtils;
import com.ldxx.util.MsgFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("CruxWeight")
public class CruxWeightController {

    @Autowired
    private CruxWeightService service;

    @RequestMapping("/getCruxWeight")
    public List<CruxWeight> getCruxWeight() {
        return service.getCruxWeight();
    }

    @RequestMapping("/addCruxWeight")
    public String addCruxWeight(@RequestBody CruxWeight CruxWeight) {
        JSONObject jsonObject=new JSONObject();
        String id = LDXXUtils.getUUID12();
        CruxWeight.setCruxWeightId(id);
        CruxWeight.setDelState(1);
        int i=service.addCruxWeight(CruxWeight);
        String daoMsg = MsgFormatUtils.getMsgByResult(i, "新增");
        jsonObject.put("resultMsg",daoMsg);
        jsonObject.put("daoMsg",i);
        jsonObject.put("obj",CruxWeight);
        return jsonObject.toJSONString();

    }

    @RequestMapping("/updCruxWeight")
    public String updCruxWeight(@RequestBody CruxWeight CruxWeight) {
        JSONObject jsonObject=new JSONObject();
        int i=service.updCruxWeight(CruxWeight);
        String daoMsg = MsgFormatUtils.getMsgByResult(i, "更新");
        jsonObject.put("resultMsg",daoMsg);
        jsonObject.put("daoMsg",i);
        jsonObject.put("obj",CruxWeight);
        return jsonObject.toJSONString();
    }

    @RequestMapping("/delCruxWeight")
    public int delCruxWeight(String id) {
        int i= service.delCruxWeight(id);
        return i;
    }

}
