package com.ldxx.controller;

import com.ldxx.bean.tAvgDay;
import com.ldxx.service.tAvgDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
