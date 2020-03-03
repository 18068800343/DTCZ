package com.ldxx.service;

import com.github.pagehelper.PageInfo;
import com.ldxx.bean.TongJiTableQuery;
import com.ldxx.vo.tWimMsgVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface tWimMsgService {

    List<tWimMsgVo> getAlltWimMsg(String stationPort);

    List<tWimMsgVo> getAlltWimMsgByCondition(TongJiTableQuery tongJiTableQuery);

    List<tWimMsgVo> getAlltWimMsgByConditionByPage(TongJiTableQuery tongJiTableQuery);

    int getMeiRiCheLiuLiangByStationPort(String stationPort);

    int getMeiRiChaoZhongByStationPort(String stationPort);

    int getCountByTableName(@Param("tjq") TongJiTableQuery tongJiTableQuery);

    int getMeiRiGuanJianCheLiangByStationPort(String stationPort);

    List<tWimMsgVo> getMeiRiChaoZaiShujuByStationPort(String stationPort);

    List<tWimMsgVo> getMeiRiGuanJianChaoZHongShujuByStationPort(String stationPort,Integer lv);

    List<tWimMsgVo> getMeiRiCheLiuLiangShujuByStationPort(String stationPort);

    tWimMsgVo gettWimMsgById(String idLocal);
}
