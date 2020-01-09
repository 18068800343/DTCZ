package com.ldxx.controller;

import com.ldxx.bean.tUserInfo;
import com.ldxx.service.tWimMsgService;
import com.ldxx.vo.tWimMsgVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("tWimMsg")
public class tWimMsgController {

    @Autowired
    private tWimMsgService service;

    @RequestMapping("/getAlltWimMsg")
    public List<tWimMsgVo> getAlltWimMsg(HttpSession session,String  stationPort) {
        String zhandianduankouhao="";
        if(stationPort!=null&&stationPort!=""){
            zhandianduankouhao=stationPort;
        }else{
            tUserInfo user = (tUserInfo) session.getAttribute("user");
            if(user!=null){
                zhandianduankouhao = user.getStationPort();
            }
        }
        List<tWimMsgVo> list= service.getAlltWimMsg(zhandianduankouhao);
        return list;
    }

    @RequestMapping("/getAlltWimMsgByCondiTion")
    public List<tWimMsgVo> getAlltWimMsgByCondiTion(HttpSession session,String  stationPort,String startTime,String endTime) {
        String zhandianduankouhao="";
        if(stationPort!=null&&stationPort!=""){
            zhandianduankouhao=stationPort;
        }else{
            tUserInfo user = (tUserInfo) session.getAttribute("user");
            zhandianduankouhao = user.getStationPort();
        }
        List<tWimMsgVo> list= service.getAlltWimMsgByCondition(zhandianduankouhao,startTime,endTime);
        return list;
    }
}