package com.ldxx.bean;

/**
 * 关键重量字典
 */
public class CruxWeight {
    private String cruxWeightId;
    //关键重量
    private String cruxWeight;
    //1:存在；0：已删除
    private Integer delState;

    private  Integer lv;

    public Integer getLv() {
        return lv;
    }

    public void setLv(Integer lv) {
        this.lv = lv;
    }

    public Integer getDelState() {
        return delState;
    }

    public void setDelState(Integer delState) {
        this.delState = delState;
    }

    public String getCruxWeightId() {
        return cruxWeightId;
    }

    public void setCruxWeightId(String cruxWeightId) {
        this.cruxWeightId = cruxWeightId;
    }

    public String getCruxWeight() {
        return cruxWeight;
    }

    public void setCruxWeight(String cruxWeight) {
        this.cruxWeight = cruxWeight;
    }
}
