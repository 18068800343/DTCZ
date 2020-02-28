let homePageInit = {};
homePageInit.initzhandian=()=>{
    $.ajax({
        type: 'POST',
        url: '/login/getUser',
        dataType: 'json',
        async:false,
        data: {
        },
        error: function (msg) {
        },
        success: function (json) {
            homePageInit.user=json;
            if(json!=null){
                homePageInit.stationPort=json.stationPort.split(",");
                homePageInit.stationName=json.stationName.split(",");
            }
        }
    });
}
homePageInit.initzhandian();
homePageInit.setLeftEcharts = (id,stationNames,nums) => {

    let myChart = echarts.init(document.getElementById(id));
    //初始化数据
    let category =  stationNames;
    let barData = nums;
    let dataShadow = [];
    for (let i = 0; i < barData.length; i++) {
        dataShadow.push(barData[i])
    }

    let option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        grid: {
            left: '20%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'value',
            axisLine: {
                show: true
            },
            axisTick: {
                show: false
            },
            axisLabel: {
                show: false
            },
            splitLine: {
                show: false
            }
        },
        yAxis: {
            type: 'category',
            data: category,
            splitLine: {
                show: false
            },
            axisLine: {
                show: false
            },
            axisTick: {
                show: false
            },
            axisLabel: {
                show: false,
            },
            offset: 10,
            nameTextStyle: {
                fontSize: 15
            },
            splitLine: {
                show: false
            },
            data: category
        },
        series: [{ // For shadow
            name: '数量',
            type: 'bar',
            label: {
                normal: {
                    show: true,
                    position:'right',
                    color:'white',
                }
            },
            itemStyle: {
                normal: {
                    barBorderRadius: 20,
                    color: 'rgb(43,82,226)'
                },
                emphasis: {
                    barBorderRadius: 20
                },

            },
            barGap: '-100%',
            barCategoryGap: '70%',
            data: dataShadow,

        },
            {
                type: 'bar',
                data: barData,
                smooth: true,
                label: {
                    normal: {
                        show: true,
                        position: 'left',
                        distance: 10,

                        textStyle: {
                            color: '#FFF',
                            fontSize: 12
                        },
                        formatter: function (param) {
                            for (let i = 0; i < category.length; i++) {
                                if (param.dataIndex == i) {
                                    return category[i];
                                }
                            }
                        }
                    }
                },
                itemStyle: {
                    emphasis: {
                        barBorderRadius: 40
                    },
                    normal: {
                        barBorderRadius: 20,
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgb(12,81,226)'
                        }, {
                            offset: 1,
                            color: '#3fa7dc'
                        }]),

                    }
                }
            }
        ]
    };
    myChart.setOption(option);
}


homePageInit.initLeftEcharts = (id)=>{
    $.ajax({
        type: 'POST',
        url: '/tWimMsg/getMeiRiCheLiuLiangByStationPort',
        dataType: 'json',
        data: {
            stationPort: homePageInit.stationPort.toString(),
        },
        error: function (msg) {
        },
        success: function (json) {
            homePageInit.setLeftEcharts(id,homePageInit.stationName,json.list);
        }
    });
}
//******************************************************************************环形图初始化***************************************************************************************

homePageInit.initHuanEcharts = (id)=>{
    // 环形图
    let myChart = echarts.init(document.getElementById(id));
    let option = {
        // 标题组件，包含主标题和副标题
        title: {
            text: 'G15-苏鲁省界',
            show: true,
            x: "center",
            y: "bottom",
            textStyle: {
                fontSize: "13",
                color: "#a3a6b4",
            }


        },
        color: ['#FF8F63', '#152364'],
        tooltip: {
            show: false,
            // 触发类型: item:数据项触发，axis：坐标轴触发
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        series: [{
            name: '任务进度',
            type: 'pie',
            // 饼图的半径，数组的第一项是内半径，第二项是外半径
            radius: ['50%', '70%'],
            // 是否启用防止标签重叠策略，默认开启
            avoidLabelOverlap: false,
            hoverAnimation: false,
            labelLine: {
                normal: {
                    show: false
                }
            },
            data: [{ // 数据值
                value: 20,
                selected: false,
                // 单个扇区的标签配置
                label: {
                    normal: {
                        // 是显示标签
                        show: true,
                        color: "#FFF",
                        position: 'center',
                        fontSize: 14,
                        // 标签内容格式器，支持字符串模板和回调函数两种形式，字符串模板与回调函数返回的字符串均支持用 \n 换行
                        formatter: '{b}\n{d}%',
                    }

                },

            },
                {
                    value: 100,
                    label: {
                        normal: {
                            show: false,

                        }
                    }

                },

            ]
        }]
    };
    myChart.setOption(option)
}
