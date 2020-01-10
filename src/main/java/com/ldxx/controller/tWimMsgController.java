package com.ldxx.controller;

import com.ldxx.bean.StationSite;
import com.ldxx.bean.tUserInfo;
import com.ldxx.dao.StationSiteDao;
import com.ldxx.dao.tUserInfoDao;
import com.ldxx.service.tWimMsgService;
import com.ldxx.vo.ChaoZaiVo;
import com.ldxx.vo.tWimMsgVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("tWimMsg")
public class tWimMsgController {

    @Autowired
    private tWimMsgService service;
    @Resource
    private tUserInfoDao tUserInfoDao;
    @Autowired
    private StationSiteDao sitedao;

    @RequestMapping("/getAlltWimMsg")
    public List<tWimMsgVo> getAlltWimMsg(HttpSession session,String  stationPort) {
        String zhandianduankouhao="";
        tUserInfo user = (tUserInfo) session.getAttribute("user");
        if(stationPort!=null&&stationPort!=""){
            zhandianduankouhao=stationPort;
            if(user!=null){
                updlastMonitoringSiteById(stationPort,user.getUsrId());
            }
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

    /**
     * 每日超流量
     * @param stationPort
     * @return
     */
    @RequestMapping("/getMeiRiCheLiuLiangByStationPort")
    public List<Integer> getMeiRiCheLiuLiangByStationPort(String stationPort) {
        List<Integer> list=new ArrayList<Integer>();
        if(stationPort!=null){
            String[] split = stationPort.split(",");
            for (String sport:split){
                int i=service.getMeiRiCheLiuLiangByStationPort(sport);
                list.add(i);
            }
        }
        return list;
    }

    @RequestMapping("/getMeiRiChaoZhongByStationPort")
    public List<ChaoZaiVo> getMeiRiChaoZhongByStationPort(String stationPort) {
        List<ChaoZaiVo> list=new ArrayList<ChaoZaiVo>();
        if(stationPort!=null){
            String[] split = stationPort.split(",");
            for (String sport:split){
                tWimMsgVo tWimMsgVo=new tWimMsgVo();
                ChaoZaiVo chaoZaiVo=new ChaoZaiVo();
                int i=service.getMeiRiChaoZhongByStationPort(sport);
                List<StationSite> stationSites = sitedao.getStationSiteByPort(sport);
                if(i>0){
                    chaoZaiVo.setValue(i);
                    chaoZaiVo.setName(stationSites.get(0).getStationName()+"+"+stationSites.get(0).getRouteName());
                    list.add(chaoZaiVo);
                }
            }
        }
        return list;
    }
}
