package com.ldxx.dao;

import com.ldxx.bean.tAvgDay;
import com.ldxx.bean.tAvgMonth;
import com.ldxx.bean.tjfxTotalEchars;
import com.ldxx.vo.tAvgDayColumn;

import java.util.List;

public interface tAvgDayDao {

    tAvgDay gettAvgDayByIpAndTime(String stationIP, String avgTime, int avgLaneNo);

    tAvgDay gettAvgDayTotalByTime(String avgTime);

    List<tAvgDayColumn> gettAvgDayColumnByIpAndTime(String stationIP, String avgTime, int avgLaneNo);

    List<tAvgDayColumn> gettAvgDayColumnByIpAndTime_week(String stationIP, String avgTime, int avgLaneNo);

    List<tAvgDayColumn> gettAvgDayColumnByCompanyNameAndTime_lgs(String companyName, String avgTime, int avgLaneNo);

    List<tAvgDayColumn> gettAvgDayColumnByCompanyNameAndTime_lgs_week(String companyName, String avgTime, int avgLaneNo);

    List<tAvgDayColumn> gettAvgDayColumnByCompanyNameAndTime_sj(String provinceName, String avgTime, int avgLaneNo);

    List<tAvgDayColumn> gettAvgDayColumnByCompanyNameAndTime_sj_week(String provinceName, String avgTime, int avgLaneNo);

    List<tAvgDayColumn> gettAvgDayColumnByIpAndTimeMonth(String stationIP, String avgTime, int avgLaneNo);

    List<tAvgDayColumn> gettAvgDayColumnByIpAndTimeMonth_lgs(String companyName, String avgTime, int avgLaneNo);

    List<tAvgDayColumn> gettAvgDayColumnByIpAndTimeMonth_sj(String provinceName, String avgTime, int avgLaneNo);

    List<String> getEchartsListByLaneNoAndColumnX(String stationIP, String columnName, String laneNo);

    List<String> getEchartsListByLaneNoAndColumnY(String stationIP, String columnName, String laneNo);

    List<String> getEchartsListByLaneNoAndColumnX_week(String stationIP, String columnName, String laneNo);

    List<String> getEchartsListByLaneNoAndColumnY_week(String stationIP, String columnName, String laneNo);

    List<String> getEchartsListByLaneNoAndColumnX_lgs(String companyName, String columnName, String laneNo);

    List<String> getEchartsListByLaneNoAndColumnY_lgs(String companyName, String columnName, String laneNo);

    List<String> getEchartsListByLaneNoAndColumnX_lgs_week(String companyName, String columnName, String laneNo);

    List<String> getEchartsListByLaneNoAndColumnY_lgs_week(String companyName, String columnName, String laneNo);

    List<String> getEchartsListByLaneNoAndColumnX_sj(String provinceName, String columnName, String laneNo);

    List<String> getEchartsListByLaneNoAndColumnY_sj(String provinceName, String columnName, String laneNo);

    List<String> getEchartsListByLaneNoAndColumnX_sj_week(String provinceName, String columnName, String laneNo);

    List<String> getEchartsListByLaneNoAndColumnY_sj_week(String provinceName, String columnName, String laneNo);

    List<String> getEchartsListByLaneNoAndColumnXMonth(String stationIP, String columnName, String laneNo);

    List<String> getEchartsListByLaneNoAndColumnYMonth(String stationIP, String columnName, String laneNo);

    List<String> getEchartsListByLaneNoAndColumnXMonth_lgs(String companyName, String columnName, String laneNo);

    List<String> getEchartsListByLaneNoAndColumnYMonth_lgs(String companyName, String columnName, String laneNo);

    List<String> getEchartsListByLaneNoAndColumnXMonth_sj(String provinceName, String columnName, String laneNo);

    List<String> getEchartsListByLaneNoAndColumnYMonth_sj(String provinceName, String columnName, String laneNo);

    String getColumnNameStrByTableName(String tableName);

    tAvgMonth gettAvgMonthByIpAndTime(String stationIP, String avgTime, int avgLaneNo);

    List<tjfxTotalEchars> gettjfxTotalEchars();
}
