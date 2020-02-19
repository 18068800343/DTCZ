package com.ldxx.dao;

import com.ldxx.bean.tAvgDay;
import com.ldxx.vo.tAvgDayColumn;

public interface tAvgDayDao {

    tAvgDay gettAvgDayByIpAndTime(String stationIP,  String avgTime,int avgLaneNo);

    tAvgDayColumn gettAvgDayColumnByIpAndTime(String stationIP, String avgTime, int avgLaneNo);
}
