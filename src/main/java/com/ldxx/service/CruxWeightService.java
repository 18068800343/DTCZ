package com.ldxx.service;

import com.ldxx.bean.CruxWeight;

import java.util.List;

public interface CruxWeightService {

    List<CruxWeight> getCruxWeight();

    int addCruxWeight(CruxWeight CruxWeight);

    int updCruxWeight(CruxWeight CruxWeight);

    int delCruxWeight(String id);
}
