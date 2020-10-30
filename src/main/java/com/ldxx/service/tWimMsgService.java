package com.ldxx.service;

import com.github.pagehelper.PageInfo;
import com.ldxx.bean.CheLiuLiangEchartsList;
import com.ldxx.bean.HomeData;
import com.ldxx.bean.TongJiTableQuery;
import com.ldxx.bean.tjfxTotalEchars;
import com.ldxx.vo.tWimMsgVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface tWimMsgService {

    List<tWimMsgVo> getAlltWimMsg(String stationPort);

    List<tWimMsgVo> getAlltWimMsgByCondition(TongJiTableQuery tongJiTableQuery);

    List<tWimMsgVo> getAlltWimMsgByConditionByPage(TongJiTableQuery tongJiTableQuery);

    List<tWimMsgVo> getAlltWimMsgYiChangByConditionByPage(TongJiTableQuery tongJiTableQuery);

    int getMeiRiCheLiuLiangByStationPort(String stationPort);

    int getMeiRiChaoZhongByStationPort(String stationPort);

    int getCountByTableName(@Param("tjq") TongJiTableQuery tongJiTableQuery);

    int getCountYiChangByTableName(@Param("tjq") TongJiTableQuery tongJiTableQuery);

    int getMeiRiGuanJianCheLiangByStationPort(String stationPort);

    List<tWimMsgVo> getMeiRiChaoZaiShujuByStationPort(String stationPort);

    List<tWimMsgVo> getMeiRiGuanJianChaoZHongShujuByStationPort(String stationPort,Integer lv);

    List<tWimMsgVo> getMeiRiCheLiuLiangShujuByStationPort(String stationPort);

    tWimMsgVo gettWimMsgById(String idLocal);

    tWimMsgVo gettWimMsgYiChangById(String idLocal);

    List<tjfxTotalEchars> getQushitu(String stationPorts);
    List<tjfxTotalEchars> getQushitu2();

    CheLiuLiangEchartsList getDiTujwdByPort(String zhandianduankouhao);
    CheLiuLiangEchartsList getDiTujwdByPort2();

    HomeData getHomeData(String stationPorts);
    HomeData getHomeData2();

    CheLiuLiangEchartsList getCheLiuLiangEchartsList(String stationPorts, Integer link, Integer limit);
    CheLiuLiangEchartsList getCheLiuLiangEchartsList2();

    List<CheLiuLiangEchartsList> getFirChaoZaiLv(String stationPorts, Integer link, Integer limit);
    List<CheLiuLiangEchartsList> getFirChaoZaiLv2( Integer link, Integer limit);

}
