package com.ldxx.dao;

import com.ldxx.bean.CheLiuLiangEchartsList;
import com.ldxx.bean.HomeData;
import com.ldxx.bean.TongJiTableQuery;
import com.ldxx.vo.tWimMsgVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface tWimMsgDao {

    List<tWimMsgVo> getAlltWimMsg(@Param("stationPort")String stationPort);

    List<tWimMsgVo> getAlltWimMsgByCondition(@Param("tjq") TongJiTableQuery tongJiTableQuery);

    CheLiuLiangEchartsList getCheLiuLiangEchartsList(String stationPorts);


    CheLiuLiangEchartsList getChaoZaiEchartsList(String stationPorts);


    HomeData getHomeData(String stationPorts);

    int getMeiRiCheLiuLiangByStationPort(@Param("stationPort")String stationPort);

    int getMeiRiCheLiuLiangMaxByStationPort();

    int getMeiRiGuanJianCheLiangMax();

    int getCountByTableName(@Param("tjq") TongJiTableQuery tongJiTableQuery);

    int getMeiRiChaoZhongByStationPort(@Param("stationPort")String stationPort);

    int getMeiRiGuanJianCheLiangByStationPort(@Param("stationPort")String stationPort);

    List<tWimMsgVo> getMeiRiChaoZaiShujuByStationPort(@Param("stationPort")String stationPort);

    List<tWimMsgVo> getMeiRiGuanJianChaoZHongShujuByStationPort(@Param("stationPort")String stationPort);

    List<tWimMsgVo> getMeiRiCheLiuLiangShujuByStationPort(@Param("stationPort")String stationPort);

    tWimMsgVo gettWimMsgById(String idLocal);
}
