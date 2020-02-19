package com.ldxx.dao;

import com.ldxx.bean.tAvgDay;
import com.ldxx.vo.tAvgDayColumn;

import java.util.List;

public interface tAvgDayDao {

    tAvgDay gettAvgDayByIpAndTime(String stationIP,  String avgTime,int avgLaneNo);

    List<tAvgDayColumn> gettAvgDayColumnByIpAndTime(String stationIP, String avgTime, int avgLaneNo);
}
