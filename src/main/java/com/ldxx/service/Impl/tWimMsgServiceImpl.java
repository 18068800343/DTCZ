package com.ldxx.service.Impl;

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
    public List<tWimMsgVo> getAlltWimMsgByCondition(String stationPort, String startTime, String endTime) {

        return dao.getAlltWimMsgByCondition(stationPort, startTime,endTime);
    }
}
