package com.ldxx.controller;

import com.ldxx.bean.tAvgDay;
import com.ldxx.bean.tAvgMonth;
import com.ldxx.vo.tAvgDayColumn;
import com.ldxx.service.tAvgDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("tAvgDay")
@RestController
public class tAvgDayController {

    @Autowired
    private tAvgDayService service;

    @RequestMapping("/gettAvgDayByIpAndTime")
    public tAvgDay gettAvgDayByIpAndTime(String stationIP, String avgTime,int avgLaneNo){
        tAvgDay tAvgDay=service.gettAvgDayByIpAndTime(stationIP,avgTime,avgLaneNo);
        return tAvgDay;
    }


    @RequestMapping("/gettAvgDayColumnByIpAndTime")
    public List<tAvgDayColumn>  gettAvgDayColumnByIpAndTime(String stationIP, String avgTime, int avgLaneNo){
        List<tAvgDayColumn> tAvgDayColumn=service.gettAvgDayColumnByIpAndTime(stationIP,avgTime,avgLaneNo);
        return tAvgDayColumn;
    }

    @RequestMapping("/gettAvgDayColumnByIpAndTimeMonth")
    public List<tAvgDayColumn>  gettAvgDayColumnByIpAndTimeMonth(String stationIP, String avgTime, int avgLaneNo){
        List<tAvgDayColumn> tAvgDayColumn=service.gettAvgDayColumnByIpAndTimeMonth(stationIP,avgTime,avgLaneNo);
        return tAvgDayColumn;
    }


    @RequestMapping("/getEchartsListByLaneNoAndColumn")
    public Map getEchartsListByLaneNoAndColumn(String stationIP, String column, String laneNo){
        Map tAvgDayColumn=service.getEchartsListByLaneNoAndColumn(stationIP,column,laneNo);
        return tAvgDayColumn;
    }

    @RequestMapping("/gettAvgMonthByIpAndTime")
    public tAvgMonth gettAvgMonthByIpAndTime(String stationIP, String avgTime, int avgLaneNo){
        tAvgMonth tAvgMonth=service.gettAvgMonthByIpAndTime(stationIP,avgTime,avgLaneNo);
        return tAvgMonth;
    }


}
