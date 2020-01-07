package com.ldxx.dao;

import com.ldxx.bean.CruxWeight;

import java.util.List;

public interface CruxWeightDao {

    List<CruxWeight> getCruxWeight();

    int addCruxWeight(CruxWeight CruxWeight);

    int updCruxWeight(CruxWeight CruxWeight);

    int delCruxWeight(String id);
}
