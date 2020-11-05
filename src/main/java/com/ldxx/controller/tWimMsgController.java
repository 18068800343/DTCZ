package com.ldxx.controller;

import com.alibaba.fastjson.JSON;
import com.ldxx.Thread.PageCountCallable;
import com.ldxx.Thread.PageCountYiChangCallable;
import com.ldxx.bean.*;
import com.ldxx.config.DS;
import com.ldxx.dao.StationSiteDao;
import com.ldxx.dao.tAvgDayDao;
import com.ldxx.dao.tUserInfoDao;
import com.ldxx.dao.tWimMsgDao;
import com.ldxx.service.Impl.RedisServiceImpl;
import com.ldxx.service.tWimMsgService;
import com.ldxx.util.Base64Util;
import com.ldxx.util.FormatUtil;
import com.ldxx.util.GetThisTimeUtils;
import com.ldxx.util.MsgFormatUtils;
import com.ldxx.vo.ChaoZaiVo;
import com.ldxx.vo.tWimMsgVo;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.*;

@RestController
@RequestMapping("tWimMsg")
public class tWimMsgController {
    @Resource
    private JedisPool jedisPool;

    @Resource
    RedisServiceImpl redisServiceImpl;
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

    @RequestMapping("/settWimMsgCondiTion")
    public int settWimMsgCondiTion(HttpSession session,  TongJiTableQuery tongJiTableQuery) {
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

        //格式化车长
        doubleMid = FormatUtil.changeBigAndSmall(tongJiTableQuery.getCheChangStart(),tongJiTableQuery.getCheChangEnd());
        Double cheChangStart = doubleMid.get("start");
        Double cheChangEnd = doubleMid.get("end");

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
        tongJiTableQuery.setCheChangStart(cheChangStart);
        tongJiTableQuery.setCheChangEnd(cheChangEnd);
        tongJiTableQuery.setRoadTmpStart(roadTmpStart);
        tongJiTableQuery.setRoadTmpEnd(roadTmpEnd);
        tongJiTableQuery.setChaozhongStart(chaozhongStart);
        tongJiTableQuery.setChaozhongEnd(chaozhongEnd);

        tongJiTableQuery.setStationPort(zhandianduankouhao);
        session.setAttribute("tongJiTableQuery",tongJiTableQuery);
        return 1;
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

        //格式化车长
        doubleMid = FormatUtil.changeBigAndSmall(tongJiTableQuery.getCheChangStart(),tongJiTableQuery.getCheChangEnd());
        Double cheChangStart = doubleMid.get("start");
        Double cheChangEnd = doubleMid.get("end");

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
        tongJiTableQuery.setCheChangStart(cheChangStart);
        tongJiTableQuery.setCheChangEnd(cheChangEnd);
        tongJiTableQuery.setRoadTmpStart(roadTmpStart);
        tongJiTableQuery.setRoadTmpEnd(roadTmpEnd);
        tongJiTableQuery.setChaozhongStart(chaozhongStart);
        tongJiTableQuery.setChaozhongEnd(chaozhongEnd);

        tongJiTableQuery.setStationPort(zhandianduankouhao);

        List<tWimMsgVo> list= service.getAlltWimMsgByConditionByPage(tongJiTableQuery);
        PageData<tWimMsgVo> pd = new PageData<>();
        pd.setData(list);
        ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(2);
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
        session.setAttribute("tongJiTableQuery",tongJiTableQuery);
        /*redisServiceImpl.set("tongJiTableQuery",tongJiTableQuery);*/
        //TongJiTableQuery tongJiTableQuery1 = (TongJiTableQuery) redisServiceImpl.get("tongJiTableQuery");
        threadPoolExecutor.shutdown();
        return pd;
    }

    @RequestMapping("/getAlltWimMsgYiChangByCondiTion")
    public PageData<tWimMsgVo> getAlltWimMsgYiChangByCondiTion(HttpSession session,  TongJiTableQuery tongJiTableQuery) {



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

        //格式化车长
        doubleMid = FormatUtil.changeBigAndSmall(tongJiTableQuery.getCheChangStart(),tongJiTableQuery.getCheChangEnd());
        Double cheChangStart = doubleMid.get("start");
        Double cheChangEnd = doubleMid.get("end");

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
        tongJiTableQuery.setCheChangStart(cheChangStart);
        tongJiTableQuery.setCheChangEnd(cheChangEnd);
        tongJiTableQuery.setRoadTmpStart(roadTmpStart);
        tongJiTableQuery.setRoadTmpEnd(roadTmpEnd);
        tongJiTableQuery.setChaozhongStart(chaozhongStart);
        tongJiTableQuery.setChaozhongEnd(chaozhongEnd);

        tongJiTableQuery.setStationPort(zhandianduankouhao);

        List<tWimMsgVo> list= service.getAlltWimMsgYiChangByConditionByPage(tongJiTableQuery);
        PageData<tWimMsgVo> pd = new PageData<>();
        pd.setData(list);
        ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(2);
        Callable callable = new PageCountYiChangCallable(tongJiTableQuery);

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
        session.setAttribute("tongJiTableQueryYiChang",tongJiTableQuery);
        /*redisServiceImpl.set("tongJiTableQuery",tongJiTableQuery);*/
        //TongJiTableQuery tongJiTableQuery1 = (TongJiTableQuery) redisServiceImpl.get("tongJiTableQuery");
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
        StationSite stationSite = sitedao.getStationSiteByStationNameAndRouteName(stationName, "");
        String port = stationSite.getStationPort();
        return service.getMeiRiChaoZaiShujuByStationPort(port);
    }

    @RequestMapping("/getCheLiuLiangEchartsList")
    public CheLiuLiangEchartsList getCheLiuLiangEchartsList(String stationPorts, Integer link, Integer limit, HttpSession session) {
        return tWimMsgDao.getCheLiuLiangEchartsList(stationPorts, link, limit);
    }

    @RequestMapping("/initLiuZhouChaoZaiShuLiang")
    public CheLiuLiangEchartsList initLiuZhouChaoZaiShuLiang(String stationPorts, Integer link, Integer limit, HttpSession session) {
        return tWimMsgDao.initLiuZhouChaoZaiShuLiang(stationPorts, link, limit);
    }

    @RequestMapping("/getGuanJianChaoZhongCheLiangEchartsList")
    public CheLiuLiangEchartsList getGuanJianChaoZhongCheLiangEchartsList(String stationPorts, Integer link, Integer limit, HttpSession session) {
        return tWimMsgDao.getGuanJianChaoZhongCheLiangEchartsList(2, 3, stationPorts, link, limit);
    }


    @RequestMapping("/getChaoZaiEchartsList")
    public CheLiuLiangEchartsList getChaoZaiEchartsList(String stationPorts,Integer link,Integer limit, HttpSession session) {
        return tWimMsgDao.getChaoZaiEchartsList(stationPorts, link, limit);
    }

    @RequestMapping("/getFirChaoZaiLv")
    public List<CheLiuLiangEchartsList> getFirChaoZaiLv(String stationPorts,Integer link,Integer limit) {
        List<CheLiuLiangEchartsList> list = tWimMsgDao.getFirChaoZaiLv(stationPorts, link, limit);
        return list;
    }

    @RequestMapping("/getHomeDataObject")
    public HomeData getHomeDataObject(String stationPorts, HttpSession session) {
        HomeData homeData = tWimMsgDao.getHomeData(stationPorts);
        String[] nums = homeData.getLinks().split(",");
        int m = 0;
        for (String num : nums) {
            if ("1".equals(num)) {
                m++;
            }
        }
        homeData.setStationNums(m);
        return homeData;
    }

    @RequestMapping("/getHomeDataObject1")
    public HomeData getHomeDataObject1(String stationPorts, HttpSession session) {
        HomeData homeData2 = service.getHomeData2();
        HomeData homeData = tWimMsgDao.getHomeData(stationPorts);

        String[] nums = homeData.getLinks().split(",");
        int m = 0;
        Double db2MaxWeight = homeData2.getMaxWeight();

        Double lessWeight = tWimMsgDao.getMaxWeightByWeight(stationPorts, db2MaxWeight);
        homeData.setMaxWeight(lessWeight);
        for (String num : nums) {
            if ("1".equals(num)) {
                m++;
            }
        }
        homeData.setStationNums(m);
        return homeData;
    }

    @RequestMapping("/getMeiRiGuanJianChaoZHongShujuByStationPort")
    public List<tWimMsgVo> getMeiRiGuanJianChaoZHongShujuByStationPort(String stationPort, Integer lv, HttpSession session) {
        String zhandianduankouhao = "";
        tUserInfo user = (tUserInfo) session.getAttribute("user");
        if (stationPort != null && stationPort != "") {
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
    @RequestMapping("/gettWimMsgYiChangById")
    public tWimMsgVo gettWimMsgYiChangById(String idLocal) {

        tWimMsgVo tWimMsgVo = service.gettWimMsgYiChangById(idLocal);
        if(null==tWimMsgVo){
            idLocal = idLocal.substring(0,idLocal.length()-6);
            return service.gettWimMsgYiChangById(idLocal);
        }else {
            return tWimMsgVo;
        }
    }



    /**
     * 获取检测站点的车流量数，超载车辆数，关键超重数，监测站点数
     * @param stationPorts
     * @return
     */
    @RequestMapping("/getJianCeTotal")
    public HomeData getJianCeTotal(String stationPorts) {
        HomeData homeData = tWimMsgDao.getJianCeTotal(stationPorts);
        String[] nums = homeData.getLinks().split(",");
        int m=0;
        for(String num:nums){
            if("1".equals(num)){
                m++;
            }
        }
        homeData.setStationNums(m);
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

    @RequestMapping("/test")
    public String setRedis(String value) {
        redisServiceImpl.set("1","22222");
        return "22222";
    }

    /**
     * 首页第二个界面根据轴数查询统计数量
     * @param axlesCount 轴数
     * @param stationPorts 端口
     * @return
     */
    @RequestMapping("/getSecHomeTotal")
    public Map<String,String> getSecHomeTotal(String axlesCount,String stationPorts) throws UnsupportedEncodingException {
        Map<String,String> map = tWimMsgDao.getSecHomeTotal(axlesCount,stationPorts);
        /*SecHomeData secHomeData=new SecHomeData();
        Set<String> set1 = map.keySet();
        Iterator<String> it = set1.iterator();
        while(it.hasNext()) {
            String key = it.next();
            String value= map.get(key);
            if("liuliangCount".equals(key)){
                secHomeData.setLiuliangCount(value);
            }else if("ChaoZaiCount".equals(key)){
                secHomeData.setChaoZaiCount(value);
            }else{
                secHomeData.setGuanJianCount(value);
            }
        }*/
        return map;
    }

    /**
     * 首页第二个页面通过轴数和站点端口获得站点车流量
     * @param axlesCount
     * @param stationPorts
     * @return
     */
    @RequestMapping("/getSecLiuLiangEcharsList")
    public SecHomeData getSecLiuLiangEcharsList(String axlesCount,String stationPorts,Integer limit){
        SecHomeData list = tWimMsgDao.getSecLiuLiangEcharsList(axlesCount, stationPorts,limit);
        return list;
    }

    /**
     * 首页第二个页面通过轴数和站点端口获得站点车流量 按照省界统计
     *
     * @param axlesCount
     * @param stationPorts
     * @return
     */
    @RequestMapping("/getSecChaoZaiEcharsList")
    public SecHomeData getSecChaoZaiEcharsList(String axlesCount, String stationPorts, Integer limit) {
        SecHomeData list = tWimMsgDao.getSecChaoZaiEcharsList(axlesCount, stationPorts, limit);
        return list;
    }

    /**
     * 首页第二个页面通过轴数和站点端口获得站点车流量 按照站点统计
     *
     * @param axlesCount
     * @param stationPorts
     * @return
     */
    @RequestMapping("/getSecChaoZaiEcharsListByZhanDian")
    public SecHomeData getSecChaoZaiEcharsListByZhanDian(String axlesCount, String stationPorts, Integer limit) {
        SecHomeData list = tWimMsgDao.getSecChaoZaiEcharsListByZhanDian(axlesCount, stationPorts, limit);
        return list;
    }

    /**
     * 首页第二个页面通过轴数和站点端口获得站点车流量
     *
     * @param axlesCount
     * @param stationPorts
     * @return
     */
    @RequestMapping("/initLiuZhouShengJieChaoZai")
    public SecHomeData initLiuZhouShengJieChaoZai(String axlesCount, String stationPorts, Integer limit) {
        SecHomeData list = tWimMsgDao.getSecChaoZaiEcharsList(axlesCount, stationPorts, limit);
        return list;
    }

    /**
     * 首页第二个页面通过轴数和站点端口获得当月站点车流量
     *
     * @param axlesCount
     * @param stationPorts
     * @return
     */
    @RequestMapping("/getSecChaoZaiEcharsListMonth")
    public SecHomeData getSecChaoZaiEcharsListMonth(String axlesCount, String stationPorts, Integer limit) {
        String yearMonth = GetThisTimeUtils.getMonth();
        SecHomeData list = tWimMsgDao.getSecChaoZaiEcharsListMonth(yearMonth, axlesCount, stationPorts, limit);
        return list;
    }

    /**
     * 首页第二个页面通过轴数和站点端口获得站点车流量
     *
     * @param limit
     * @param stationPorts
     * @return
     */
    @RequestMapping("/getGongSiTongJiYuJingData")
    public Map getGongSiTongJiYuJingData(String stationPorts, Integer limit, Integer axlesCount) {
        Map map = tWimMsgDao.getGongSiTongJiYuJingData(stationPorts, limit, axlesCount);
        return map;
    }

    /**
     * 首页第三个页面单轴大于14吨
     *
     * @param limit
     * @param stationPorts
     * @return
     */
    @RequestMapping("/getDanZhouChaoZai")
    public List<DanZhouChaoZai> getDanZhouChaoZai(String stationPorts, Integer limit) {
        List<DanZhouChaoZai> map = tWimMsgDao.getDanZhouChaoZai(stationPorts, limit);
        return map;
    }

    /**
     * 首页第三个页面通过轴数和站点端口获得站点车流量
     *
     * @param limit
     * @param stationPorts
     * @return
     */
    @RequestMapping("/getGongSiTongJiYuJingData_LiuZhou")
    public Map getGongSiTongJiYuJingData_LiuZhou(String stationPorts, Integer limit) {
        Map map = tWimMsgDao.getGongSiTongJiYuJingData_LiuZhou(stationPorts, limit);
        return map;
    }

    /**
     * 首页第二个页面通过轴数和站点端口获得站点车流量
     *
     * @param limit
     * @param stationPorts
     * @return
     */
    @RequestMapping("/initShengJieTongJiYuJingData")
    public Map initShengJieTongJiYuJingData(String stationPorts, Integer limit) {
        Map map = tWimMsgDao.initShengJieTongJiYuJingData(stationPorts, limit);
        return map;
    }

    /**
     * 通过登录人的站点端口号查询地图经纬度信息并按照车流量大小倒叙排
     * @param 
     * @return
     */
    @RequestMapping("/getDiTujwdByPort")
    public CheLiuLiangEchartsList getDiTujwdByPort(HttpSession session) {
        tUserInfo user = (tUserInfo) session.getAttribute("user");
        String zhandianduankouhao = user.getStationPort();
        return tWimMsgDao.getDiTujwdByPort(zhandianduankouhao);
    }

    /**
     * 首页趋势图，查询最近七天总流量和超载量
     */

    @RequestMapping("/getQushitu")
    public List<tjfxTotalEchars> getQushitu(String stationPorts) {
        String date = GetThisTimeUtils.getDate();
        tjfxTotalEchars toadynum = tWimMsgDao.getTodayzongliuliangAndzongchaozai(stationPorts);
        toadynum.setAvgTime(date);
        List<tjfxTotalEchars> tjfxTotalEchars = tWimMsgDao.getsexTotalEcharsByStationPorts(stationPorts);
        tjfxTotalEchars.add(0, toadynum);

        Collections.reverse(tjfxTotalEchars);
        return tjfxTotalEchars;
    }

//    @DS("db2")
//    @RequestMapping("/getQushitu")
//    public List<tjfxTotalEchars> getQushitu(String stationPorts) {
//        String date = GetThisTimeUtils.getDate();
//        tjfxTotalEchars toadynum = tWimMsgDao.getTodayzongliuliangAndzongchaozai(stationPorts);
//        toadynum.setAvgTime(date);
//        List<tjfxTotalEchars> tjfxTotalEchars = tWimMsgDao.getsexTotalEcharsByStationPorts(stationPorts);
//        tjfxTotalEchars.add(0, toadynum);
//
//        Collections.reverse(tjfxTotalEchars);
//        return tjfxTotalEchars;
//    }



    //

    //=============================================================以下首页第五个界面，省界和控股的加一起===============================

    @RequestMapping("/getDiTujwdByPort2")
    public CheLiuLiangEchartsList getDiTujwdByPort2(HttpSession session) {
        tUserInfo user = (tUserInfo) session.getAttribute("user");
        String zhandianduankouhao = user.getStationPort();
        CheLiuLiangEchartsList obj1= service.getDiTujwdByPort(zhandianduankouhao);
        String[] port = obj1.getStationPorts().split(",");
        List<String> list=new ArrayList<>();
        String[] port1 = obj1.getStationNames().split(",");
        for(int i=0;i<port1.length;i++){
            if(port1[i].equals("泰州大桥")||port1[i].equals("苏通大桥")||port1[i].equals("江阴大桥")||port1[i].equals("润扬大桥")){
                list.add("3");
            }else{
                if (!Arrays.asList(port1).contains("泰州大桥")||!Arrays.asList(port1).contains("苏通大桥")||!Arrays.asList(port1).contains("江阴大桥")||!Arrays.asList(port1).contains("润扬大桥")){
                    list.add("1");
                }else{
                    list.add("2");
                }
            }
        }

        CheLiuLiangEchartsList obj2= service.getDiTujwdByPort2();
        String[] port2 = obj2.getStationNames().split(",");
        for(int i=0;i<port2.length;i++){
            if(port2[i].equals("泰州大桥")||port2[i].equals("苏通大桥")||port2[i].equals("江阴大桥")||port2[i].equals("润扬大桥")){
                list.add("3");
            }else{
                if (!Arrays.asList(port2).contains("泰州大桥")||!Arrays.asList(port2).contains("苏通大桥")||!Arrays.asList(port2).contains("江阴大桥")||!Arrays.asList(port2).contains("润扬大桥")){
                    list.add("1");
                }else{
                    list.add("2");
                }
            }
        }
        obj1.setNums(obj1.getNums()+","+obj2.getNums());
        obj1.setStationPorts(obj1.getStationPorts()+","+obj2.getStationPorts());
        obj1.setStationNames(obj1.getStationNames()+","+obj2.getStationNames());
        obj1.setLnglat(obj1.getLnglat()+","+obj2.getLnglat());
        obj1.setColorState(StringUtils.join(list.toArray(),","));
        return obj1;
    }

    @RequestMapping("/getHomeDataObject2")
    public HomeData getHomeDataObject2(String stationPorts, HttpSession session) {
        JSONObject jsonObject=new JSONObject();
        HomeData homeData = service.getHomeData(stationPorts);
        String[] nums = homeData.getLinks().split(",");
        int m=0;
        for(String num:nums){
            if("1".equals(num)){
                m++;
            }
        }
        homeData.setStationNums(m);
        HomeData homeData2 = service.getHomeData2();
        String[] nums2 = homeData2.getLinks().split(",");
        int m2 = 0;
        for (String num2 : nums2) {
            if ("1".equals(num2)) {
                m2++;
            }
        }
        homeData2.setStationNums(m2);
        homeData.setIdLocal(homeData.getMaxWeight() > homeData2.getMaxWeight() ? homeData.getIdLocal() : homeData2.getIdLocal());
        homeData.setLinks(homeData.getLinks() +","+ homeData2.getLinks());

        //DTCZ
        homeData.setMaxWeight(homeData.getMaxWeight() > homeData2.getMaxWeight() ? homeData2.getMaxWeight() : homeData.getMaxWeight());
        //DTCZ2
//        homeData.setMaxWeight(homeData.getMaxWeight()>homeData2.getMaxWeight()?homeData2.getMaxWeight():homeData.getMaxWeight());
        homeData.setStationNames(homeData.getStationNames() +","+ homeData2.getStationNames());
        homeData.setStationNums(homeData.getStationNums() + homeData2.getStationNums());
        homeData.setTotalChaoZai(homeData.getTotalChaoZai() + homeData2.getTotalChaoZai());
        homeData.setTotalCheLiu(homeData.getTotalCheLiu() + homeData2.getTotalCheLiu());

        return homeData;
    }

    @RequestMapping("/getCheLiuLiangEchartsList2")
    public CheLiuLiangEchartsList getCheLiuLiangEchartsList2(String stationPorts, Integer link, Integer limit, HttpSession session) {
        CheLiuLiangEchartsList list = service.getCheLiuLiangEchartsList(stationPorts, link, limit);
        CheLiuLiangEchartsList list2 = service.getCheLiuLiangEchartsList2();

        CheLiuLiangEchartsList cheLiuLiangEchartsListEnd = MsgFormatUtils.getCheLiuLiangEchartsByTow(list, list2);
        return cheLiuLiangEchartsListEnd;
    }

    @RequestMapping("/getFirChaoZaiLv2")
    public List<CheLiuLiangEchartsList> getFirChaoZaiLv2(String stationPorts,Integer link,Integer limit) {
        List<CheLiuLiangEchartsList> list = service.getFirChaoZaiLv(stationPorts, link, limit);
        List<CheLiuLiangEchartsList> list2 = service.getFirChaoZaiLv2( link, limit);
        list.addAll(list2);
        Collections.sort(list, new Comparator<CheLiuLiangEchartsList>() {
            @Override
            public int compare(CheLiuLiangEchartsList o1, CheLiuLiangEchartsList o2) {
                //降序
                return o2.getNumsBili().compareTo(o1.getNumsBili());
            }
        });
        return list;
    }

    @RequestMapping("/getQushitu2")
    public List<tjfxTotalEchars> getQushitu2(String stationPorts) {
        String date = GetThisTimeUtils.getDate();
        List<tjfxTotalEchars> tjfxTotalEchars = service.getQushitu(stationPorts);
        List<tjfxTotalEchars> tjfxTotalEchars2 = service.getQushitu2();
        tjfxTotalEchars = MsgFormatUtils.gettjfxTotalEchartsByTowList(tjfxTotalEchars, tjfxTotalEchars2);
        return tjfxTotalEchars;
    }
}
