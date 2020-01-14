package com.ldxx.dao;

import com.ldxx.vo.tWimMsgVo;

import java.util.List;

public interface tWimMsgDao {

    List<tWimMsgVo> getAlltWimMsg(String stationPort);

    List<tWimMsgVo> getAlltWimMsgByCondition(String stationPort, String startTime, String endTime,Double startWeight,Double endWeight,String chexing);

    int getMeiRiCheLiuLiangByStationPort(String stationPort);

    int getMeiRiChaoZhongByStationPort(String stationPort);

    int getMeiRiGuanJianCheLiangByStationPort(String stationPort);
}
