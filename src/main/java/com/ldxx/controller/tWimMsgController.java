package com.ldxx.controller;

import com.ldxx.bean.StationSite;
import com.ldxx.bean.tUserInfo;
import com.ldxx.dao.StationSiteDao;
import com.ldxx.dao.tUserInfoDao;
import com.ldxx.service.tWimMsgService;
import com.ldxx.vo.ChaoZaiVo;
import com.ldxx.vo.tWimMsgVo;
import org.apache.catalina.Session;
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
    @Resource
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

    @RequestMapping("/getWimMsgHomePage")
    public List<tWimMsgVo> getWimMsgHomePage(HttpSession session,String  roadName) {
        boolean b = roadName != null && !"".equals(roadName) && roadName.contains("+");
        String stationName = b ?roadName.split("/+")[0]:"";
        String routeName = b ?roadName.split("/+")[1]:"";
        StationSite stationSite = sitedao.getStationSiteByStationNameAndRouteName(stationName,routeName);
        String port = stationSite.getStationPort();
        List<tWimMsgVo> list= service.getAlltWimMsg(port);
        return list;
    }

    private int  updlastMonitoringSiteById(String stationPort,String usrId){
        int i=tUserInfoDao.updlastMonitoringSiteById(stationPort,usrId);
        return i;
    }

    @RequestMapping("/getAlltWimMsgByCondiTion")
    public List<tWimMsgVo> getAlltWimMsgByCondiTion(HttpSession session,String  stationPort,String startTime,String endTime,Double startWeight,Double endWeight,String chexing) {
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
        Double mid = 0.0;
        if(null!=startWeight&&null!=endWeight&&startWeight>endWeight){
            mid=startWeight;
            startWeight = endWeight;
            endWeight = mid;
        }
        List<tWimMsgVo> list= service.getAlltWimMsgByCondition(zhandianduankouhao,startTime,endTime,startWeight,endWeight,chexing);
        return list;
    }

    /**
     * 每日车流量
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

    /**
     * 每日超载量
     * @param stationPort
     * @return
     */
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
                int num=service.getMeiRiCheLiuLiangByStationPort(sport);
                /*if(i>0){*/
                    chaoZaiVo.setValue(i);
                    chaoZaiVo.setName(stationSites.get(0).getStationName()+"+"+stationSites.get(0).getRouteName());
                    chaoZaiVo.setNum(num);
                    list.add(chaoZaiVo);
                /*}*/
            }
        }
        return list;
    }

    /**
     * 每日关键超重测量
     * @param stationPort
     * @return
     */
    @RequestMapping("/getMeiRiGuanJianCheLiangByStationPort")
    public List<Integer> getMeiRiGuanJianCheLiangByStationPort(String stationPort) {
        List<Integer> list=new ArrayList<Integer>();
        if(stationPort!=null){
            String[] split = stationPort.split(",");
            for (String sport:split){
                int i=service.getMeiRiGuanJianCheLiangByStationPort(sport);
                list.add(i);
            }
        }
        return list;
    }

    @RequestMapping("/getMeiRiCheLiuLiangShujuByStationPort")
    public List<tWimMsgVo> getMeiRiCheLiuLiangShujuByStationPort(String stationPort,HttpSession session) {
        String zhandianduankouhao="";
        tUserInfo user = (tUserInfo) session.getAttribute("user");
        if(stationPort!=null&&stationPort!=""){
            zhandianduankouhao=stationPort;
        }else{
            if(user!=null){
                zhandianduankouhao = user.getStationPort();
                updlastMonitoringSiteById(user.getStationPort(),user.getUsrId());
            }
        }
        return service.getMeiRiCheLiuLiangShujuByStationPort(stationPort);
    }

    @RequestMapping("/getMeiRiChaoZaiShujuByStationPort")
    public List<tWimMsgVo> getMeiRiChaoZaiShujuByStationPort(String stationPort,HttpSession session) {
        String zhandianduankouhao="";
        tUserInfo user = (tUserInfo) session.getAttribute("user");
        if(stationPort!=null&&stationPort!=""){
            zhandianduankouhao=stationPort;
        }else{
            if(user!=null){
                zhandianduankouhao = user.getStationPort();
                updlastMonitoringSiteById(user.getStationPort(),user.getUsrId());
            }
        }
        return service.getMeiRiChaoZaiShujuByStationPort(stationPort);
    }

    @RequestMapping("/getMeiRiGuanJianChaoZHongShujuByStationPort")
    public List<tWimMsgVo> getMeiRiGuanJianChaoZHongShujuByStationPort(String stationPort,HttpSession session) {
        String zhandianduankouhao="";
        tUserInfo user = (tUserInfo) session.getAttribute("user");
        if(stationPort!=null&&stationPort!=""){
            zhandianduankouhao=stationPort;
        }else{
            if(user!=null){
                zhandianduankouhao = user.getStationPort();
                updlastMonitoringSiteById(user.getStationPort(),user.getUsrId());
            }
        }
        return service.getMeiRiGuanJianChaoZHongShujuByStationPort(stationPort);
    }

}
