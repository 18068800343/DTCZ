function setjiancezhandianLeft(list){
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
            data: list.stationNames.split(","),
            axisLabel : {//坐标轴刻度标签的相关设置。
                interval:0,
                rotate:"45"
            }
        },
        yAxis: {
            type: 'value',
            boundaryGap: [0, 0.01]
        },

        series: [{
            name: '车流量',
            type: 'bar',
            label: seriesLabel,
            data: list.nums.split(","),
            barWidth: 40,
            itemStyle: {
                normal: {
                    color: '#80B6FE'
                }
            },

        }, ]
    };
    return option;
}

function setjiancezhandianRight(list){
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
    	            //saveAsImage: {}
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
    	            data: list.stationNames.split(","),
                    axisLabel : {//坐标轴刻度标签的相关设置。
                        interval:0,
                        rotate:"45"
                    }
    	        }
    	    ],
    	    yAxis: [
    	        {
    	            type: 'value'
    	        }
    	    ],
    	    series: [
    	        {
    	            name: '超载车辆',
    	            type: 'line',
    	            smooth:true,
    	            stack: '总量',
    	            color:'#BCD8FE',
    	            areaStyle: {},
    	            data: list.nums.split(",")
    	        }
    	    ]
    	};
    return option;
}

function setjiancezhandianBottom(list){

    var option = {
        color: ['#6154FD','#FE545E'],
        textStyle: {
            color: '#black',
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
                data: list.stationNames.split(","),
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
                data: list.cnt2.split(","),
                barWidth : 20,//柱图宽度

            },
            {
                name: '三级预警(≥100吨)',
                type: 'bar',
                label: {
                    normal: {
                        show: true,
                        position: 'top',
                        color: 'black',
                    }
                },
                data: list.cnt3.split(","),
                barWidth : 20,//柱图宽度
            }
        ]
    };
    return option;
}