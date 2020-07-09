package com.ldxx.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class DanZhouChaoZai {
    //打包时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date Evt_Time;
    //车道号
    private Integer maxAxle;

    //打包时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getEvt_Time() {
        return Evt_Time;
    }

    //打包时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setEvt_Time(Date evt_Time) {
        Evt_Time = evt_Time;
    }

    public Integer getMaxAxle() {
        return maxAxle;
    }

    public void setMaxAxle(Integer maxAxle) {
        this.maxAxle = maxAxle;
    }
}
