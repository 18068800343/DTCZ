package com.ldxx.controller;

import com.alibaba.fastjson.JSONObject;
import com.ldxx.bean.tUserInfo;
import com.ldxx.dao.tVehicleOverweightDao;
import com.ldxx.service.TVehicleOverweightService;
import com.ldxx.util.MsgFormatUtils;
import com.ldxx.vo.tWimMsgVo;
import com.ldxx.vo.ImgUrlPrefixVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("tVehicleOverweight")
@RestController
public class TVehicleOverweightController {

    @Autowired
    ImgUrlPrefixVo imgUrlPrefixVo;
    @Autowired
    private TVehicleOverweightService service;

    @RequestMapping("/getimgUrlPrefix")
    public String getimgUrlPrefix(){
        JSONObject jsonObject=new JSONObject();
        String imgUrlPrefix=imgUrlPrefixVo.getImgUrlPrefix();
        jsonObject.put("imgUrlPrefix",imgUrlPrefix);
        return jsonObject.toString();
    }

    @RequestMapping("/getMeiRiChaoZaiShujuByStationPortCondition")
    public List<tWimMsgVo> getMeiRiChaoZaiShujuByStationPortCondition(String stationPorts,String startTime,String endTime, HttpSession session){
        String zhandianduankouhao="";
        tUserInfo user = (tUserInfo) session.getAttribute("user");
        if(stationPorts!=null&&stationPorts!=""){
            zhandianduankouhao=stationPorts;
        }else{
            if(user!=null){
                zhandianduankouhao = user.getStationPort();
            }
        }
        return service.getMeiRiChaoZaiShujuByStationPortCondition(zhandianduankouhao,startTime,endTime);
    }

    @RequestMapping("/getMeiRiChaoZaiShujuByStationPortAlreadychuli")
    public List<tWimMsgVo> getMeiRiChaoZaiShujuByStationPortAlreadychuli(String stationPorts,String startTime,String endTime, HttpSession session){
        String zhandianduankouhao="";
        tUserInfo user = (tUserInfo) session.getAttribute("user");
        if(stationPorts!=null&&stationPorts!=""){
            zhandianduankouhao=stationPorts;
        }else{
            if(user!=null){
                zhandianduankouhao = user.getStationPort();
            }
        }
        return service.getMeiRiChaoZaiShujuByStationPortAlreadychuli(zhandianduankouhao,startTime,endTime);
    }


    @RequestMapping("/getMeiRiGuanJianChaoZHongShujuByStationPort")
    public List<tWimMsgVo> getMeiRiGuanJianChaoZHongShujuByStationPort(String stationPorts,Integer lv,String startTime,String endTime, HttpSession session){
        String zhandianduankouhao="";
        tUserInfo user = (tUserInfo) session.getAttribute("user");
        if(stationPorts!=null&&stationPorts!=""){
            zhandianduankouhao=stationPorts;
        }else{
            if(user!=null){
                zhandianduankouhao = user.getStationPort();
            }
        }
        return service.getMeiRiGuanJianChaoZHongShujuByStationPort(zhandianduankouhao,lv,startTime,endTime);
    }

    @RequestMapping("/getMeiRiGuanJianChaoZHongShujuByStationPortAlreadychuli")
    public List<tWimMsgVo> getMeiRiGuanJianChaoZHongShujuByStationPortAlreadychuli(String stationPorts,Integer lv,String startTime,String endTime, HttpSession session){
        String zhandianduankouhao="";
        tUserInfo user = (tUserInfo) session.getAttribute("user");
        if(stationPorts!=null&&stationPorts!=""){
            zhandianduankouhao=stationPorts;
        }else{
            if(user!=null){
                zhandianduankouhao = user.getStationPort();
            }
        }
        return service.getMeiRiGuanJianChaoZHongShujuByStationPortAlreadychuli(zhandianduankouhao,lv,startTime,endTime);
    }

    @RequestMapping("/addtVehicleOverweight")
    public String addtVehicleOverweight(String idLocal){
        JSONObject jsonObject=new JSONObject();
        int i=service.addtVehicleOverweightByidLocal(idLocal);
        String daoMsg = MsgFormatUtils.getMsgByResult(i, "处理");
        jsonObject.put("resultMsg",daoMsg);
        jsonObject.put("daoMsg",i);
        return jsonObject.toString();
    }

    @RequestMapping("/addtVehicleOverweightList")
    public String addtVehicleOverweightList(String startTime,String endTime ,HttpSession session,Integer lv){
        JSONObject jsonObject=new JSONObject();
        String zhandianduankouhao="";
        tUserInfo user = (tUserInfo) session.getAttribute("user");
        if(user!=null){
            zhandianduankouhao = user.getStationPort();
        }
        int i=service.addtVehicleOverweightList(zhandianduankouhao,startTime,endTime,lv);
        String daoMsg = MsgFormatUtils.getMsgByResult(i, "处理");
        jsonObject.put("resultMsg",daoMsg);
        jsonObject.put("daoMsg",i);
        return jsonObject.toString();
    }


    @RequestMapping("/delVehicleOverweightTemp")
    public int delVehicleOverweightTemp(List<tWimMsgVo> tWimMsgVoList){
        int i=service.delVehicleOverweightTemp(tWimMsgVoList);
        return i;
    }




}
