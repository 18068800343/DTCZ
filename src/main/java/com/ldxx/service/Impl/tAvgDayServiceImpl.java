package com.ldxx.service.Impl;

import com.ldxx.bean.tAvgDay;
import com.ldxx.dao.tAvgDayDao;
import com.ldxx.service.tAvgDayService;
import com.ldxx.vo.tAvgDayColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}
