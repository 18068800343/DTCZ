package com.ldxx.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ldxx.bean.TongJiTableQuery;
import com.ldxx.dao.tWimMsgDao;
import com.ldxx.service.tWimMsgService;
import com.ldxx.vo.tWimMsgVo;
import org.apache.ibatis.annotations.Param;
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
    public List<tWimMsgVo> getAlltWimMsgByCondition(TongJiTableQuery tongJiTableQuery) {

        tongJiTableQuery.setStart(1);
        tongJiTableQuery.setLength(1);
        return dao.getAlltWimMsgByCondition(tongJiTableQuery);
    }

    @Override
    public List<tWimMsgVo>  getAlltWimMsgByConditionByPage(TongJiTableQuery tongJiTableQuery) {

        //PageHelper.startPage(tongJiTableQuery.getStart(), tongJiTableQuery.getLength());


        List<tWimMsgVo> list = dao.getAlltWimMsgByCondition(tongJiTableQuery);
        //PageInfo<tWimMsgVo> pageInfo = new PageInfo<>(list);
        return list;
    }

    @Override
    public List<tWimMsgVo>  getAlltWimMsgYiChangByConditionByPage(TongJiTableQuery tongJiTableQuery) {

        //PageHelper.startPage(tongJiTableQuery.getStart(), tongJiTableQuery.getLength());


        List<tWimMsgVo> list = dao.getAlltWimMsgYiChangByCondition(tongJiTableQuery);
        //PageInfo<tWimMsgVo> pageInfo = new PageInfo<>(list);
        return list;
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
    public int getCountByTableName(@Param("tjq") TongJiTableQuery tongJiTableQuery) {

        return dao.getCountByTableName(tongJiTableQuery);
    }

    @Override
    public int getCountYiChangByTableName(@Param("tjq") TongJiTableQuery tongJiTableQuery) {

        return dao.getCountYiChangByTableName(tongJiTableQuery);
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
    public List<tWimMsgVo> getMeiRiGuanJianChaoZHongShujuByStationPort(String stationPort,Integer lv) {
        return dao.getMeiRiGuanJianChaoZHongShujuByStationPort(stationPort,lv);

    }

    @Override
    public List<tWimMsgVo> getMeiRiCheLiuLiangShujuByStationPort(String stationPort) {
        return dao.getMeiRiCheLiuLiangShujuByStationPort(stationPort);
    }

    @Override
    public tWimMsgVo gettWimMsgById(String idLocal) {
        return dao.gettWimMsgById(idLocal);
    }
    @Override
    public tWimMsgVo gettWimMsgYiChangById(String idLocal) {
        return dao.gettWimMsgYiChangById(idLocal);
    }
}
