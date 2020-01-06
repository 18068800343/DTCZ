package com.ldxx.service;

import com.ldxx.bean.tUserInfo;
import com.ldxx.vo.tUserInfoVo;

import java.util.List;

public interface tUserInfoService {

    int addtUserInfo(tUserInfo tUserInfo);

    List<tUserInfoVo> getAlltUserInfo();

    int iscountName(String name);

    int updatetUserInfo(tUserInfo tUserInfo);

    int deltUserInfo(String id);

    int iscountUNameEdit(String name, String id);
}
