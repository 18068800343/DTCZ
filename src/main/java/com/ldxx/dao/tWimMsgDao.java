package com.ldxx.dao;

import com.ldxx.bean.*;
import com.ldxx.vo.tWimMsgVo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface tWimMsgDao {

    List<tWimMsgVo> getAlltWimMsg(@Param("stationPort") String stationPort);

    List<tWimMsgVo> getAlltWimMsgByCondition(TongJiTableQuery tongJiTableQuery);

    List<tWimMsgVo> getAlltWimMsgYiChangByCondition(TongJiTableQuery tongJiTableQuery);

    CheLiuLiangEchartsList getCheLiuLiangEchartsList(String stationPorts, Integer link, Integer limit);

    CheLiuLiangEchartsList initLiuZhouChaoZaiShuLiang(String stationPorts, Integer link, Integer limit);


    CheLiuLiangEchartsList getChaoZaiEchartsList(String stationPorts, Integer link, Integer limit);

    List<CheLiuLiangEchartsList> getFirChaoZaiLv(String stationPorts, Integer link, Integer limit);

    CheLiuLiangEchartsList getGuanJianChaoZhongCheLiangEchartsList(Integer lv1, Integer lv2, String stationPorts, Integer link, Integer limit);


    HomeData getHomeData(String stationPorts);

    Double getMaxWeightByWeight(String stationPorts, Double weight);

    int getMeiRiCheLiuLiangByStationPort(@Param("stationPort") String stationPort);

    int getMeiRiCheLiuLiangMaxByStationPort();

    int getMeiRiGuanJianCheLiangMax();

    int getCountByTableName(TongJiTableQuery tongJiTableQuery);

    int getCountYiChangByTableName(TongJiTableQuery tongJiTableQuery);

    int getMeiRiChaoZhongByStationPort(@Param("stationPort")String stationPort);

    int getMeiRiGuanJianCheLiangByStationPort(@Param("stationPort")String stationPort);

    List<tWimMsgVo> getMeiRiChaoZaiShujuByStationPort(@Param("stationPort")String stationPort);

    List<tWimMsgVo> getMeiRiGuanJianChaoZHongShujuByStationPort(@Param("stationPort")String stationPort,@Param("lv")Integer lv);

    List<tWimMsgVo> getMeiRiCheLiuLiangShujuByStationPort(@Param("stationPort")String stationPort);

    tWimMsgVo gettWimMsgById(String idLocal);
    tWimMsgVo gettWimMsgYiChangById(String idLocal);

    CheLiuLiangEchartsList getGuanJianChaoZhongCheLiangEchartsList2(Integer lv1,Integer lv2,String stationPorts);


    HomeData getJianCeTotal(String stationPorts);

    CheLiuLiangEchartsList getJianCeCheLiuLiangEchartsList(String stationPorts);

    CheLiuLiangEchartsList getJianCechaozaiEchartsList(String stationPorts);

    Map<String, String> getSecHomeTotal(String axlesCount, String stationPorts);

    SecHomeData getSecLiuLiangEcharsList(String axlesCount, String stationPorts, Integer limit);

    SecHomeData getSecChaoZaiEcharsList(String axlesCount, String stationPorts, Integer limit);

    SecHomeData getSecChaoZaiEcharsListMonth(String yearMonth, String axlesCount, String stationPorts, Integer limit);

    SecHomeData getSecChaoZaiEcharsListByZhanDian(String axlesCount, String stationPorts, Integer limit);

   List<DanZhouChaoZai> getDanZhouChaoZai(String stationPorts, Integer limit);

    Map getGongSiTongJiYuJingData(String stationPorts, Integer limit, Integer axlesCount);

    Map getGongSiTongJiYuJingData_LiuZhou(String stationPorts, Integer limit);

    Map initShengJieTongJiYuJingData(String stationPorts, Integer limit);

    CheLiuLiangEchartsList getDiTujwdByPort(String stationPort);

    tjfxTotalEchars getTodayzongliuliangAndzongchaozai(String stationPort);

    List<tjfxTotalEchars> getsexTotalEchars();

    List<tjfxTotalEchars> getsexTotalEcharsByStationPorts(String stationPort);

}
