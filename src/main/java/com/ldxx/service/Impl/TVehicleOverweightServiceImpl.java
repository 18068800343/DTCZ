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
    public List<tWimMsgVo> getMeiRiChaoZaiShujuByStationPortCondition(String stationPorts,String startTime,String endTime) {
        return tdao.getMeiRiChaoZaiShujuByStationPortCondition(stationPorts,startTime,endTime);
    }

    @Override
    public List<tWimMsgVo> getMeiRiGuanJianChaoZHongShujuByStationPort(String stationPorts, Integer lv,String startTime,String endTime) {
        return tdao.getMeiRiGuanJianChaoZHongShujuByStationPort(stationPorts,lv,startTime,endTime);
    }

    @Override
    public int addtVehicleOverweightByidLocal(String idLocal) {
        int i=tdao.addtVehicleOverweightByidLocal(idLocal);
        if(i>0){
            List<tWimMsgVo> list=new ArrayList<>();
            tWimMsgVo t=new tWimMsgVo();
            t.setIdLocal(idLocal);
            list.add(t);
            i=tdao.delVehicleOverweightTemp(list);
        }
        return i;
    }

    @Override
    public int addtVehicleOverweightList(List<tWimMsgVo> tWimMsgVoList) {
        //List<tWimMsgVo> tWimMsgVoList=tdao.getMeiRiGuanJianChaoZaiShujuByidLocals(idLocals,lv);;
        /*if(lv==1){
            tWimMsgVoList = tdao.getMeiRiChaoZaiShujuByStationPortCondition(stationPorts,startTime,endTime);
        }else{
            tWimMsgVoList=tdao.getMeiRiGuanJianChaoZHongShujuByStationPort(stationPorts,lv,startTime,endTime);
        }*/

        int i=0;
        if(tWimMsgVoList.size()!=0&&tWimMsgVoList!=null){
            i=tdao.addtVehicleOverweightList(tWimMsgVoList);
            if(i>0){
                i=tdao.delVehicleOverweightTemp(tWimMsgVoList);
            }
        }
        return i;
    }


    @Override
    public int delVehicleOverweightTemp(List<tWimMsgVo> tWimMsgVoList) {
        return tdao.delVehicleOverweightTemp(tWimMsgVoList);
    }

    @Override
    public List<tWimMsgVo> getMeiRiChaoZaiShujuByStationPortAlreadychuli(String stationPorts,String startTime,String endTime) {
        return tdao.getMeiRiChaoZaiShujuByStationPortAlreadychuli(stationPorts,startTime,endTime);
    }

    @Override
    public List<tWimMsgVo> getMeiRiGuanJianChaoZHongShujuByStationPortAlreadychuli(String stationPorts, Integer lv,String startTime,String endTime) {
        return tdao.getMeiRiGuanJianChaoZHongShujuByStationPortAlreadychuli(stationPorts,lv,startTime,endTime);
    }

    @Override
    public int quxiaotVehicleOverweight(String idLocal) {
        int i=tdao.addtVehicleOverweightTempByidLocal(idLocal);
        if(i>0){
            i=tdao.delVehicleOverweight(idLocal);
        }
        return i;
    }
}
