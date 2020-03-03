function setjiancezhandianLeft(list,stationName,max){
    var seriesLabel = {
        normal: {
            show: true,
            textBorderColor: '#336666',
            color:'black',
            textBorderWidth: 1
        }
    }
    var option = {
        title: {
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },

        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'value',
            //max: max,
            boundaryGap: [0, 0.01]
        },
        yAxis: {
            type: 'category',
            data: stationName
        },

        series: [{
            name: '车流量',
            type: 'bar',
            label: seriesLabel,
            data: list,
            barWidth: 20,
            itemStyle: {
                normal: {
                    color: '#91FD63'
                }
            },

        }, ]
    };
    return option;
}

function setjiancezhandianRight(list,stationName){
    var seriesLabel = {
        normal: {
            show: true,
            textBorderColor: '#333',
            textBorderWidth: 2
        }
    }
    var option = {
        title: {
            x: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{b} <br/>{a} : {c} ({d}%)"
        },
        /*legend: {
            orient: 'vertical',
            right: 'right',
            data: stationName,
            color: '#FFFFFF',

        },*/
        series: [{
            name: '超载车辆',
            type: 'pie',
            radius: '55%',
            center: ['50%', '60%'],
            color: ['#06d3c4', '#ffbb32', '#2ccc44', "#0278fe", "#FF3976", "#6058E1", "#e569ff", "#00d6fb"],
            data:list,
            label: {
                formatter: '{b}: {@2012} ({d}%)'
            },
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        },{
                name: '超载车辆',
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                color: ['#06d3c4', '#ffbb32', '#2ccc44', "#0278fe", "#FF3976", "#6058E1", "#e569ff", "#00d6fb"],
                data:list,
                label: {
                    position: 'inner',
                    color:'black',
                    formatter: function (params) {
                        if(params.data.num==0){
                            return '';
                        }else{
                            return params.percent+"%";
                        }
                    },
                },
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
            ]
    };
    return option;
}

function setjiancezhandianBottom(json){
    /*var seriesLabel = {
        normal: {
            show: true,
            textBorderColor: '#336666',
            color:'black',
            textBorderWidth: 1
        }
    }
    var option = {
        color: ['#29DC93'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                triggerEvent: true,
                type : 'category',
                data :stationName,
                axisTick: {
                    alignWithLabel: true
                },
                //文字过长隐藏剩余部分
                axisLabel: {
                    formatter: function(value) {
                        var res = value;
                        if(res.length > 6) {
                            res = res.substring(0, 5) + "..";
                        }
                        return res;
                    }
                }

            }
        ],
        yAxis : [
            {
                type : 'value',
                //max: max
            }
        ],
        series : [
            {
                name:'关键车辆超重统计',
                type:'bar',
                label: seriesLabel,
                barWidth: '20%',
                data:list
            }
        ]
    };*/
    var option = {
        color: ['#6154FD','#FE545E'],

        textStyle: {
            color: '#fff',
            fontSize: 13
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            }
        },

        legend: {
            data: ['二级预警(≥80吨)', '三级预警(≥100吨)'],
            right: 10,
            orient: 'vertical',  //垂直显示
            textStyle: {
                color: '#17447E',
                fontSize: 13
            },

        },
        xAxis: [
            {
                type: 'category',
                data: json.stationNames.split(","),
                axisLabel : {
                    formatter : function(params){
                        var newParamsName = "";
                        var paramsNameNumber = params.length;
                        var provideNumber = 3;
                        var rowNumber = Math.ceil(paramsNameNumber / provideNumber);
                        if (paramsNameNumber > provideNumber) {
                            for (var p = 0; p < rowNumber; p++) {
                                var tempStr = "";
                                var start = p * provideNumber;
                                var end = start + provideNumber;
                                if (p == rowNumber - 1) {
                                    tempStr = params.substring(start, paramsNameNumber);
                                } else {
                                    tempStr = params.substring(start, end) + "\n";
                                }
                                newParamsName += tempStr;
                            }

                        } else {
                            newParamsName = params;
                        }
                        return newParamsName
                    }

                },
            }
        ],
        yAxis: [
            {
                show:false,
                type: 'value',
            },

        ],
        series: [
            {
                name: '二级预警(≥80吨)',
                type: 'bar',
                label: {
                    normal: {
                        show: true,
                        position: 'top',
                        color: 'black',
                    }
                },
                data: json.cnt2.split(","),
                barWidth : 20,//柱图宽度

            },
            {
                name: '三级预警(≥100吨)',
                type: 'bar',
                label: {
                    normal: {
                        show: true,
                        position: 'top',
                        color: 'white',
                    }
                },
                data: json.cnt3.split(","),
                barWidth : 20,//柱图宽度
            }
        ]
    };
    return option;
}