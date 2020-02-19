package com.ldxx.service;

import com.ldxx.bean.tAvgDay;
import com.ldxx.vo.tAvgDayColumn;

import java.util.List;

public interface tAvgDayService {

    tAvgDay gettAvgDayByIpAndTime(String stationIP,  String avgTime,int avgLaneNo);

    List<tAvgDayColumn> gettAvgDayColumnByIpAndTime(String stationIP, String avgTime, int avgLaneNo);
}
