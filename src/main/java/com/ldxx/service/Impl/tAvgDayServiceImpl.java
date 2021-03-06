package com.ldxx.service.Impl;

import com.ldxx.bean.tAvgDay;
import com.ldxx.bean.tAvgMonth;
import com.ldxx.dao.tAvgDayDao;
import com.ldxx.service.tAvgDayService;
import com.ldxx.vo.tAvgDayColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class tAvgDayServiceImpl implements tAvgDayService {

    @Resource
    private tAvgDayDao dao;

    @Override
    public tAvgDay gettAvgDayByIpAndTime(String stationIP, String avgTime, int avgLaneNo) {
        return dao.gettAvgDayByIpAndTime(stationIP, avgTime, avgLaneNo);
    }

    @Override
    public List<tAvgDayColumn> gettAvgDayColumnByIpAndTime(String stationIP, String avgTime, int avgLaneNo) {
        return dao.gettAvgDayColumnByIpAndTime(stationIP, avgTime, avgLaneNo);
    }

    @Override
    public List<tAvgDayColumn> gettAvgDayColumnByIpAndTime_week(String stationIP, String avgTime, int avgLaneNo) {
        return dao.gettAvgDayColumnByIpAndTime_week(stationIP, avgTime, avgLaneNo);
    }

    @Override
    public List<tAvgDayColumn> gettAvgDayColumnByIpAndTimeMonth(String stationIP, String avgTime, int avgLaneNo) {
        return dao.gettAvgDayColumnByIpAndTimeMonth(stationIP, avgTime, avgLaneNo);
    }

    @Override
    public Map<String, List<String>> getEchartsListByLaneNoAndColumn(String stationIP, String column, String laneNo) {
        Map map = new HashMap();
        Integer columnIndex = Integer.parseInt(column);
        String columnName = dao.getColumnNameStrByTableName("t_avg_day").split(",")[columnIndex - 1];
        List listX = dao.getEchartsListByLaneNoAndColumnX(stationIP, columnName, laneNo);
        List listY = dao.getEchartsListByLaneNoAndColumnY(stationIP, columnName, laneNo);
        map.put("y", listX);
        map.put("x", listY);

        return map;
    }

    @Override
    public Map<String, List<String>> getEchartsListByLaneNoAndColumn_week(String stationIP, String column, String laneNo) {
        Map map = new HashMap();
        Integer columnIndex = Integer.parseInt(column);
        String columnName = dao.getColumnNameStrByTableName("t_avg_week").split(",")[columnIndex - 1];
        List listX = dao.getEchartsListByLaneNoAndColumnX_week(stationIP, columnName, laneNo);
        List listY = dao.getEchartsListByLaneNoAndColumnY_week(stationIP, columnName, laneNo);
        map.put("y", listX);
        map.put("x", listY);

        return map;
    }

    @Override
    public Map<String, List<String>> getEchartsListByLaneNoAndColumn_lgs(String companyName, String column, String laneNo) {
        Map map = new HashMap();
        Integer columnIndex = Integer.parseInt(column);
        String columnName = dao.getColumnNameStrByTableName("t_avg_day_lgs").split(",")[columnIndex - 1];
        List listX = dao.getEchartsListByLaneNoAndColumnX_lgs(companyName, columnName, laneNo);
        List listY = dao.getEchartsListByLaneNoAndColumnY_lgs(companyName, columnName, laneNo);
        map.put("y", listX);
        map.put("x", listY);

        return map;
    }

    @Override
    public Map<String, List<String>> getEchartsListByLaneNoAndColumn_lgs_week(String companyName, String column, String laneNo) {
        Map map = new HashMap();
        Integer columnIndex = Integer.parseInt(column);
        String columnName = dao.getColumnNameStrByTableName("t_avg_week_lgs").split(",")[columnIndex - 1];
        List listX = dao.getEchartsListByLaneNoAndColumnX_lgs_week(companyName, columnName, laneNo);
        List listY = dao.getEchartsListByLaneNoAndColumnY_lgs_week(companyName, columnName, laneNo);
        map.put("y", listX);
        map.put("x", listY);

        return map;
    }

    @Override
    public Map<String, List<String>> getEchartsListByLaneNoAndColumn_sj(String provinceName, String column, String laneNo) {
        Map map = new HashMap();
        Integer columnIndex = Integer.parseInt(column);
        String columnName = dao.getColumnNameStrByTableName("t_avg_day").split(",")[columnIndex - 1];
        List listX = dao.getEchartsListByLaneNoAndColumnX_sj(provinceName, columnName, laneNo);
        List listY = dao.getEchartsListByLaneNoAndColumnY_sj(provinceName, columnName, laneNo);
        map.put("y", listX);
        map.put("x", listY);

        return map;
    }

    @Override
    public Map<String, List<String>> getEchartsListByLaneNoAndColumn_sj_week(String provinceName, String column, String laneNo) {
        Map map = new HashMap();
        Integer columnIndex = Integer.parseInt(column);
        String columnName = dao.getColumnNameStrByTableName("t_avg_week").split(",")[columnIndex - 1];
        List listX = dao.getEchartsListByLaneNoAndColumnX_sj_week(provinceName, columnName, laneNo);
        List listY = dao.getEchartsListByLaneNoAndColumnY_sj_week(provinceName, columnName, laneNo);
        map.put("y", listX);
        map.put("x", listY);

        return map;
    }

    @Override
    public Map<String, List<String>> getEchartsListByLaneNoAndColumnMonth(String stationIP, String column, String laneNo) {
        Map map = new HashMap();
        Integer columnIndex = Integer.parseInt(column);

        String columnNameStr = dao.getColumnNameStrByTableName("t_avg_month");
        String columnName = dao.getColumnNameStrByTableName("t_avg_month").split(",")[columnIndex - 1];

        List listX = dao.getEchartsListByLaneNoAndColumnXMonth(stationIP, columnName, laneNo);
        List listY = dao.getEchartsListByLaneNoAndColumnYMonth(stationIP, columnName, laneNo);

        map.put("y", listX);
        map.put("x", listY);

        return map;
    }

    @Override
    public Map<String, List<String>> getEchartsListByLaneNoAndColumnMonth_lgs(String companyName, String column, String laneNo) {
        Map map = new HashMap();
        Integer columnIndex = Integer.parseInt(column);

        String columnNameStr = dao.getColumnNameStrByTableName("t_avg_month");
        String columnName = dao.getColumnNameStrByTableName("t_avg_month").split(",")[columnIndex - 1];

        List listX = dao.getEchartsListByLaneNoAndColumnXMonth_lgs(companyName, columnName, laneNo);
        List listY = dao.getEchartsListByLaneNoAndColumnYMonth_lgs(companyName, columnName, laneNo);

        map.put("y", listX);
        map.put("x", listY);

        return map;
    }

    @Override
    public Map<String, List<String>> getEchartsListByLaneNoAndColumnMonth_sj(String provinceName, String column, String laneNo) {
        Map map = new HashMap();
        Integer columnIndex = Integer.parseInt(column);

        String columnNameStr = dao.getColumnNameStrByTableName("t_avg_month");
        String columnName = dao.getColumnNameStrByTableName("t_avg_month").split(",")[columnIndex - 1];

        List listX = dao.getEchartsListByLaneNoAndColumnXMonth_sj(provinceName, columnName, laneNo);
        List listY = dao.getEchartsListByLaneNoAndColumnYMonth_sj(provinceName, columnName, laneNo);

        map.put("y", listX);
        map.put("x", listY);

        return map;
    }

    @Override
    public tAvgMonth gettAvgMonthByIpAndTime(String stationIP, String avgTime, int avgLaneNo) {
        return dao.gettAvgMonthByIpAndTime(stationIP, avgTime, avgLaneNo);
    }
}
