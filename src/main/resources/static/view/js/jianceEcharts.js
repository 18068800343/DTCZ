function setjiancezhandianLeft(list){

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
                //rotate:"45"  //文字倾斜
                formatter : function(params){ //文字换行
                    var newParamsName = "";// 最终拼接成的字符串
                    var paramsNameNumber = params.length;// 实际标签的个数
                    var provideNumber = 4;// 每行能显示的字的个数
                    var rowNumber = Math.ceil(paramsNameNumber / provideNumber);// 换行的话，需要显示几行，向上取整
                    /**
                     * 判断标签的个数是否大于规定的个数， 如果大于，则进行换行处理 如果不大于，即等于或小于，就返回原标签
                     */
                    // 条件等同于rowNumber>1
                    if (paramsNameNumber > provideNumber) {
                        /** 循环每一行,p表示行 */
                        for (var p = 0; p < rowNumber; p++) {
                            var tempStr = "";// 表示每一次截取的字符串
                            var start = p * provideNumber;// 开始截取的位置
                            var end = start + provideNumber;// 结束截取的位置
                            // 此处特殊处理最后一行的索引值
                            if (p == rowNumber - 1) {
                                // 最后一次不换行
                                tempStr = params.substring(start, paramsNameNumber);
                            } else {
                                // 每一次拼接字符串并换行
                                tempStr = params.substring(start, end) + "\n";
                            }
                            newParamsName += tempStr;// 最终拼成的字符串
                        }

                    } else {
                        // 将旧标签的值赋给新标签
                        newParamsName = params;
                    }
                    //将最终的字符串返回
                    return newParamsName
                }
            }
        },
        yAxis: {
            type: 'value',
            name: '单位(辆)',
            position: 'left',
            axisLine: {
                lineStyle: {
                    color: "#black"
                }
            },
            axisLabel: {
                formatter: '{value} '
            }
        },

        series: [{
            name: '车流量',
            type: 'bar',
            label: {
                normal: {
                    show: true,
                    position: 'top',
                    color: 'black',
                }
            },
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
                        //rotate:"45" //文字倾斜
                        formatter : function(params){ //文字换行
                            var newParamsName = "";// 最终拼接成的字符串
                            var paramsNameNumber = params.length;// 实际标签的个数
                            var provideNumber = 4;// 每行能显示的字的个数
                            var rowNumber = Math.ceil(paramsNameNumber / provideNumber);// 换行的话，需要显示几行，向上取整
                            /**
                             * 判断标签的个数是否大于规定的个数， 如果大于，则进行换行处理 如果不大于，即等于或小于，就返回原标签
                             */
                            // 条件等同于rowNumber>1
                            if (paramsNameNumber > provideNumber) {
                                /** 循环每一行,p表示行 */
                                for (var p = 0; p < rowNumber; p++) {
                                    var tempStr = "";// 表示每一次截取的字符串
                                    var start = p * provideNumber;// 开始截取的位置
                                    var end = start + provideNumber;// 结束截取的位置
                                    // 此处特殊处理最后一行的索引值
                                    if (p == rowNumber - 1) {
                                        // 最后一次不换行
                                        tempStr = params.substring(start, paramsNameNumber);
                                    } else {
                                        // 每一次拼接字符串并换行
                                        tempStr = params.substring(start, end) + "\n";
                                    }
                                    newParamsName += tempStr;// 最终拼成的字符串
                                }

                            } else {
                                // 将旧标签的值赋给新标签
                                newParamsName = params;
                            }
                            //将最终的字符串返回
                            return newParamsName
                        }
                    },
    	        }
    	    ],
    	    yAxis: [
    	        {
    	            type: 'value',
                    name: '单位(辆)',
                    position: 'left',
                    axisLine: {
                        lineStyle: {
                            color: "#black"
                        }
                    },
                    axisLabel: {
                        formatter: '{value} '
                    }
    	        }
    	    ],
    	    series: [
    	        {
    	            name: '超载车辆',
    	            type: 'line',
    	            smooth:true,
                    label: {
                        normal: {
                            show: true,
                            position: 'top',
                            color: 'black',
                        }
                    },
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
            data: ['一级预警(≥80吨)', '二级预警(≥100吨)'],
            //right: 10,
            //orient: 'vertical',  //垂直显示
            itemGap: 80,
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
                show:true,
                type: 'value',
                name: '单位(辆)',
                position: 'left',
                axisLine: {
                    lineStyle: {
                        color: "#black"
                    }
                },
                axisLabel: {
                    formatter: '{value} '
                }
            },

        ],
        series: [
            {
                name: '一级预警(≥80吨)',
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
                itemStyle:{
                    barBorderRadius:[50,50,0,0],//柱顶弧形
                }

            },
            {
                name: '二级预警(≥100吨)',
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
                itemStyle:{
                    barBorderRadius:[50,50,0,0],
                }
            }
        ]
    };
    return option;
}