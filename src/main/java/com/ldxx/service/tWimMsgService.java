package com.ldxx.service;

import com.github.pagehelper.PageInfo;
import com.ldxx.bean.TongJiTableQuery;
import com.ldxx.vo.tWimMsgVo;

import java.util.List;

public interface tWimMsgService {

    List<tWimMsgVo> getAlltWimMsg(String stationPort);

    List<tWimMsgVo> getAlltWimMsgByCondition(String stationPort,String startTime,String endTime,Double startWeight,Double endWeight,String chexing);

    PageInfo<tWimMsgVo> getAlltWimMsgByConditionByPage(TongJiTableQuery tongJiTableQuery);

    int getMeiRiCheLiuLiangByStationPort(String stationPort);

    int getMeiRiChaoZhongByStationPort(String stationPort);

    int getMeiRiGuanJianCheLiangByStationPort(String stationPort);

    List<tWimMsgVo> getMeiRiChaoZaiShujuByStationPort(String stationPort);

    List<tWimMsgVo> getMeiRiGuanJianChaoZHongShujuByStationPort(String stationPort);

    List<tWimMsgVo> getMeiRiCheLiuLiangShujuByStationPort(String stationPort);
}
