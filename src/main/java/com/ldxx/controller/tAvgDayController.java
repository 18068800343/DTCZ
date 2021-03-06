package com.ldxx.controller;

import com.ldxx.bean.tAvgDay;
import com.ldxx.bean.tAvgMonth;
import com.ldxx.bean.tjfxTotalEchars;
import com.ldxx.config.Const;
import com.ldxx.dao.tAvgDayDao;
import com.ldxx.util.DateUtil;
import com.ldxx.vo.tAvgDayColumn;
import com.ldxx.service.tAvgDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequestMapping("tAvgDay")
@RestController
public class tAvgDayController {

    @Autowired
    private tAvgDayService service;

    @Resource
    private tAvgDayDao tAvgDayDao;

    @RequestMapping("/gettAvgDayByIpAndTime")
    public tAvgDay gettAvgDayByIpAndTime(String stationIP, String avgTime, int avgLaneNo) {
        tAvgDay tAvgDay = service.gettAvgDayByIpAndTime(stationIP, avgTime, avgLaneNo);
        return tAvgDay;
    }

    @RequestMapping("/gettAvgDayTotalByTime")
    public tAvgDay gettAvgDayTotalByTime(String avgTime) {
        tAvgDay tAvgDay = tAvgDayDao.gettAvgDayTotalByTime(avgTime);
        if (null == tAvgDay) {
            tAvgDay = new tAvgDay();
        }
        return tAvgDay;
    }


    @RequestMapping("/gettAvgDayColumnByCompanyNameAndTime_lgs")
    public List<tAvgDayColumn> gettAvgDayColumnByCompanyNameAndTime_lgs(String companyName, String avgTime, int avgLaneNo) {

        List<tAvgDayColumn> tAvgDayColumn = tAvgDayDao.gettAvgDayColumnByCompanyNameAndTime_lgs(companyName, avgTime, avgLaneNo);
        return tAvgDayColumn;
    }

    @RequestMapping("/gettAvgDayColumnByCompanyNameAndTime_lgs_week")
    public List<tAvgDayColumn> gettAvgDayColumnByCompanyNameAndTime_lgs_week(String companyName, String avgTime, int avgLaneNo) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<tAvgDayColumn> tAvgDayColumn = null;
        try {
            Date date = sdf.parse(avgTime);
            avgTime = sdf.format(DateUtil.geLastWeekMonday(date));
            tAvgDayColumn = tAvgDayDao.gettAvgDayColumnByCompanyNameAndTime_lgs_week(companyName, avgTime, avgLaneNo);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tAvgDayColumn;
    }

    @RequestMapping("/gettAvgDayColumnByCompanyNameAndTime_sj")
    public List<tAvgDayColumn> gettAvgDayColumnByCompanyNameAndTime_sj(String provinceName, String avgTime, int avgLaneNo) {
        List<tAvgDayColumn> tAvgDayColumn = tAvgDayDao.gettAvgDayColumnByCompanyNameAndTime_sj(provinceName, avgTime, avgLaneNo);
        return tAvgDayColumn;
    }

    @RequestMapping("/gettAvgDayColumnByCompanyNameAndTime_sj_week")
    public List<tAvgDayColumn> gettAvgDayColumnByCompanyNameAndTime_sj_week(String provinceName, String avgTime, int avgLaneNo) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<tAvgDayColumn> tAvgDayColumn = null;
        try {
            Date date = sdf.parse(avgTime);
            avgTime = sdf.format(DateUtil.geLastWeekMonday(date));
            tAvgDayColumn = tAvgDayDao.gettAvgDayColumnByCompanyNameAndTime_sj_week(provinceName, avgTime, avgLaneNo);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tAvgDayColumn;
    }

    @RequestMapping("/gettAvgDayColumnByIpAndTime")
    public List<tAvgDayColumn> gettAvgDayColumnByIpAndTime(String stationIP, String avgTime, int avgLaneNo) {
        List<tAvgDayColumn> tAvgDayColumn = service.gettAvgDayColumnByIpAndTime(stationIP, avgTime, avgLaneNo);
        return tAvgDayColumn;
    }

    @RequestMapping("/gettAvgDayColumnByIpAndTime_week")
    public List<tAvgDayColumn> gettAvgDayColumnByIpAndTime_week(String stationIP, String avgTime, int avgLaneNo) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<tAvgDayColumn> tAvgDayColumn = null;
        try {
            Date date = sdf.parse(avgTime);
            avgTime = sdf.format(DateUtil.geLastWeekMonday(date));
            tAvgDayColumn = service.gettAvgDayColumnByIpAndTime_week(stationIP, avgTime, avgLaneNo);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tAvgDayColumn;
    }

    @RequestMapping("/gettAvgDayColumnByIpAndTimeMonth")
    public List<tAvgDayColumn> gettAvgDayColumnByIpAndTimeMonth(String stationIP, String avgTime, int avgLaneNo) {
        List<tAvgDayColumn> tAvgDayColumn = service.gettAvgDayColumnByIpAndTimeMonth(stationIP, avgTime, avgLaneNo);
        return tAvgDayColumn;
    }

    @RequestMapping("/gettAvgDayColumnByIpAndTimeMonth_lgs")
    public List<tAvgDayColumn> gettAvgDayColumnByIpAndTimeMonth_lgs(String companyName, String avgTime, int avgLaneNo) {
        List<tAvgDayColumn> tAvgDayColumn = tAvgDayDao.gettAvgDayColumnByIpAndTimeMonth_lgs(companyName, avgTime, avgLaneNo);
        return tAvgDayColumn;
    }

    @RequestMapping("/gettAvgDayColumnByIpAndTimeMonth_sj")
    public List<tAvgDayColumn> gettAvgDayColumnByIpAndTimeMonth_sj(String provinceName, String avgTime, int avgLaneNo) {
        List<tAvgDayColumn> tAvgDayColumn = tAvgDayDao.gettAvgDayColumnByIpAndTimeMonth_sj(provinceName, avgTime, avgLaneNo);
        return tAvgDayColumn;
    }


    @RequestMapping("/getEchartsListByLaneNoAndColumn")
    public Map getEchartsListByLaneNoAndColumn(String stationIP, String column, String laneNo) {
        Map tAvgDayColumn = service.getEchartsListByLaneNoAndColumn(stationIP, column, laneNo);
        return tAvgDayColumn;
    }

    @RequestMapping("/getEchartsListByLaneNoAndColumn_week")
    public Map getEchartsListByLaneNoAndColumn_week(String stationIP, String column, String laneNo) {
        Map tAvgDayColumn = service.getEchartsListByLaneNoAndColumn_week(stationIP, column, laneNo);
        return tAvgDayColumn;
    }

    @RequestMapping("/getEchartsListByLaneNoAndColumn_lgs")
    public Map getEchartsListByLaneNoAndColumn_lgs(String companyName, String column, String laneNo) {
        Map tAvgDayColumn = service.getEchartsListByLaneNoAndColumn_lgs(companyName, column, laneNo);
        return tAvgDayColumn;
    }

    @RequestMapping("/getEchartsListByLaneNoAndColumn_lgs_week")
    public Map getEchartsListByLaneNoAndColumn_lgs_week(String companyName, String column, String laneNo) {
        Map tAvgDayColumn = service.getEchartsListByLaneNoAndColumn_lgs_week(companyName, column, laneNo);
        return tAvgDayColumn;
    }

    @RequestMapping("/getEchartsListByLaneNoAndColumn_sj")
    public Map getEchartsListByLaneNoAndColumn_sj(String provinceName, String column, String laneNo) {
        Map tAvgDayColumn = service.getEchartsListByLaneNoAndColumn_sj(provinceName, column, laneNo);
        return tAvgDayColumn;
    }

    @RequestMapping("/getEchartsListByLaneNoAndColumn_sj_week")
    public Map getEchartsListByLaneNoAndColumn_sj_week(String provinceName, String column, String laneNo) {
        Map tAvgDayColumn = service.getEchartsListByLaneNoAndColumn_sj_week(provinceName, column, laneNo);
        return tAvgDayColumn;
    }

    @RequestMapping("/getEchartsListByLaneNoAndColumnMonth")
    public Map getEchartsListByLaneNoAndColumnMonth(String stationIP, String column, String laneNo) {
        Map tAvgDayColumn = service.getEchartsListByLaneNoAndColumnMonth(stationIP, column, laneNo);
        return tAvgDayColumn;
    }

    @RequestMapping("/getEchartsListByLaneNoAndColumnMonth_lgs")
    public Map getEchartsListByLaneNoAndColumnMonth_lgs(String companyName, String column, String laneNo) {
        Map tAvgDayColumn = service.getEchartsListByLaneNoAndColumnMonth_lgs(companyName, column, laneNo);
        return tAvgDayColumn;
    }

    @RequestMapping("/getEchartsListByLaneNoAndColumnMonth_sj")
    public Map getEchartsListByLaneNoAndColumnMonth_sj(String provinceName, String column, String laneNo) {
        Map tAvgDayColumn = service.getEchartsListByLaneNoAndColumnMonth_sj(provinceName, column, laneNo);
        return tAvgDayColumn;
    }

    @RequestMapping("/gettAvgMonthByIpAndTime")
    public tAvgMonth gettAvgMonthByIpAndTime(String stationIP, String avgTime, int avgLaneNo) {
        tAvgMonth tAvgMonth = service.gettAvgMonthByIpAndTime(stationIP, avgTime, avgLaneNo);
        return tAvgMonth;
    }

    @RequestMapping("/gettjfxTotalEchars")
    public tjfxTotalEchars gettjfxTotalEchars(){
        tjfxTotalEchars ztqk=new tjfxTotalEchars();
        List<tjfxTotalEchars> tjfxTotalEchars = tAvgDayDao.gettjfxTotalEchars();
        StringBuffer avgTime=new StringBuffer();
        StringBuffer zongliuliangnum=new StringBuffer();
        StringBuffer zongchaozainum =new StringBuffer();
        StringBuffer yijiyujingnum =new StringBuffer();
        StringBuffer erjiyujingnum =new StringBuffer();
        for(int i=0;i<tjfxTotalEchars.size();i++){
            avgTime.append(tjfxTotalEchars.get(i).getAvgTime()).append(",");
            zongliuliangnum.append(tjfxTotalEchars.get(i).getZongliuliangnum()).append(",");
            zongchaozainum.append(tjfxTotalEchars.get(i).getZongchaozainum()).append(",");
            yijiyujingnum.append(tjfxTotalEchars.get(i).getYijiyujingnum()).append(",");
            erjiyujingnum.append(tjfxTotalEchars.get(i).getErjiyujingnum()).append(",");
        }
        if(avgTime.length()>0){
            avgTime.substring(0, avgTime.length()-1);
        }
        if(zongliuliangnum.length()>0){
            zongliuliangnum.substring(0, zongliuliangnum.length()-1);
        }
        if(zongchaozainum.length()>0){
            zongchaozainum.substring(0, zongchaozainum.length()-1);
        }
        if(yijiyujingnum.length()>0){
            yijiyujingnum.substring(0, yijiyujingnum.length()-1);
        }
        if(erjiyujingnum.length()>0){
            erjiyujingnum.substring(0, erjiyujingnum.length()-1);
        }
        ztqk.setAvgTime(avgTime.toString());
        ztqk.setZongliuliangnum(zongliuliangnum.toString());
        ztqk.setZongchaozainum(zongchaozainum.toString());
        ztqk.setYijiyujingnum(yijiyujingnum.toString());
        ztqk.setErjiyujingnum(erjiyujingnum.toString());
        return ztqk;
    }


}
