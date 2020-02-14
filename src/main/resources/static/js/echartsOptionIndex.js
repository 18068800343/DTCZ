function initLeftOption(list, stationname,max) {
    let arrList = [];
    arrList.push(['score', 'amount', 'product']);
    for(let i in list){
      let arrayItem = [];
      arrayItem[0]=list[i];
      arrayItem[1]=list[i];
      arrayItem[2]=stationname[i];
      arrList.push(arrayItem);
    }

    let seriesLabel = {
        normal: {
            show: true,
            textBorderColor: '#fff',
            textBorderWidth: 0,

        },

    }

    let optionLeft = {
        tooltip: {
            trigger: 'item',
        },
        title: {
            x: 'center'
        },
        dataset: {
            source: arrList,
        },
        grid: {
            containLabel: true
        },
        xAxis: {

            triggerEvent: true,
            name: 'amount',
            axisLabel: {
                show: true,
                textStyle: {
                    color: '#fff'
                },
                formatter: function(value) {
                    var res = value;
                    if(res.length > 6) {
                        res = res.substring(0, 5) + "..";
                    }
                    return res;
                }
            },

        },
        yAxis: {
            triggerEvent: true,
            type: 'category',
            axisTick: {
                alignWithLabel: true
            },
            axisLabel: {
                show: true,
                textStyle: {
                    color: '#fff'
                },
                formatter: function(value) {
                    var res = value;
                    if(res.length > 6) {
                        res = res.substring(0, 5) + "..";
                    }
                    return res;
                }
            },


        },
        series: [{
            type: 'bar',
            label: seriesLabel,
            encode: {
                // Map the "amount" column to X axis.
                x: 'amount',
                // Map the "product" column to Y axis
                y: 'product'
            }
        }]
    };
    return optionLeft;
}


function initRightOption(list,stationName) {
    let optionRight = {
        title: {
            x: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
/*        legend: {
            orient: 'vertical',
            right: 'right',
            data: stationName,
            color: '#FFFFFF',
        },*/
        series: [{
            name: '访问来源',
            type: 'pie',
            radius: '55%',
            center: ['50%', '60%'],
            color: ['#06d3c4', '#ffbb32', '#2ccc44', "#0278fe", "#FF3976", "#6058E1", "#e569ff", "#00d6fb"],
            data: list,
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        },{
            name: '访问来源',
            type: 'pie',
            radius: '55%',
            center: ['50%', '60%'],
            color: ['#06d3c4', '#ffbb32', '#2ccc44', "#0278fe", "#FF3976", "#6058E1", "#e569ff", "#00d6fb"],
            data: list,
            label: {
                position: 'inner',
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
        }]
    };
    return optionRight;
}


function initDownOption(list,stationName,max){
    var dataAxis = stationName;
    var data = list;
    var yMax = 500;
    var dataShadow = [];
    for (var i = 0; i < data.length; i++) {
        dataShadow.push(data[i]);
    }
    let seriesLabel = {
        normal: {
            show: true,
            textBorderColor: '#fff',
            textBorderWidth: 0,

        },

    }

    var optionBottom = {
        tooltip: {
            trigger: 'item',
        },
        title: {
            x: 'center'
        },
        xAxis: {
            data: dataAxis,
            splitLine: {
                show: false
            },
            axisLabel: {
                /*inside: true,*/
                textStyle: {
                    color: '#fff'
                },
                formatter: function(value) {
                    var res = value;
                    if(res.length > 6) {
                        res = res.substring(0, 5) + "..";
                    }
                    return res;
                }
            },
            axisTick: {
                show: true
            },
            axisLine: {
                show: true
            },
            z: 10
        },
        yAxis: {
            axisTick: {
                show: true
            },
            axisLabel: {
                textStyle: {
                    color: '#999'
                }
            },
        },
        dataZoom: [{
            type: 'inside'
        }],
        series: [{ // For shadow
            type: 'bar',
            itemStyle: {
                normal: {
                    color: 'rgba(0,0,0,0.05)'
                }
            },
           /* label: seriesLabel,*/
            barGap: '-100%',
            barCategoryGap: '40%',
            data: dataShadow,
            animation: false
        },
            {
                type: 'bar',
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1, [{
                                offset: 0,
                                color: '#83bff6'
                            },
                                {
                                    offset: 0.5,
                                    color: '#188df0'
                                },
                                {
                                    offset: 1,
                                    color: '#188df0'
                                }
                            ]
                        )
                    },
                    emphasis: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1, [{
                                offset: 0,
                                color: '#2378f7'
                            },
                                {
                                    offset: 0.7,
                                    color: '#2378f7'
                                },
                                {
                                    offset: 1,
                                    color: '#83bff6'
                                }
                            ]
                        )
                    }
                },
                data: data
            }
        ]
    };
   return optionBottom;
}