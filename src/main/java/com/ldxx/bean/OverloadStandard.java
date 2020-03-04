package com.ldxx.bean;

/**
 * 超载标准
 */
public class OverloadStandard {
    private String id;
    private Integer axleNumber;//轴车
    private String overloadStandard;//超载标准（kg）

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAxleNumber() {
        return axleNumber;
    }

    public void setAxleNumber(Integer axleNumber) {
        this.axleNumber = axleNumber;
    }

    public String getOverloadStandard() {
        return overloadStandard;
    }

    public void setOverloadStandard(String overloadStandard) {
        this.overloadStandard = overloadStandard;
    }
}
