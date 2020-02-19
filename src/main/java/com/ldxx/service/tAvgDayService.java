package com.ldxx.service;

import com.ldxx.bean.tAvgDay;
import com.ldxx.vo.tAvgDayColumn;

public interface tAvgDayService {

    tAvgDay gettAvgDayByIpAndTime(String stationIP,  String avgTime,int avgLaneNo);

    tAvgDayColumn gettAvgDayColumnByIpAndTime(String stationIP, String avgTime, int avgLaneNo);
}
