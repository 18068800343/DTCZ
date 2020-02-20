package com.ldxx.service.Impl;

import com.ldxx.bean.tAvgDay;
import com.ldxx.dao.tAvgDayDao;
import com.ldxx.service.tAvgDayService;
import com.ldxx.vo.tAvgDayColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class tAvgDayServiceImpl implements tAvgDayService {

    @Autowired
    private tAvgDayDao dao;

    @Override
    public tAvgDay gettAvgDayByIpAndTime(String stationIP,  String avgTime,int avgLaneNo) {
        return dao.gettAvgDayByIpAndTime(stationIP, avgTime,avgLaneNo);
    }

    @Override
    public List<tAvgDayColumn> gettAvgDayColumnByIpAndTime(String stationIP, String avgTime, int avgLaneNo) {
        return dao.gettAvgDayColumnByIpAndTime(stationIP, avgTime,avgLaneNo);
    }

    @Override
    public Map<String,List<String>> getEchartsListByLaneNoAndColumn(String stationIP, String column, String laneNo) {
        Map map = new HashMap();

        String columnName = dao.getColumnNameStrByTableName("");

        List listX = dao.getEchartsListByLaneNoAndColumnX(stationIP,column,laneNo);
        List listY = dao.getEchartsListByLaneNoAndColumnY(stationIP,column,laneNo);

        map.put("x",listX);
        map.put("y",listY);

        return map;
    }
}
