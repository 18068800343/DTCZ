package com.ldxx.service.Impl;

import com.ldxx.dao.tWimMsgDao;
import com.ldxx.service.tWimMsgService;
import com.ldxx.vo.tWimMsgVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class tWimMsgServiceImpl implements tWimMsgService {

    @Autowired
    private tWimMsgDao dao;

    @Override
    public List<tWimMsgVo> getAlltWimMsg(String stationPort) {
        return dao.getAlltWimMsg(stationPort);
    }
}
