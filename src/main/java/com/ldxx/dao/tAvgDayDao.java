package com.ldxx.dao;

import com.ldxx.bean.tAvgDay;
import com.ldxx.bean.tAvgMonth;
import com.ldxx.vo.tAvgDayColumn;

import java.util.List;

public interface tAvgDayDao {

    tAvgDay gettAvgDayByIpAndTime(String stationIP,  String avgTime,int avgLaneNo);

    List<tAvgDayColumn> gettAvgDayColumnByIpAndTime(String stationIP, String avgTime, int avgLaneNo);


    List<tAvgDayColumn> gettAvgDayColumnByIpAndTimeMonth(String stationIP, String avgTime, int avgLaneNo);

    List<String> getEchartsListByLaneNoAndColumnX(String stationIP, String columnName, String laneNo);

    List<String> getEchartsListByLaneNoAndColumnY(String stationIP, String columnName, String laneNo);

    List<String> getEchartsListByLaneNoAndColumnXMonth(String stationIP, String columnName, String laneNo);

    List<String> getEchartsListByLaneNoAndColumnYMonth(String stationIP, String columnName, String laneNo);

    String getColumnNameStrByTableName(String tableName);

    tAvgMonth gettAvgMonthByIpAndTime(String stationIP, String avgTime, int avgLaneNo);
}
