package com.ldxx.service;

import com.ldxx.bean.tAvgDay;
import com.ldxx.bean.tAvgMonth;
import com.ldxx.vo.tAvgDayColumn;

import java.util.List;
import java.util.Map;

public interface tAvgDayService {

    tAvgDay gettAvgDayByIpAndTime(String stationIP, String avgTime, int avgLaneNo);

    List<tAvgDayColumn> gettAvgDayColumnByIpAndTime(String stationIP, String avgTime, int avgLaneNo);

    List<tAvgDayColumn> gettAvgDayColumnByIpAndTime_week(String stationIP, String avgTime, int avgLaneNo);


    List<tAvgDayColumn> gettAvgDayColumnByIpAndTimeMonth(String stationIP, String avgTime, int avgLaneNo);


    Map<String, List<String>> getEchartsListByLaneNoAndColumn(String stationIP, String column, String laneNo);

    Map<String, List<String>> getEchartsListByLaneNoAndColumn_week(String stationIP, String column, String laneNo);

    Map<String, List<String>> getEchartsListByLaneNoAndColumn_lgs(String companyName, String column, String laneNo);

    Map<String, List<String>> getEchartsListByLaneNoAndColumn_lgs_week(String companyName, String column, String laneNo);

    Map<String, List<String>> getEchartsListByLaneNoAndColumn_sj(String provinceName, String column, String laneNo);

    Map<String, List<String>> getEchartsListByLaneNoAndColumn_sj_week(String provinceName, String column, String laneNo);

    Map<String, List<String>> getEchartsListByLaneNoAndColumnMonth(String stationIP, String column, String laneNo);

    Map<String, List<String>> getEchartsListByLaneNoAndColumnMonth_lgs(String companyName, String column, String laneNo);

    Map<String, List<String>> getEchartsListByLaneNoAndColumnMonth_sj(String provinceName, String column, String laneNo);

    tAvgMonth gettAvgMonthByIpAndTime(String stationIP, String avgTime, int avgLaneNo);
}
