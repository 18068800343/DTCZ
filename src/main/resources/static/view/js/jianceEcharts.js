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
            type: 'category',
            //max: max,
            data: stationName
        },
        yAxis: {
            type: 'value',
            boundaryGap: [0, 0.01]
        },

        series: [{
            name: '车流量',
            type: 'bar',
            label: seriesLabel,
            data: list,
            barWidth: 20,
            itemStyle: {
                normal: {
                    color: '#80B6FE'
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
    	    tooltip: {
    	        trigger: 'axis',
    	        axisPointer: {
    	            type: 'cross'
    	        }
    	    },
    	    toolbox: {
    	        feature: {
    	            saveAsImage: {}
    	        }
    	    },
    	    grid: {
    	        left: '3%',
    	        right: '4%',
    	        bottom: '3%',
    	        containLabel: true
    	    },
    	    xAxis: [
    	        {
    	            type: 'category',
    	            boundaryGap: false,
    	            data: ['G2京沪苏鲁界', 'G2京沪苏鲁界', 'G2京沪苏鲁界', 'G2京沪苏鲁界', 'G2京沪苏鲁界', 'G2京沪苏鲁界', 'G2京沪苏鲁界', 'G2京沪苏鲁界', 'G2京沪苏鲁界', 'G2京沪苏鲁界', 'G2京沪苏鲁界', 'G2京沪苏鲁界']
    	        }
    	    ],
    	    yAxis: [
    	        {
    	            type: 'value'
    	        }
    	    ],
    	    series: [
    	        {
    	            name: '邮件营销',
    	            type: 'line',
    	            smooth:true,
    	            stack: '总量',
    	            color:'#BCD8FE',
    	            areaStyle: {},
    	            data: [2, 1, 3, 2, 1, 6, 4,2, 1, 6, 4,1, 6, 4]
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