package com.ldxx.service;

import com.ldxx.bean.tAvgDay;
import com.ldxx.vo.tAvgDayColumn;

import java.util.List;
import java.util.Map;

public interface tAvgDayService {

    tAvgDay gettAvgDayByIpAndTime(String stationIP,  String avgTime,int avgLaneNo);

    List<tAvgDayColumn> gettAvgDayColumnByIpAndTime(String stationIP, String avgTime, int avgLaneNo);


    Map<String,List<String>> getEchartsListByLaneNoAndColumn(String stationIP, String column, String laneNo);
}
