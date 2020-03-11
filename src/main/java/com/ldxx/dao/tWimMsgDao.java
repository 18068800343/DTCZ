package com.ldxx.dao;

import com.ldxx.bean.CheLiuLiangEchartsList;
import com.ldxx.bean.HomeData;
import com.ldxx.bean.TongJiTableQuery;
import com.ldxx.vo.tWimMsgVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface tWimMsgDao {

    List<tWimMsgVo> getAlltWimMsg(@Param("stationPort")String stationPort);

    List<tWimMsgVo> getAlltWimMsgByCondition( TongJiTableQuery tongJiTableQuery);

    CheLiuLiangEchartsList getCheLiuLiangEchartsList(String stationPorts,Integer link,Integer limit);


    CheLiuLiangEchartsList getChaoZaiEchartsList(String stationPorts,Integer link,Integer limit);

    CheLiuLiangEchartsList getGuanJianChaoZhongCheLiangEchartsList(Integer lv1,Integer lv2,String stationPorts,Integer link,Integer limit);


    HomeData getHomeData(String stationPorts);

    int getMeiRiCheLiuLiangByStationPort(@Param("stationPort")String stationPort);

    int getMeiRiCheLiuLiangMaxByStationPort();

    int getMeiRiGuanJianCheLiangMax();

    int getCountByTableName(TongJiTableQuery tongJiTableQuery);

    int getMeiRiChaoZhongByStationPort(@Param("stationPort")String stationPort);

    int getMeiRiGuanJianCheLiangByStationPort(@Param("stationPort")String stationPort);

    List<tWimMsgVo> getMeiRiChaoZaiShujuByStationPort(@Param("stationPort")String stationPort);

    List<tWimMsgVo> getMeiRiGuanJianChaoZHongShujuByStationPort(@Param("stationPort")String stationPort,@Param("lv")Integer lv);

    List<tWimMsgVo> getMeiRiCheLiuLiangShujuByStationPort(@Param("stationPort")String stationPort);

    tWimMsgVo gettWimMsgById(String idLocal);

    CheLiuLiangEchartsList getGuanJianChaoZhongCheLiangEchartsList2(Integer lv1,Integer lv2,String stationPorts);


    HomeData getJianCeTotal(String stationPorts);
    CheLiuLiangEchartsList getJianCeCheLiuLiangEchartsList(String stationPorts);
    CheLiuLiangEchartsList getJianCechaozaiEchartsList(String stationPorts);
}
