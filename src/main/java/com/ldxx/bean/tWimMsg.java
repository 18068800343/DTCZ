package com.ldxx.bean;

import javax.xml.crypto.Data;
import java.math.BigInteger;
import java.util.Date;

/**
 * 2.2.	动态称重信息表
 */
public class tWimMsg {
    private String idLocal;
    private String id;
    //站点Id
    private String StationId;
    //设备信息ID
    private BigInteger EvtNo;
    //通行时间
    private Date EvtTime;
    //车道号
    private Integer LaneNo;
    //称重设备类型 0: 100+，1: WIM, 2: EMU , 3 ：TMU，5: WanJi
    private Integer DevType;
    //车型分类编码
    private Integer ClassIndex;
    //车型分类名称
    private String ClassName;
    //轴数
    private Integer AxlesCount;
    //总重量 kg
    private Integer TotalWeight;
    //轴1重 kg
    private Integer Axle1;
    private Integer Axle2;
    private Integer Axle3;
    private Integer Axle4;
    private Integer Axle5;
    private Integer Axle6;
    private Integer Axle7;
    private Integer Axle8;
    private Integer Axle9;
    private Integer Axle10;
    //轴距1 cm
    private Integer AxleSpace1;
    private Integer AxleSpace2;
    private Integer AxleSpace3;
    private Integer AxleSpace4;
    private Integer AxleSpace5;
    private Integer AxleSpace6;
    private Integer AxleSpace7;
    private Integer AxleSpace8;
    private Integer AxleSpace9;
    //行驶方向 0 正常行驶，1逆行
    private Integer Direction;
    //车速 Km/h
    private Integer Speed;
    //车长 cm
    private Integer Length;
    //跨道 0：否，1，是
    private Integer IsStraddle;
    //车头轴数
    private Integer AxlesHauler;
    //牵引杆挂车 牵引杆挂车，全挂车
    // 0：否,1 是，2 未知
    private Integer DrawBarTrailer;
    //车间距 cm
    private Integer Gap;
    //车间时距 单位：百毫秒
    private Integer TimeGap;
    //车头时距 单位：毫秒
    private Integer Headway;
    //路面温度 摄氏度(+100)
    private Integer Temperature;
    //加速度 万集(m/s)
    private Integer Acceleration;
    //超重数量 万集(m/s)
    private Integer OverWeight;
    //超重比率 百分之X
    private Integer OverWeightRatio;
    //收到信息时间
    private Date MsgTime;
    //测点IP地址
    private String StationIP;

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
        return StationId;
    }

    public void setStationId(String stationId) {
        StationId = stationId;
    }

    public BigInteger getEvtNo() {
        return EvtNo;
    }

    public void setEvtNo(BigInteger evtNo) {
        EvtNo = evtNo;
    }

    public Date getEvtTime() {
        return EvtTime;
    }

    public void setEvtTime(Date evtTime) {
        EvtTime = evtTime;
    }

    public Integer getLaneNo() {
        return LaneNo;
    }

    public void setLaneNo(Integer laneNo) {
        LaneNo = laneNo;
    }

    public Integer getDevType() {
        return DevType;
    }

    public void setDevType(Integer devType) {
        DevType = devType;
    }

    public Integer getClassIndex() {
        return ClassIndex;
    }

    public void setClassIndex(Integer classIndex) {
        ClassIndex = classIndex;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public Integer getAxlesCount() {
        return AxlesCount;
    }

    public void setAxlesCount(Integer axlesCount) {
        AxlesCount = axlesCount;
    }

    public Integer getTotalWeight() {
        return TotalWeight;
    }

    public void setTotalWeight(Integer totalWeight) {
        TotalWeight = totalWeight;
    }

    public Integer getAxle1() {
        return Axle1;
    }

    public void setAxle1(Integer axle1) {
        Axle1 = axle1;
    }

    public Integer getAxle2() {
        return Axle2;
    }

    public void setAxle2(Integer axle2) {
        Axle2 = axle2;
    }

    public Integer getAxle3() {
        return Axle3;
    }

    public void setAxle3(Integer axle3) {
        Axle3 = axle3;
    }

    public Integer getAxle4() {
        return Axle4;
    }

    public void setAxle4(Integer axle4) {
        Axle4 = axle4;
    }

    public Integer getAxle5() {
        return Axle5;
    }

    public void setAxle5(Integer axle5) {
        Axle5 = axle5;
    }

    public Integer getAxle6() {
        return Axle6;
    }

    public void setAxle6(Integer axle6) {
        Axle6 = axle6;
    }

    public Integer getAxle7() {
        return Axle7;
    }

    public void setAxle7(Integer axle7) {
        Axle7 = axle7;
    }

    public Integer getAxle8() {
        return Axle8;
    }

    public void setAxle8(Integer axle8) {
        Axle8 = axle8;
    }

    public Integer getAxle9() {
        return Axle9;
    }

    public void setAxle9(Integer axle9) {
        Axle9 = axle9;
    }

    public Integer getAxle10() {
        return Axle10;
    }

    public void setAxle10(Integer axle10) {
        Axle10 = axle10;
    }

    public Integer getAxleSpace1() {
        return AxleSpace1;
    }

    public void setAxleSpace1(Integer axleSpace1) {
        AxleSpace1 = axleSpace1;
    }

    public Integer getAxleSpace2() {
        return AxleSpace2;
    }

    public void setAxleSpace2(Integer axleSpace2) {
        AxleSpace2 = axleSpace2;
    }

    public Integer getAxleSpace3() {
        return AxleSpace3;
    }

    public void setAxleSpace3(Integer axleSpace3) {
        AxleSpace3 = axleSpace3;
    }

    public Integer getAxleSpace4() {
        return AxleSpace4;
    }

    public void setAxleSpace4(Integer axleSpace4) {
        AxleSpace4 = axleSpace4;
    }

    public Integer getAxleSpace5() {
        return AxleSpace5;
    }

    public void setAxleSpace5(Integer axleSpace5) {
        AxleSpace5 = axleSpace5;
    }

    public Integer getAxleSpace6() {
        return AxleSpace6;
    }

    public void setAxleSpace6(Integer axleSpace6) {
        AxleSpace6 = axleSpace6;
    }

    public Integer getAxleSpace7() {
        return AxleSpace7;
    }

    public void setAxleSpace7(Integer axleSpace7) {
        AxleSpace7 = axleSpace7;
    }

    public Integer getAxleSpace8() {
        return AxleSpace8;
    }

    public void setAxleSpace8(Integer axleSpace8) {
        AxleSpace8 = axleSpace8;
    }

    public Integer getAxleSpace9() {
        return AxleSpace9;
    }

    public void setAxleSpace9(Integer axleSpace9) {
        AxleSpace9 = axleSpace9;
    }

    public Integer getDirection() {
        return Direction;
    }

    public void setDirection(Integer direction) {
        Direction = direction;
    }

    public Integer getSpeed() {
        return Speed;
    }

    public void setSpeed(Integer speed) {
        Speed = speed;
    }

    public Integer getLength() {
        return Length;
    }

    public void setLength(Integer length) {
        Length = length;
    }

    public Integer getIsStraddle() {
        return IsStraddle;
    }

    public void setIsStraddle(Integer isStraddle) {
        IsStraddle = isStraddle;
    }

    public Integer getAxlesHauler() {
        return AxlesHauler;
    }

    public void setAxlesHauler(Integer axlesHauler) {
        AxlesHauler = axlesHauler;
    }

    public Integer getDrawBarTrailer() {
        return DrawBarTrailer;
    }

    public void setDrawBarTrailer(Integer drawBarTrailer) {
        DrawBarTrailer = drawBarTrailer;
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

    public Integer getTemperature() {
        return Temperature;
    }

    public void setTemperature(Integer temperature) {
        Temperature = temperature;
    }

    public Integer getAcceleration() {
        return Acceleration;
    }

    public void setAcceleration(Integer acceleration) {
        Acceleration = acceleration;
    }

    public Integer getOverWeight() {
        return OverWeight;
    }

    public void setOverWeight(Integer overWeight) {
        OverWeight = overWeight;
    }

    public Integer getOverWeightRatio() {
        return OverWeightRatio;
    }

    public void setOverWeightRatio(Integer overWeightRatio) {
        OverWeightRatio = overWeightRatio;
    }

    public Date getMsgTime() {
        return MsgTime;
    }

    public void setMsgTime(Date msgTime) {
        MsgTime = msgTime;
    }

    public String getStationIP() {
        return StationIP;
    }

    public void setStationIP(String stationIP) {
        StationIP = stationIP;
    }
}
