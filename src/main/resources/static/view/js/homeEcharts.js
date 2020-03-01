let homePageInit = {};
homePageInit.initzhandian = () => {
    $.ajax({
        type: 'POST',
        url: '/login/getUser',
        dataType: 'json',
        async: false,
        data: {},
        error: function (msg) {
        },
        success: function (json) {
            homePageInit.user = json;
            if (json != null) {
                homePageInit.stationPort = json.stationPort.split(",");
                homePageInit.stationName = json.stationName.split(",");
            }
        }
    });
}
homePageInit.initzhandian();
homePageInit.setLeftEcharts = (id, stationNames, nums) => {

    let myChart = echarts.init(document.getElementById(id));
    //初始化数据
    let category = stationNames;
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
                    position: 'right',
                    color: 'white',
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


homePageInit.initLeftEcharts = (id) => {
    $.ajax({
        type: 'POST',
        url: '/tWimMsg/getCheLiuLiangEchartsList',
        dataType: 'json',
        data: {
            stationPorts: homePageInit.stationPort.toString(),
        },
        error: function (msg) {
        },
        success: function (json) {
            homePageInit.stationName = json.stationNames.split(",");
            homePageInit.nums = json.nums.split(",");
            homePageInit.setLeftEcharts(id, homePageInit.stationName, homePageInit.nums);
        }
    });
}
//******************************************************************************环形图初始化***************************************************************************************

homePageInit.initDownEcharts = () => {
    $.ajax({
        type: 'POST',
        url: '/tWimMsg/getChaoZaiEchartsList',
        dataType: 'json',
        data: {
            stationPorts: homePageInit.stationPort.toString(),
        },
        error: function (msg) {
        },
        success: function (json) {
            homePageInit.downStationName = json.stationNames.split(",");
            homePageInit.downNums = json.nums.split(",");
            for(let i in json.nums){
                homePageInit.initHuanEcharts("huan"+i,i,homePageInit.downNums,homePageInit.downStationName[i]);
            }
        }
    });
}


homePageInit.initHuanEcharts = (id,index,nums,stationName) => {
    // 环形图
    let myChart = echarts.init(document.getElementById(id));
    let dataArray = getHuanDataByJson(index,nums);
    let colorArray = getColor(index,nums);
    let option = {
        // 标题组件，包含主标题和副标题
        title: {
            text: stationName,
            show: true,
            x: "center",
            y: "bottom",
            textStyle: {
                fontSize: "13",
                color: "#a3a6b4",
            }


        },
        color: colorArray,
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
            data: dataArray
        }]
    };
    myChart.setOption(option)
    $("#huan"+index).show();
}

let getHuanDataByJson = (index,nums)=>{
    let dataArray = [];


    for(let i in nums){
        let nowIndexData = { // 数据值
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
        };
        let otherIndexData = {
            label: {
                normal: {
                    show: false,
                }
            }
        };
       if(i==index){
           nowIndexData.value = nums[i];
           dataArray.push(nowIndexData);
       }else{
           otherIndexData.value = nums[i];
           dataArray.push(otherIndexData);
       }
    }
    return dataArray;
}

let getColor = (index,nums)=>{
    let colorArray = [];


    for(let i in nums){
        //['#FF8F63','#152364','#152364','#152364','#152364','#152364']
       if(i==index){
           colorArray.push('#FF8F63')
       }else{
           colorArray.push('#152364')
       }
    }
    return colorArray;
}
homePageInit.initHomeData = ()=>{
    $.ajax({
        type: 'POST',
        url: '/tWimMsg/getHomeDataObject',
        dataType: 'json',
        data: {
            stationPorts: homePageInit.stationPort.toString(),
        },
        error: function (msg) {
        },
        success: function (json) {
            $("#leftTable1").html('');
            $("#leftTable1").html(json.totalCheLiu);
            $("#leftTable2").html('');
            $("#leftTable2").html(json.totalChaoZai);
            $("#leftTable3").html('');
            $("#leftTable3").html(json.maxWeight);
            $("#leftTable4").html('');
            $("#leftTable4").html(json.stationNums);
        }
    });
}

