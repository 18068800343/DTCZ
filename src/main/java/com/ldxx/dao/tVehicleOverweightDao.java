package com.ldxx.dao;

import com.ldxx.vo.tWimMsgVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface tVehicleOverweightDao {

    List<tWimMsgVo> getMeiRiChaoZaiShujuByStationPort(@Param("stationPorts")String stationPorts);

    List<tWimMsgVo> getMeiRiGuanJianChaoZHongShujuByStationPort(@Param("stationPorts")String stationPorts,@Param("lv")Integer lv);

    int addtVehicleOverweight(tWimMsgVo tWimMsgVo);

    List<tWimMsgVo> getidLocalBystationPorts(@Param("stationPorts")String stationPorts);

    int addtVehicleOverweightList(/*@Param("tWimMsgVoList") List<tWimMsgVo> tWimMsgVoList*/@Param("stationPorts")String stationPorts);

    int delVehicleOverweightTemp(@Param("tWimMsgVoList") List<tWimMsgVo> tWimMsgVoList);

    List<tWimMsgVo> getMeiRiChaoZaiShujuByStationPortAlreadychuli(@Param("stationPorts")String stationPorts);

    List<tWimMsgVo> getMeiRiGuanJianChaoZHongShujuByStationPortAlreadychuli(@Param("stationPorts")String stationPorts,@Param("lv")Integer lv);
}
