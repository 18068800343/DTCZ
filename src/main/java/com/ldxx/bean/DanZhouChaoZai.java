package com.ldxx.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class DanZhouChaoZai implements Serializable {
    //打包时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date evtTime;
    //车道号
    private Integer maxAxle;

    public Date getEvtTime() {
        return evtTime;
    }

    public void setEvtTime(Date evtTime) {
        this.evtTime = evtTime;
    }

    public Integer getMaxAxle() {
        return maxAxle;
    }

    public void setMaxAxle(Integer maxAxle) {
        this.maxAxle = maxAxle;
    }
}
