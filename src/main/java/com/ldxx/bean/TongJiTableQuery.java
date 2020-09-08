package com.ldxx.bean;

import java.io.Serializable;

public class TongJiTableQuery implements Serializable {
    String stationPort;
    String startTime;
    String endTime;
    Double startWeight;
    Double endWeight;
    String chexing;
    String chepai;
    Double chesuStart;
    Double chesuEnd;
    Double chedao;
    Double cheChangStart;
    Double cheChangEnd;
    Double zhoushu;
    Double roadTmpStart;
    Double roadTmpEnd;
    Double chaozhongStart;
    Double chaozhongEnd;
    Integer start;
    Integer length;
    String columnName;

    public Double getChedao() {
        return chedao;
    }

    public void setChedao(Double chedao) {
        this.chedao = chedao;
    }

    public Double getCheChangStart() {
        return cheChangStart;
    }

    public void setCheChangStart(Double cheChangStart) {
        this.cheChangStart = cheChangStart;
    }

    public Double getCheChangEnd() {
        return cheChangEnd;
    }

    public void setCheChangEnd(Double cheChangEnd) {
        this.cheChangEnd = cheChangEnd;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getChepai() {
        return chepai;
    }

    public void setChepai(String chepai) {
        this.chepai = chepai;
    }

    public Double getChesuStart() {
        return chesuStart;
    }

    public void setChesuStart(Double chesuStart) {
        this.chesuStart = chesuStart;
    }

    public Double getChesuEnd() {
        return chesuEnd;
    }

    public void setChesuEnd(Double chesuEnd) {
        this.chesuEnd = chesuEnd;
    }

    public Double getZhoushu() {
        return zhoushu;
    }

    public void setZhoushu(Double zhoushu) {
        this.zhoushu = zhoushu;
    }

    public Double getRoadTmpStart() {
        return roadTmpStart;
    }

    public void setRoadTmpStart(Double roadTmpStart) {
        this.roadTmpStart = roadTmpStart;
    }

    public Double getRoadTmpEnd() {
        return roadTmpEnd;
    }

    public void setRoadTmpEnd(Double roadTmpEnd) {
        this.roadTmpEnd = roadTmpEnd;
    }

    public Double getChaozhongStart() {
        return chaozhongStart;
    }

    public void setChaozhongStart(Double chaozhongStart) {
        this.chaozhongStart = chaozhongStart;
    }

    public Double getChaozhongEnd() {
        return chaozhongEnd;
    }

    public void setChaozhongEnd(Double chaozhongEnd) {
        this.chaozhongEnd = chaozhongEnd;
    }

    public String getStationPort() {
        return stationPort;
    }

    public void setStationPort(String stationPort) {
        this.stationPort = stationPort;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Double getStartWeight() {
        return startWeight;
    }

    public void setStartWeight(Double startWeight) {
        this.startWeight = startWeight;
    }

    public Double getEndWeight() {
        return endWeight;
    }

    public void setEndWeight(Double endWeight) {
        this.endWeight = endWeight;
    }

    public String getChexing() {
        return chexing;
    }

    public void setChexing(String chexing) {
        this.chexing = chexing;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
