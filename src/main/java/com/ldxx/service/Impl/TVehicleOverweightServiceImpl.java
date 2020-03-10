package com.ldxx.service.Impl;

import com.ldxx.dao.tVehicleOverweightDao;
import com.ldxx.service.TVehicleOverweightService;
import com.ldxx.vo.tWimMsgVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TVehicleOverweightServiceImpl implements TVehicleOverweightService {

    @Autowired
    private tVehicleOverweightDao tdao;


    @Override
    public List<tWimMsgVo> getMeiRiChaoZaiShujuByStationPort(String stationPorts) {
        return tdao.getMeiRiChaoZaiShujuByStationPort(stationPorts);
    }

    @Override
    public List<tWimMsgVo> getMeiRiGuanJianChaoZHongShujuByStationPort(String stationPorts, Integer lv) {
        return tdao.getMeiRiGuanJianChaoZHongShujuByStationPort(stationPorts,lv);
    }

    @Override
    public int addtVehicleOverweight(tWimMsgVo tWimMsgVo) {
        int i=tdao.addtVehicleOverweight(tWimMsgVo);
        if(i>0){
            List<tWimMsgVo> list=new ArrayList<>();
            list.add(tWimMsgVo);
            i=tdao.delVehicleOverweightTemp(list);
        }
        return i;
    }

    @Override
    public int addtVehicleOverweightList(/*List<tWimMsgVo> tWimMsgVoList,*/String stationPorts) {
        int i=tdao.addtVehicleOverweightList(stationPorts);
        if(i>0){
            List<tWimMsgVo> list=tdao.getidLocalBystationPorts(stationPorts);
            i=tdao.delVehicleOverweightTemp(list);
        }
        return i;
    }


    @Override
    public int delVehicleOverweightTemp(List<tWimMsgVo> tWimMsgVoList) {
        return tdao.delVehicleOverweightTemp(tWimMsgVoList);
    }

    @Override
    public List<tWimMsgVo> getMeiRiChaoZaiShujuByStationPortAlreadychuli(String stationPorts) {
        return tdao.getMeiRiChaoZaiShujuByStationPortAlreadychuli(stationPorts);
    }

    @Override
    public List<tWimMsgVo> getMeiRiGuanJianChaoZHongShujuByStationPortAlreadychuli(String stationPorts, Integer lv) {
        return tdao.getMeiRiGuanJianChaoZHongShujuByStationPortAlreadychuli(stationPorts,lv);
    }
}
