package com.ldxx.controller;

import com.ldxx.bean.tAvgDay;
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


    @RequestMapping("/getEchartsListByLaneNoAndColumn")
    public Map getEchartsListByLaneNoAndColumn(String stationIP, String laneNo, String column){
        Map tAvgDayColumn=service.getEchartsListByLaneNoAndColumn(stationIP,laneNo,column);
        return tAvgDayColumn;
    }


}
