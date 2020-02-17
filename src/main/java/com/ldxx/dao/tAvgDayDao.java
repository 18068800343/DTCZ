package com.ldxx.dao;

import com.ldxx.bean.tAvgDay;

public interface tAvgDayDao {

    tAvgDay gettAvgDayByIpAndTime(String stationIP,  String avgTime,int avgLaneNo);
}
