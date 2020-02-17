package com.ldxx.service;

import com.ldxx.bean.tAvgDay;

public interface tAvgDayService {

    tAvgDay gettAvgDayByIpAndTime(String stationIP,  String avgTime,int avgLaneNo);
}
