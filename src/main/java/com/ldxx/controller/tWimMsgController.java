package com.ldxx.controller;

import com.ldxx.bean.tUserInfo;
import com.ldxx.dao.tUserInfoDao;
import com.ldxx.service.tWimMsgService;
import com.ldxx.vo.tWimMsgVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("tWimMsg")
public class tWimMsgController {

    @Autowired
    private tWimMsgService service;
    @Resource
    private tUserInfoDao tUserInfoDao;

    @RequestMapping("/getAlltWimMsg")
    public List<tWimMsgVo> getAlltWimMsg(HttpSession session,String  stationPort) {
        String zhandianduankouhao="";
        tUserInfo user = (tUserInfo) session.getAttribute("user");
        if(stationPort!=null&&stationPort!=""){
            zhandianduankouhao=stationPort;
            updlastMonitoringSiteById(stationPort,user.getUsrId());
        }else{
            if(user!=null){
                zhandianduankouhao = user.getStationPort();
                updlastMonitoringSiteById(user.getStationPort(),user.getUsrId());
            }
        }
        List<tWimMsgVo> list= service.getAlltWimMsg(zhandianduankouhao);
        return list;
    }

    private int  updlastMonitoringSiteById(String stationPort,String usrId){
        int i=tUserInfoDao.updlastMonitoringSiteById(stationPort,usrId);
        return i;
    }

    @RequestMapping("/getAlltWimMsgByCondiTion")
    public List<tWimMsgVo> getAlltWimMsgByCondiTion(HttpSession session,String  stationPort,String startTime,String endTime,Double startWeight,Double endWeight) {
        String zhandianduankouhao="";
        if(stationPort!=null&&stationPort!=""){
            zhandianduankouhao=stationPort;
        }else{
            tUserInfo user = (tUserInfo) session.getAttribute("user");
            zhandianduankouhao = user.getStationPort();
        }
        Double mid = 0.0;
        if(startWeight>endWeight){
            mid=startWeight;
            startWeight = endWeight;
            endWeight = mid;
        }
        List<tWimMsgVo> list= service.getAlltWimMsgByCondition(zhandianduankouhao,startTime,endTime,startWeight,endWeight);
        return list;
    }
}
