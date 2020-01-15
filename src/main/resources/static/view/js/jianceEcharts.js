function setjiancezhandianLeft(list,stationName){
    var seriesLabel = {
        normal: {
            show: true,
            textBorderColor: '#fff',
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
                    formatter: '{d}%',
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

function setjiancezhandianBottom(list,stationName){
    var seriesLabel = {
        normal: {
            show: true,
            textBorderColor: '#fff',
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
                type : 'value'
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
    };
    return option;
}