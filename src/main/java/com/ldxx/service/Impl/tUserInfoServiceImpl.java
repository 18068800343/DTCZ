package com.ldxx.service.Impl;

import com.ldxx.bean.StationSite;
import com.ldxx.bean.tUserInfo;
import com.ldxx.dao.StationSiteDao;
import com.ldxx.dao.tUserInfoDao;
import com.ldxx.service.tUserInfoService;
import com.ldxx.vo.tUserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class tUserInfoServiceImpl implements tUserInfoService {

    @Autowired
    private tUserInfoDao dao;


    @Override
    public int addtUserInfo(tUserInfo tUserInfo) {
        return dao.addtUserInfo(tUserInfo);
    }

    @Override
    public List<tUserInfoVo> getAlltUserInfo() {
        List<tUserInfoVo> list = dao.getAlltUserInfo();
        return list;
    }

    @Override
    public int iscountName(String name) {
        return dao.iscountName(name);
    }

    @Override
    public int updatetUserInfo(tUserInfo tUserInfo) {
        return dao.updatetUserInfo(tUserInfo);
    }

    @Override
    public int deltUserInfo(String id) {
        return dao.deltUserInfo(id);
    }

    @Override
    public int iscountUNameEdit(String name, String id) {
        return dao.iscountUNameEdit(name,id);
    }

    @Override
    public tUserInfoVo selectUserByUsrName(String name) {
        return dao.selectUserByUsrName(name);
    }
}
