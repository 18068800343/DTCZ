package com.ldxx.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class DanZhouChaoZai implements Serializable {

    private String evtTime;

    //车道号
    private Integer maxAxle;

    public String getEvtTime() {
        return evtTime;
    }

    public void setEvtTime(String evtTime) {
        this.evtTime = evtTime;
    }

    public Integer getMaxAxle() {
        return maxAxle;
    }

    public void setMaxAxle(Integer maxAxle) {
        this.maxAxle = maxAxle;
    }
}
