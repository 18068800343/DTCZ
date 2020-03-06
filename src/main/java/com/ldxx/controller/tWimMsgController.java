package com.ldxx.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ldxx.Thread.PageCountCallable;
import com.ldxx.bean.*;
import com.ldxx.dao.StationSiteDao;
import com.ldxx.dao.tUserInfoDao;
import com.ldxx.dao.tWimMsgDao;
import com.ldxx.service.tWimMsgService;
import com.ldxx.util.FormatUtil;
import com.ldxx.vo.ChaoZaiVo;
import com.ldxx.vo.tWimMsgVo;
import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@RestController
@RequestMapping("tWimMsg")
public class tWimMsgController {

    @Autowired
    private tWimMsgService service;
    @Resource
    private tWimMsgDao tWimMsgDao;
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
                this.updlastMonitoringSiteById(stationPort,user.getUsrId(),session);
            }
        }else{
            if(user!=null){
                zhandianduankouhao = user.getStationPort();
                updlastMonitoringSiteById(user.getStationPort(),user.getUsrId(),session);
            }
        }
        List<tWimMsgVo> list= service.getAlltWimMsg(zhandianduankouhao);
        return list;
    }

    @RequestMapping("/getWimMsgHomePage")
    public List<tWimMsgVo> getWimMsgHomePage(HttpSession session,String  roadName) {
        boolean b = roadName != null && !"".equals(roadName) ;
        String stationName = roadName;
        StationSite stationSite = sitedao.getStationSiteByStationNameAndRouteName(stationName,"");
        String port = stationSite.getStationPort();
        List<tWimMsgVo> list= service.getMeiRiCheLiuLiangShujuByStationPort(port);
        return list;
    }

    private int  updlastMonitoringSiteById(String stationPort,String usrId,HttpSession session){
        int i=tUserInfoDao.updlastMonitoringSiteById(stationPort,usrId);
        if(i>0){
            tUserInfo user = (tUserInfo) session.getAttribute("user");
            user.setLastMonitoringSite(stationPort);
            session.removeAttribute("user");
            session.setAttribute("user",user);
        }
        return i;
    }

    @RequestMapping("/getAlltWimMsgByCondiTion")
    public PageData<tWimMsgVo> getAlltWimMsgByCondiTion(HttpSession session,  TongJiTableQuery tongJiTableQuery) {

        String zhandianduankouhao="";
        tUserInfo user = (tUserInfo) session.getAttribute("user");
        String stationPort = tongJiTableQuery.getStationPort();
        String startTime = tongJiTableQuery.getStartTime();
        String endTime = tongJiTableQuery.getEndTime();
        String chexing = tongJiTableQuery.getChexing();
        Double startWeight = tongJiTableQuery.getStartWeight();
        Double endWeight = tongJiTableQuery.getEndWeight();
           if(stationPort!=null&&stationPort!=""){
            zhandianduankouhao=stationPort;
            if(user!=null){
                updlastMonitoringSiteById(stationPort,user.getUsrId(),session);
            }
        }else{
             if(user!=null){
                zhandianduankouhao = user.getStationPort();
                updlastMonitoringSiteById(user.getStationPort(),user.getUsrId(),session);
            }
        }
           //格式化重量范围s
        Double mid = 0.0;
        if(null!=startWeight&&null!=endWeight&&startWeight>endWeight){
            mid=startWeight;
            startWeight = endWeight;
            endWeight = mid;
        }
        //格式化车速
        Map<String,Double> doubleMid = FormatUtil.changeBigAndSmall(tongJiTableQuery.getChesuStart(),tongJiTableQuery.getChesuEnd());
        Double chesuStart = doubleMid.get("start");
        Double chesuEnd = doubleMid.get("end");

        //格式化温度
        doubleMid = FormatUtil.changeBigAndSmall(tongJiTableQuery.getRoadTmpStart(),tongJiTableQuery.getRoadTmpEnd());
        Double roadTmpStart = doubleMid.get("start");
        Double roadTmpEnd = doubleMid.get("end");

        //格式化超重比率
        doubleMid = FormatUtil.changeBigAndSmall(tongJiTableQuery.getChaozhongStart(),tongJiTableQuery.getChaozhongEnd());
        Double chaozhongStart = doubleMid.get("start");
        Double chaozhongEnd = doubleMid.get("end");

        tongJiTableQuery.setStartWeight(startWeight);
        tongJiTableQuery.setEndWeight(endWeight);
        tongJiTableQuery.setChesuStart(chesuStart);
        tongJiTableQuery.setChesuEnd(chesuEnd);
        tongJiTableQuery.setRoadTmpStart(roadTmpStart);
        tongJiTableQuery.setRoadTmpEnd(roadTmpEnd);
        tongJiTableQuery.setChaozhongStart(chaozhongStart);
        tongJiTableQuery.setChaozhongEnd(chaozhongEnd);

        tongJiTableQuery.setStationPort(zhandianduankouhao);

        List<tWimMsgVo> list= service.getAlltWimMsgByConditionByPage(tongJiTableQuery);
        PageData<tWimMsgVo> pd = new PageData<>();
        pd.setData(list);
        ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(1);
        Callable callable = new PageCountCallable(tongJiTableQuery);
        Future<Integer> res = null;
        res = threadPoolExecutor.submit(callable);
        try {
            Integer count  = res.get();
            pd.setiTotalRecords(count);
            pd.setiTotalDisplayRecords(count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        threadPoolExecutor.shutdown();
        return pd;
    }

    /**
     * 每日车流量
     * @param stationPort
     * @return
     */
    @RequestMapping("/getMeiRiCheLiuLiangByStationPort")
    public Map getMeiRiCheLiuLiangByStationPort(String stationPort) {
        Map map = new HashMap();
        List<Integer> list=new ArrayList<Integer>();
        if(stationPort!=null){
            String[] split = stationPort.split(",");
            for (String sport:split){
                int i=service.getMeiRiCheLiuLiangByStationPort(sport);
                list.add(i);
            }
        }
        Integer max = tWimMsgDao.getMeiRiCheLiuLiangMaxByStationPort();
        if(10000<max&&max<=20000){
            max = 20000;
        }else if (max<=10000){
            max = 10000;
        }else{
            max = 30000;
        }
        map.put("list",list);
        map.put("max",max);
        return map;
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
                    chaoZaiVo.setName(stationSites.get(0).getStationName());
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
    public Map getMeiRiGuanJianCheLiangByStationPort(String stationPort) {
        List<Integer> list=new ArrayList<Integer>();
        if(stationPort!=null){
            String[] split = stationPort.split(",");
            for (String sport:split){
                int i=service.getMeiRiGuanJianCheLiangByStationPort(sport);
                list.add(i);
            }
        }
        Integer max = tWimMsgDao.getMeiRiGuanJianCheLiangMax();
        Map map = new HashMap();
        if(10000<max&&max<=20000){
            max = 20000;
        }else if (max<=10000&&max>5000){
            max = 10000;
        }else if (max<=5000){
            max = 5000;
        }else{
            max = 30000;
        }
        map.put("list",list);
        map.put("max",max);
        return map;
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
                //updlastMonitoringSiteById(user.getStationPort(),user.getUsrId(),session);
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
                //updlastMonitoringSiteById(user.getStationPort(),user.getUsrId(),session);
            }
        }
        return service.getMeiRiChaoZaiShujuByStationPort(stationPort);
    }

    @RequestMapping("/getChaoZaiHomePage")
    public List<tWimMsgVo> getChaoZaiHomePage(String roadName,HttpSession session) {
        boolean b = roadName != null && !"".equals(roadName);
        String stationName = roadName;
        StationSite stationSite = sitedao.getStationSiteByStationNameAndRouteName(stationName,"");
        String port = stationSite.getStationPort();
        return service.getMeiRiChaoZaiShujuByStationPort(port);
    }

    @RequestMapping("/getCheLiuLiangEchartsList")
    public CheLiuLiangEchartsList getCheLiuLiangEchartsList(String stationPorts,Integer link,Integer limit,HttpSession session) {
        return tWimMsgDao.getCheLiuLiangEchartsList(stationPorts,link,limit);
    }

    @RequestMapping("/getGuanJianChaoZhongCheLiangEchartsList")
    public CheLiuLiangEchartsList getGuanJianChaoZhongCheLiangEchartsList(String stationPorts,Integer link,Integer limit, HttpSession session) {
        return tWimMsgDao.getGuanJianChaoZhongCheLiangEchartsList(2,3,stationPorts, link, limit);
    }



    @RequestMapping("/getChaoZaiEchartsList")
    public CheLiuLiangEchartsList getChaoZaiEchartsList(String stationPorts,Integer link,Integer limit, HttpSession session) {
        return tWimMsgDao.getChaoZaiEchartsList(stationPorts, link, limit);
    }

    @RequestMapping("/getHomeDataObject")
    public HomeData getHomeDataObject(String stationPorts, HttpSession session) {
        HomeData homeData = tWimMsgDao.getHomeData(stationPorts);
        homeData.setStationNums(stationPorts.split(",").length);
        return homeData;
    }

    @RequestMapping("/getMeiRiGuanJianChaoZHongShujuByStationPort")
    public List<tWimMsgVo> getMeiRiGuanJianChaoZHongShujuByStationPort(String stationPort,Integer lv,HttpSession session) {
        String zhandianduankouhao="";
        tUserInfo user = (tUserInfo) session.getAttribute("user");
        if(stationPort!=null&&stationPort!=""){
            zhandianduankouhao=stationPort;
        }else{
            if(user!=null){
                zhandianduankouhao = user.getStationPort();
                //updlastMonitoringSiteById(user.getStationPort(),user.getUsrId(),session);
            }
        }
        return service.getMeiRiGuanJianChaoZHongShujuByStationPort(stationPort,lv);
    }

    @RequestMapping("/getChaoZHongHomePage")
    public List<tWimMsgVo> getChaoZHongHomePage(String roadName,Integer lv,HttpSession session) {
        boolean b = roadName != null && !"".equals(roadName);
        String stationName = roadName;
        String routeName = "";
        StationSite stationSite = sitedao.getStationSiteByStationNameAndRouteName(stationName,routeName);
        String port = stationSite.getStationPort();
        return service.getMeiRiGuanJianChaoZHongShujuByStationPort(port,lv);
    }

    @RequestMapping("/gettWimMsgById")
    public tWimMsgVo gettWimMsgById(String idLocal) {
        return service.gettWimMsgById(idLocal);
    }



    /**
     * 获取检测站点的车流量数，超载车辆数，关键超重数，监测站点数
     * @param stationPorts
     * @return
     */
    @RequestMapping("/getJianCeTotal")
    public HomeData getJianCeTotal(String stationPorts) {
        HomeData homeData = tWimMsgDao.getJianCeTotal(stationPorts);
        homeData.setStationNums(stationPorts.split(",").length);
        return homeData;
    }

    /**
     * 监测站点的车流量统计图
     * @param stationPorts
     * @return
     */
    @RequestMapping("/getJianCeCheLiuLiangEchartsList")
    public CheLiuLiangEchartsList getJianCeCheLiuLiangEchartsList(String stationPorts) {
        return tWimMsgDao.getJianCeCheLiuLiangEchartsList(stationPorts);
    }

    /**
     * 监测站点的超载车辆统计图
     * @param stationPorts
     * @return
     */
    @RequestMapping("/getJianCechaozaiEchartsList")
    public CheLiuLiangEchartsList getJianCechaozaiEchartsList(String stationPorts) {
        return tWimMsgDao.getJianCechaozaiEchartsList(stationPorts);
    }

    /**
     * 实时监测页面关键超重车辆统计图
     * @param stationPorts
     * @return
     */
    @RequestMapping("/getGuanJianChaoZhongCheLiangEchartsList2")
    public CheLiuLiangEchartsList getGuanJianChaoZhongCheLiangEchartsList2(String stationPorts) {
        return tWimMsgDao.getGuanJianChaoZhongCheLiangEchartsList2(2,3,stationPorts);
    }
}
