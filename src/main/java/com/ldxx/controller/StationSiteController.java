package com.ldxx.controller;

import com.alibaba.fastjson.JSONObject;
import com.ldxx.bean.StationSite;
import com.ldxx.service.StationSiteService;
import com.ldxx.util.LDXXUtils;
import com.ldxx.util.MsgFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("StationSite")
public class StationSiteController {

    @Autowired
    private StationSiteService service;

    @RequestMapping("/getAllStationSite")
    public List<StationSite> getAllStationSite(){
        List<StationSite> list = service.getAllStationSite();
        return list;
    }

    @RequestMapping("/getStationSiteById")
    public StationSite getStationSiteById(String id) {
        return service.getStationSiteById(id);
    }

    @RequestMapping("/addStationSite")
    public String addStationSite(@RequestBody StationSite StationSite) {
        JSONObject jsonObject = new JSONObject();
        String id = LDXXUtils.getUUID12();
        int i;
        int iscountStationId=service.iscountStationId(StationSite.getStationId());
        int iscountStationName=service.iscountStationName(StationSite.getStationName());
        int iscountStationIp=service.iscountStationIp(StationSite.getStationIp());
        int iscountStationPort=service.iscountStationPort(StationSite.getStationPort());
        if(iscountStationId>0){
            i=-1;
        }else if(iscountStationName>0){
            i=-2;
        }else if(iscountStationIp>0){
            i=-3;
        }else if(iscountStationPort>0){
            i=-4;
        }else{
            StationSite.setsId(id);
            StationSite.setDelState(1);
            i= service.addStationSite(StationSite);
        }
        String daoMsg = MsgFormatUtils.getMsgByResult(i, "新增");
        jsonObject.put("resultMsg",daoMsg);
        jsonObject.put("daoMsg",i);
        jsonObject.put("obj",StationSite);
        return jsonObject.toJSONString();
    }

    @RequestMapping("/updStationSite")
    public String updStationSite(@RequestBody StationSite StationSite) {
        JSONObject jsonObject = new JSONObject();
        int i;
        int iscountStationId=service.xg_iscountStationId(StationSite.getStationId(),StationSite.getsId());
        int iscountStationName=service.xg_iscountStationName(StationSite.getStationName(),StationSite.getsId());
        int iscountStationIp=service.xg_iscountStationIp(StationSite.getStationIp(),StationSite.getsId());
        int iscountStationPort=service.xg_iscountStationPort(StationSite.getStationPort(),StationSite.getsId());
        if(iscountStationId>0){
            i=-1;
        }else if(iscountStationName>0){
            i=-2;
        }else if(iscountStationIp>0){
            i=-3;
        }else if(iscountStationPort>0){
            i=-4;
        }else{
            i= service.updStationSite(StationSite);
        }
        String daoMsg = MsgFormatUtils.getMsgByResult(i, "更新");
        jsonObject.put("resultMsg",daoMsg);
        jsonObject.put("daoMsg",i);
        jsonObject.put("obj",StationSite);
        return jsonObject.toJSONString();
    }

    @RequestMapping("/delStationSite")
    public int delStationSite(String id) {
        return service.delStationSite(id);
    }
}
