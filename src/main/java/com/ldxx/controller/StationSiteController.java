package com.ldxx.controller;

import com.alibaba.fastjson.JSONObject;
import com.ldxx.bean.CompanySite;
import com.ldxx.bean.StationSite;
import com.ldxx.bean.tUserInfo;
import com.ldxx.dao.StationSiteDao;
import com.ldxx.service.StationSiteService;
import com.ldxx.util.LDXXUtils;
import com.ldxx.util.MsgFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("StationSite")
public class StationSiteController {

    @Autowired
    private StationSiteService service;

    @Resource
    private StationSiteDao stationSiteDao;

    @RequestMapping("/getAllStationSite")
    public List<StationSite> getAllStationSite(){
        List<StationSite> list = service.getAllStationSite();
        return list;
    }

    @RequestMapping("/getStationSiteById")
    public StationSite getStationSiteById(String id) {
        return service.getStationSiteById(id);
    }

    @RequestMapping("/getCompanyStationSite")
    public List<CompanySite> getCompanyStationSite() {
        return stationSiteDao.getCompanyStation();
    }

    @RequestMapping("/addStationSite")
    public String addStationSite(@RequestBody StationSite StationSite) {
        JSONObject jsonObject = new JSONObject();
        String id = LDXXUtils.getUUID12();
        int i;
        int iscountStationIp=0;
        if(StationSite.getStationIp()!=""&&StationSite.getStationIp()!=null){
            iscountStationIp=service.iscountStationIp(StationSite.getStationIp());
        }
        int iscountStationPort=0;
        if(StationSite.getStationPort()!=""&&StationSite.getStationPort()!=null){
            iscountStationPort=service.iscountStationPort(StationSite.getStationPort());
        }
        if(iscountStationIp>0){
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

        int iscountStationIp=0;
        if(StationSite.getStationIp()!=""&&StationSite.getStationIp()!=null){
            iscountStationIp=service.xg_iscountStationIp(StationSite.getStationIp(),StationSite.getsId());
        }
        int iscountStationPort=0;
        if(StationSite.getStationPort()!=""&&StationSite.getStationPort()!=null){
            iscountStationPort=service.xg_iscountStationPort(StationSite.getStationPort(),StationSite.getsId());
        }
        if(iscountStationIp>0){
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


    @RequestMapping("/delCompaySite")
    public int delCompaySite(String id) {
        return stationSiteDao.delCompanySite(id);
    }

    @RequestMapping("/addCompanySite")
    public String addCompanySite(@RequestBody CompanySite companySite) {
        JSONObject jsonObject=new JSONObject();
        String id = LDXXUtils.getUUID12();
        int i=stationSiteDao.addCompanySite(companySite);
        companySite.setStationName("");
        String daoMsg = MsgFormatUtils.getMsgByResult(i, "新增");
        jsonObject.put("resultMsg",daoMsg);
        jsonObject.put("daoMsg",i);
        jsonObject.put("obj",companySite);
        return jsonObject.toString();
    }

    /**
     * 通过登录人的站点端口号查询站点信息
     * @param stationPort
     * @return
     */
    @RequestMapping("/getStationSiteByPort")
    public List<StationSite> getStationSiteByPort(HttpSession session, String stationPort) {
        String zhandianduankouhao="";
        if(stationPort!=null){
            zhandianduankouhao=stationPort;
        }else{
            tUserInfo user = (tUserInfo) session.getAttribute("user");
            zhandianduankouhao = user.getStationPort();
        }
        return service.getStationSiteByPort(stationPort);
    }
}
