package com.ldxx.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ldxx.bean.CheLiuLiangEchartsList;
import com.ldxx.bean.HomeData;
import com.ldxx.bean.TongJiTableQuery;
import com.ldxx.bean.tjfxTotalEchars;
import com.ldxx.config.DS;
import com.ldxx.dao.tUserInfoDao;
import com.ldxx.dao.tWimMsgDao;
import com.ldxx.service.tWimMsgService;
import com.ldxx.util.GetThisTimeUtils;
import com.ldxx.vo.tUserInfoVo;
import com.ldxx.vo.tWimMsgVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class tWimMsgServiceImpl implements tWimMsgService {

    @Resource
    private tWimMsgDao dao;
    @Autowired
    private tUserInfoDao uDao;

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


    @Override
    public List<tjfxTotalEchars> getQushitu(String stationPorts) {
        String date = GetThisTimeUtils.getDate();
        tjfxTotalEchars toadynum = dao.getTodayzongliuliangAndzongchaozai(stationPorts);
        if(toadynum!=null){
            toadynum.setAvgTime(date);
        }

        List<tjfxTotalEchars> tjfxTotalEchars = dao.getsexTotalEcharsByStationPorts(stationPorts);
        if(tjfxTotalEchars!=null&&tjfxTotalEchars.size()!=0){
            tjfxTotalEchars.add(0, toadynum);
        }

        Collections.reverse(tjfxTotalEchars);
        return tjfxTotalEchars;
    }
    @DS("db2")
    @Override
    public List<tjfxTotalEchars> getQushitu2() {
        tUserInfoVo shiro = uDao.selectUserByUsrName("shiro");
        String stationPorts = shiro.getStationPort();
        String date = GetThisTimeUtils.getDate();
        tjfxTotalEchars toadynum = dao.getTodayzongliuliangAndzongchaozai(stationPorts);
        if(toadynum!=null){
            toadynum.setAvgTime(date);
        }
        List<tjfxTotalEchars> tjfxTotalEchars = dao.getsexTotalEcharsByStationPorts(stationPorts);
        if(tjfxTotalEchars!=null&&tjfxTotalEchars.size()!=0){
            tjfxTotalEchars.add(0, toadynum);
        }

        Collections.reverse(tjfxTotalEchars);
        return tjfxTotalEchars;
    }

    @Override
    public CheLiuLiangEchartsList getDiTujwdByPort(String zhandianduankouhao) {
        return dao.getDiTujwdByPort(zhandianduankouhao);
    }
    @DS("db2")
    @Override
    public CheLiuLiangEchartsList getDiTujwdByPort2() {
        tUserInfoVo shiro = uDao.selectUserByUsrName("shiro");
        String stationPorts = shiro.getStationPort();
        return dao.getDiTujwdByPort(stationPorts);
    }

    @Override
    public HomeData getHomeData(String stationPorts) {
        return  dao.getHomeData(stationPorts);
    }
    @DS("db2")
    @Override
    public HomeData getHomeData2() {
        tUserInfoVo shiro = uDao.selectUserByUsrName("shiro");
        String stationPorts = shiro.getStationPort();
        return  dao.getHomeData(stationPorts);
    }

    @Override
    public CheLiuLiangEchartsList getCheLiuLiangEchartsList(String stationPorts, Integer link, Integer limit) {
        return dao.getCheLiuLiangEchartsList(stationPorts, link, limit);
    }
    @DS("db2")
    @Override
    public CheLiuLiangEchartsList getCheLiuLiangEchartsList2() {
        tUserInfoVo shiro = uDao.selectUserByUsrName("shiro");
        String stationPorts = shiro.getStationPort();
        return dao.getCheLiuLiangEchartsList(stationPorts, null, 6);
    }

    @Override
    public List<CheLiuLiangEchartsList> getFirChaoZaiLv(String stationPorts, Integer link, Integer limit) {
        List<CheLiuLiangEchartsList> list = dao.getFirChaoZaiLv(stationPorts, link, limit);
        return list;
    }
    @DS("db2")
    @Override
    public List<CheLiuLiangEchartsList> getFirChaoZaiLv2(Integer link, Integer limit) {
        tUserInfoVo shiro = uDao.selectUserByUsrName("shiro");
        String stationPorts = shiro.getStationPort();
        List<CheLiuLiangEchartsList> list = dao.getFirChaoZaiLv(stationPorts, link, limit);
        return list;
    }
}
