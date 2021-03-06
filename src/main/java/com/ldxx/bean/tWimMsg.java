package com.ldxx.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * 2.2.	动态称重信息表
 */
public class tWimMsg implements Serializable {
    private String idLocal;
    private String id;
    //站点Id
    private String stationId;

    //通行时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date evtTime;
    //打包时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date msgTime;
    //车道号
    private Integer laneNo;
    //车牌号
    private String plate;
    //车牌颜色
    private Integer plateColor;
    //车型分类编码
    private Integer classIndex;
    //车长 cm
    private Integer length;
    //车速 Km/h
    private Integer speed;
    //行驶方向 0 正常行驶，1逆行
    private Integer direction;
    //轴数
    private Integer axlesCount;
    //总重量 kg
    private Integer totalWeight;
    //轴1重 kg
    private Integer axle1;
    private Integer axle2;
    private Integer axle3;
    private Integer axle4;
    private Integer axle5;
    private Integer axle6;
    private Integer axle7;
    private Integer axle8;
    private Integer axle9;
    private Integer axle10;
    //轴间距1 cm
    private Integer axleSpace1;
    private Integer axleSpace2;
    private Integer axleSpace3;
    private Integer axleSpace4;
    private Integer axleSpace5;
    private Integer axleSpace6;
    private Integer axleSpace7;
    private Integer axleSpace8;
    private Integer axleSpace9;

    //跨道 0：否，1，是
    private Integer isStraddle;
    //路面温度 摄氏度(+100)
    private Integer temperature;
    //超重量 Kg，0 为不超重
    private Integer overWeight;
    //超重比率 百分之X
    private Double overWeightRatio;
    //称重数据ID
    private String wIMId;
    //抓拍数据ID
    private String lPRId;
    //有效性号
    private Integer ValidityCode;
    //总轴距 cm
    private Integer Wheelbase;
    //车间距，单位：50cm
    private Integer Gap;
    //车时间距，单位：毫秒
    private Integer TimeGap;
    //车头时距，单位：100毫秒
    private Integer Headway;
    //测点IP地址
    private String stationIP;

    private String imgData;//图片流
    private Integer maxWeight;//最大允许总质量 单位: 千克 数字类型,


    public Integer getValidityCode() {
        return ValidityCode;
    }

    public void setValidityCode(Integer validityCode) {
        ValidityCode = validityCode;
    }

    public Integer getWheelbase() {
        return Wheelbase;
    }

    public void setWheelbase(Integer wheelbase) {
        Wheelbase = wheelbase;
    }

    public Integer getGap() {
        return Gap;
    }

    public void setGap(Integer gap) {
        Gap = gap;
    }

    public Integer getTimeGap() {
        return TimeGap;
    }

    public void setTimeGap(Integer timeGap) {
        TimeGap = timeGap;
    }

    public Integer getHeadway() {
        return Headway;
    }

    public void setHeadway(Integer headway) {
        Headway = headway;
    }

    public Integer getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(Integer maxWeight) {
        this.maxWeight = maxWeight;
    }

    public String getImgData() {
        return imgData;
    }

    public void setImgData(String imgData) {
        this.imgData = imgData;
    }

    public String getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(String idLocal) {
        this.idLocal = idLocal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public Date getEvtTime() {
        return evtTime;
    }

    public void setEvtTime(Date evtTime) {
        this.evtTime = evtTime;
    }

    public Date getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(Date msgTime) {
        this.msgTime = msgTime;
    }

    public Integer getLaneNo() {
        return laneNo;
    }

    public void setLaneNo(Integer laneNo) {
        this.laneNo = laneNo;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Integer getPlateColor() {
        return plateColor;
    }

    public void setPlateColor(Integer plateColor) {
        this.plateColor = plateColor;
    }

    public Integer getClassIndex() {
        return classIndex;
    }

    public void setClassIndex(Integer classIndex) {
        this.classIndex = classIndex;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public Integer getAxlesCount() {
        return axlesCount;
    }

    public void setAxlesCount(Integer axlesCount) {
        this.axlesCount = axlesCount;
    }

    public Integer getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Integer totalWeight) {
        this.totalWeight = totalWeight;
    }

    public Integer getAxle1() {
        return axle1;
    }

    public void setAxle1(Integer axle1) {
        this.axle1 = axle1;
    }

    public Integer getAxle2() {
        return axle2;
    }

    public void setAxle2(Integer axle2) {
        this.axle2 = axle2;
    }

    public Integer getAxle3() {
        return axle3;
    }

    public void setAxle3(Integer axle3) {
        this.axle3 = axle3;
    }

    public Integer getAxle4() {
        return axle4;
    }

    public void setAxle4(Integer axle4) {
        this.axle4 = axle4;
    }

    public Integer getAxle5() {
        return axle5;
    }

    public void setAxle5(Integer axle5) {
        this.axle5 = axle5;
    }

    public Integer getAxle6() {
        return axle6;
    }

    public void setAxle6(Integer axle6) {
        this.axle6 = axle6;
    }

    public Integer getAxle7() {
        return axle7;
    }

    public void setAxle7(Integer axle7) {
        this.axle7 = axle7;
    }

    public Integer getAxle8() {
        return axle8;
    }

    public void setAxle8(Integer axle8) {
        this.axle8 = axle8;
    }

    public Integer getAxle9() {
        return axle9;
    }

    public void setAxle9(Integer axle9) {
        this.axle9 = axle9;
    }

    public Integer getAxle10() {
        return axle10;
    }

    public void setAxle10(Integer axle10) {
        this.axle10 = axle10;
    }

    public Integer getAxleSpace1() {
        return axleSpace1;
    }

    public void setAxleSpace1(Integer axleSpace1) {
        this.axleSpace1 = axleSpace1;
    }

    public Integer getAxleSpace2() {
        return axleSpace2;
    }

    public void setAxleSpace2(Integer axleSpace2) {
        this.axleSpace2 = axleSpace2;
    }

    public Integer getAxleSpace3() {
        return axleSpace3;
    }

    public void setAxleSpace3(Integer axleSpace3) {
        this.axleSpace3 = axleSpace3;
    }

    public Integer getAxleSpace4() {
        return axleSpace4;
    }

    public void setAxleSpace4(Integer axleSpace4) {
        this.axleSpace4 = axleSpace4;
    }

    public Integer getAxleSpace5() {
        return axleSpace5;
    }

    public void setAxleSpace5(Integer axleSpace5) {
        this.axleSpace5 = axleSpace5;
    }

    public Integer getAxleSpace6() {
        return axleSpace6;
    }

    public void setAxleSpace6(Integer axleSpace6) {
        this.axleSpace6 = axleSpace6;
    }

    public Integer getAxleSpace7() {
        return axleSpace7;
    }

    public void setAxleSpace7(Integer axleSpace7) {
        this.axleSpace7 = axleSpace7;
    }

    public Integer getAxleSpace8() {
        return axleSpace8;
    }

    public void setAxleSpace8(Integer axleSpace8) {
        this.axleSpace8 = axleSpace8;
    }

    public Integer getAxleSpace9() {
        return axleSpace9;
    }

    public void setAxleSpace9(Integer axleSpace9) {
        this.axleSpace9 = axleSpace9;
    }

    public Integer getIsStraddle() {
        return isStraddle;
    }

    public void setIsStraddle(Integer isStraddle) {
        this.isStraddle = isStraddle;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getOverWeight() {
        return overWeight;
    }

    public void setOverWeight(Integer overWeight) {
        this.overWeight = overWeight;
    }

    public Double getOverWeightRatio() {
        return overWeightRatio;
    }

    public void setOverWeightRatio(Double overWeightRatio) {
        this.overWeightRatio = overWeightRatio;
    }

    public String getwIMId() {
        return wIMId;
    }

    public void setwIMId(String wIMId) {
        this.wIMId = wIMId;
    }

    public String getlPRId() {
        return lPRId;
    }
    public void setlPRId(String lPRId) {
        this.lPRId = lPRId;
    }

    public String getStationIP() {
        return stationIP;
    }

    public void setStationIP(String stationIP) {
        this.stationIP = stationIP;
    }
}
