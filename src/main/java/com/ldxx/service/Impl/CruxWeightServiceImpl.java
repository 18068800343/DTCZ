package com.ldxx.service.Impl;

import com.ldxx.bean.CruxWeight;
import com.ldxx.dao.CruxWeightDao;
import com.ldxx.service.CruxWeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CruxWeightServiceImpl implements CruxWeightService {


    @Autowired
    private CruxWeightDao dao;

    @Override
    public List<CruxWeight> getCruxWeight() {
        return dao.getCruxWeight();
    }

    @Override
    public int addCruxWeight(CruxWeight CruxWeight) {
        return dao.addCruxWeight(CruxWeight);
    }

    @Override
    public int updCruxWeight(CruxWeight CruxWeight) {
        return dao.updCruxWeight(CruxWeight);
    }

    @Override
    public int delCruxWeight(String id) {
        return dao.delCruxWeight(id);
    }
}
