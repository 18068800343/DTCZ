package com.ldxx.service;

import com.ldxx.vo.tWimMsgVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TVehicleOverweightService {

    List<tWimMsgVo> getMeiRiChaoZaiShujuByStationPort(String stationPorts);

    List<tWimMsgVo> getMeiRiGuanJianChaoZHongShujuByStationPort(String stationPorts,Integer lv);

    int addtVehicleOverweight(tWimMsgVo tWimMsgVo);

    int addtVehicleOverweightList(/*List<tWimMsgVo> tWimMsgVoList,*/String stationPorts);

    int delVehicleOverweightTemp(List<tWimMsgVo> tWimMsgVoList);

    List<tWimMsgVo> getMeiRiChaoZaiShujuByStationPortAlreadychuli(String stationPorts);

    List<tWimMsgVo> getMeiRiGuanJianChaoZHongShujuByStationPortAlreadychuli(String stationPorts,Integer lv);
}
