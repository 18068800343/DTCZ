package com.ldxx.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ldxx.bean.TongJiTableQuery;
import com.ldxx.dao.tWimMsgDao;
import com.ldxx.service.tWimMsgService;
import com.ldxx.vo.tWimMsgVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class tWimMsgServiceImpl implements tWimMsgService {

    @Resource
    private tWimMsgDao dao;

    @Override
    public List<tWimMsgVo> getAlltWimMsg(String stationPort) {
        List<tWimMsgVo> list = dao.getAlltWimMsg(stationPort);

        return list;
    }

    @Override
    public List<tWimMsgVo> getAlltWimMsgByCondition(String stationPort, String startTime, String endTime,Double startWeight,Double endWeight,String chexing) {

        return dao.getAlltWimMsgByCondition(stationPort, startTime,endTime,startWeight,endWeight,chexing);
    }

    @Override
    public PageInfo<tWimMsgVo>  getAlltWimMsgByConditionByPage(TongJiTableQuery tongJiTableQuery) {
        PageHelper.startPage(tongJiTableQuery.getStart(), tongJiTableQuery.getLength());
        List<tWimMsgVo> list = dao.getAlltWimMsgByCondition(tongJiTableQuery.getStationPort(), tongJiTableQuery.getStartTime(),tongJiTableQuery.getEndTime(),tongJiTableQuery.getStartWeight(),tongJiTableQuery.getEndWeight(),tongJiTableQuery.getChexing());
        PageInfo<tWimMsgVo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int getMeiRiCheLiuLiangByStationPort(String stationPort) {
        return dao.getMeiRiCheLiuLiangByStationPort(stationPort);
    }

    @Override
    public int getMeiRiChaoZhongByStationPort(String stationPort) {
        return dao.getMeiRiChaoZhongByStationPort(stationPort);
    }

    @Override
    public int getMeiRiGuanJianCheLiangByStationPort(String stationPort) {
        return dao.getMeiRiGuanJianCheLiangByStationPort(stationPort);
    }

    @Override
    public List<tWimMsgVo> getMeiRiChaoZaiShujuByStationPort(String stationPort) {
        return dao.getMeiRiChaoZaiShujuByStationPort(stationPort);
    }

    @Override
    public List<tWimMsgVo> getMeiRiGuanJianChaoZHongShujuByStationPort(String stationPort) {
        return dao.getMeiRiGuanJianChaoZHongShujuByStationPort(stationPort);
    }

    @Override
    public List<tWimMsgVo> getMeiRiCheLiuLiangShujuByStationPort(String stationPort) {
        return dao.getMeiRiCheLiuLiangShujuByStationPort(stationPort);
    }

    @Override
    public tWimMsgVo gettWimMsgById(String idLocal) {
        return dao.gettWimMsgById(idLocal);
    }
}
