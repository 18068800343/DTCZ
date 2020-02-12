package com.ldxx.dao;

import com.ldxx.bean.TongJiTableQuery;
import com.ldxx.vo.tWimMsgVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface tWimMsgDao {

    List<tWimMsgVo> getAlltWimMsg(String stationPort);

    List<tWimMsgVo> getAlltWimMsgByCondition(@Param("tjq") TongJiTableQuery tongJiTableQuery);

    int getMeiRiCheLiuLiangByStationPort(String stationPort);

    int getMeiRiCheLiuLiangMaxByStationPort();

    int getMeiRiGuanJianCheLiangMax();

    int getCountByTableName(@Param("tjq") TongJiTableQuery tongJiTableQuery);

    int getMeiRiChaoZhongByStationPort(String stationPort);

    int getMeiRiGuanJianCheLiangByStationPort(String stationPort);

    List<tWimMsgVo> getMeiRiChaoZaiShujuByStationPort(String stationPort);

    List<tWimMsgVo> getMeiRiGuanJianChaoZHongShujuByStationPort(String stationPort);

    List<tWimMsgVo> getMeiRiCheLiuLiangShujuByStationPort(String stationPort);

    tWimMsgVo gettWimMsgById(String idLocal);
}
