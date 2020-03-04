package com.ldxx.dao;

import com.ldxx.bean.OverloadStandard;

import java.util.List;

public interface OverloadStandardDao {

    List<OverloadStandard> getAllOverloadStandard();

    int addOverloadStandard(OverloadStandard OverloadStandard);

    int updOverloadStandard(OverloadStandard OverloadStandard);

    int delOverloadStandard(String id);

    OverloadStandard getOverloadStandardByNumber(Integer axleNumber);

    int countaxleNumber(Integer axleNumber);

    int xgcountaxleNumber(Integer axleNumber,String id);
}
