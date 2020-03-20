package com.ldxx.dao;

import com.ldxx.vo.tWimMsgVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface tVehicleOverweightDao {

    List<tWimMsgVo> getMeiRiChaoZaiShujuByStationPortCondition(@Param("stationPorts")String stationPorts,@Param("startTime")String startTime,@Param("endTime")String endTime);

    List<tWimMsgVo> getMeiRiChaoZaiShujuByStationPort(@Param("stationPorts")String stationPorts);

    List<tWimMsgVo> getMeiRiGuanJianChaoZHongShujuByStationPort(@Param("stationPorts")String stationPorts,@Param("lv")Integer lv,@Param("startTime")String startTime,@Param("endTime")String endTime);

    int addtVehicleOverweightByidLocal(@Param("idLocal")String idLocal);

    List<tWimMsgVo> getidLocalBystationPorts(@Param("stationPorts")String stationPorts);

    int addtVehicleOverweightList(@Param("tWimMsgVoList") List<tWimMsgVo> tWimMsgVoList);

    int delVehicleOverweightTemp(@Param("tWimMsgVoList") List<tWimMsgVo> tWimMsgVoList);

    List<tWimMsgVo> getMeiRiChaoZaiShujuByStationPortAlreadychuli(@Param("stationPorts")String stationPorts,@Param("startTime")String startTime,@Param("endTime")String endTime);

    List<tWimMsgVo> getMeiRiGuanJianChaoZHongShujuByStationPortAlreadychuli(@Param("stationPorts")String stationPorts,@Param("lv")Integer lv,@Param("startTime")String startTime,@Param("endTime")String endTime);

    List<tWimMsgVo> getMeiRiGuanJianChaoZaiShujuByidLocals(@Param("idLocals")String idLocals,@Param("lv")Integer lv);

    int addtVehicleOverweightTempByidLocal(@Param("idLocal")String idLocal);

    int delVehicleOverweight(@Param("idLocal")String idLocal);
}
