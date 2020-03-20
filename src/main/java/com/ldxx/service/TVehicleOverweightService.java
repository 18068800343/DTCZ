package com.ldxx.service;

import com.ldxx.vo.tWimMsgVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TVehicleOverweightService {

    List<tWimMsgVo> getMeiRiChaoZaiShujuByStationPortCondition(String stationPorts,String startTime,String endTime);

    List<tWimMsgVo> getMeiRiGuanJianChaoZHongShujuByStationPort(String stationPorts,Integer lv,String startTime,String endTime);

    int addtVehicleOverweightByidLocal(String idLocal);

    int addtVehicleOverweightList(List<tWimMsgVo> tWimMsgVoList);

    int delVehicleOverweightTemp(List<tWimMsgVo> tWimMsgVoList);

    List<tWimMsgVo> getMeiRiChaoZaiShujuByStationPortAlreadychuli(String stationPorts,String startTime,String endTime);

    List<tWimMsgVo> getMeiRiGuanJianChaoZHongShujuByStationPortAlreadychuli(String stationPorts,Integer lv,String startTime,String endTime);

    int quxiaotVehicleOverweight(String idLocal);
}
