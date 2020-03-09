let homePageInit = {};

let bindChartClick = (e, d) => {
    e.off('click');
    e.on('click', function (params) {
        console.log(params);
        if(d==4){
        initLeftChartDetail4(d);
        }else{
        initLeftChartDetail(d);
        }
    });
}



let initHomeMap = (lngLats,stationNames)=>{
    let geoCoordData = {};
    let markPointData = [];
    for(let i in lngLats){
        let lngLatArray = [];
        let markPointItem = {};
        markPointItem.name = i+"";
        markPointItem.value = stationNames[i];
        markPointData.push(markPointItem);
        lngLatArray.push(lngLats[i].split("-")[0]);
        lngLatArray.push(lngLats[i].split("-")[1]);
        geoCoordData[i+""] =lngLatArray;
    }

    require.config({
        paths: {
            echarts: 'js/build/dist'
        }
    });
    require(
        [
            'echarts',
            'echarts/chart/map'
        ],
        function(ec) {
            require('echarts/util/mapData/params').params.jiangsu = {

                getGeoJson: function(callback) {
                    $.getJSON('geoJson/jiangsu.json', function(data) {
                        // 压缩后的地图数据必须使用 decode 函数转换
                        callback(require('echarts/util/mapData/params').decode(data));
                    });
                }
            }
            let myChart = ec.init(document.getElementById('main'));
            let ecConfig = require('echarts/config');
            let zrEvent = require('zrender/tool/event');
            let curIndx = 0;
            let mapType = ["江苏"];

            myChart.on(ecConfig.EVENT.MAP_SELECTED, function(param) {});
            option = {

                tooltip: {
                    trigger: 'item',
                    //formatter:'dede{b}'
                    formatter: '{b}',
                    formatter: function (params,ticket,callback){
                        let $pna = params.name;
                        let res = '';
                        console.log(params,ticket)
                        return stationNames[$pna];
                    }
                },
                legend: {
                    orient: 'vertical',
                    x: '',
                    data: [' ']
                },

                series: [{
                    name: '',
                    type: 'map',
                    symbolSize: 10,
                    symbolRotate: 35,
                    mapType: 'jiangsu',
                    selectedMode: false,
                    itemStyle: {
                        normal: {
                            label: {
                                show: true
                            },
                            borderColor: 'rgb(71,160,192)',
                            borderWidth: 1
                        },
                        emphasis: {
                            label: {
                                show: false
                            }
                        }
                    },
                    data: markPointData,
                    geoCoord: geoCoordData,
                  
                    markPoint: {
                        symbol: 'emptyCircle',
                        symbolSize: 15,
                        itemStyle: {
                            normal: {
                                borderColor: '#f317eb',
                                borderWidth: 1, // 标注边线线宽，单位px，默认为1
                                label: {
                                    show: true
                                }
                            }
                        },
                        effect: {
                            show: true,
                            shadowBlur: 0,
                            loop: true
                        },
                        data : markPointData
                    }

                }]
            },




            myChart.setOption(option);

        }
    );
}

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
                initHomeMap(json.lnglat.split(","),homePageInit.stationName);
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
    bindChartClick(myChart,1);
}



homePageInit.initLeftEcharts = (id) => {
    $.ajax({
        type: 'POST',
        url: '/tWimMsg/getCheLiuLiangEchartsList',
        dataType: 'json',
        data: {
            stationPorts: homePageInit.stationPort.toString(),
            limit:6
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
            limit:6
        },
        error: function (msg) {
        },
        success: function (json) {
            homePageInit.downStationName = json.stationNames.split(",");
            homePageInit.downNums = json.nums.split(",");
            homePageInit.downNums2 = json.nums2.split(",");
            for(let i in json.nums){
                homePageInit.initHuanEcharts("huan"+i,i,homePageInit.downNums,homePageInit.downStationName[i], homePageInit.downNums2 );
                homePageInit.initHuanEcharts2("by"+i,i,homePageInit.downNums[i],homePageInit.downStationName[i]);
            }
        }
    });
}



homePageInit.initHuanEcharts = (id,index,nums,stationName,nums2) => {
    // 环形图
    let myChart = echarts.init(document.getElementById(id));
    let dataArray = getHuanDataByJson(index,nums,nums2);
    let colorArray = getColor(index,nums,nums2);
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
    bindChartClick(myChart,2);
}
homePageInit.initHuanEcharts2 = (id,index,nums,stationName) => {
    // 环形图
    var myChart = echarts.init(document.getElementById(id));
    var option = {
        title: {
            text: stationName,
            show: true,
            x: "center",
            y: "bottom",
            textStyle: {
                color: '#a3a6b4',
                fontSize: 13,
                fontWeight: 'bold'
            },
            left: 'center',
            top: '90%',
            bottom: '69%',
            itemGap: 60,
        },
        tooltip: {
            show: false,
        },
        color: ['#B7DD7'],
        legend: {
            orient: 'vertical',
            x: 690,
            y: 120,
            data: ['www'],

        },
        series: [{
            type: 'pie',
            //起始刻度的角度，默认为 90 度，即圆心的正上方。0 度为圆心的正右方。
            startAngle: 0,
            hoverAnimation: false,
            tooltip: {},
            radius: ["30%", "47%"],
            center: ['50%', '80%'],
            label: {
                normal: {
                    show: true,
                    position: 'center',
                    color: '#fff',
                    formatter: function(params) {
                        return params.value
                    },
                },
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data: [{
                value: nums,
                itemStyle: {
                    normal: {
                        color: "rgba(80,150,224,0)"
                    }
                }
            },

            ]
        },
            {
                type: 'pie',
                startAngle: 0,
                hoverAnimation: false,
                radius: ["70%", "87%"],
                center: ['50%', '80%'],
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '10',
                            fontWeight: 'bold'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data: [{
                    value: 300,
                    itemStyle: {
                        normal: {
                            color: "rgba(1,218,220,0)"
                        }
                    }
                },
                    {
                        value: 240,
                        itemStyle: {
                            normal: {
                                color: "rgba(1,218,220,1)"
                            }
                        }

                    },
                    {
                        value: 60,
                        itemStyle: {
                            normal: {
                                color: "rgba(1,218,220,0.1)"
                            }
                        }
                    },
                ]
            }
        ]
    };
    myChart.setOption(option);
    $("#by"+index).show();
    bindChartClick(myChart,3);
}

let getHuanDataByJson = (index,nums,nums2)=>{
    let dataArray = [];


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
           nowIndexData.value = nums[index];
           dataArray.push(nowIndexData);
           otherIndexData.value = nums2[index];
           dataArray.push(otherIndexData);

    return dataArray;
}

let getColor = (index,nums,nums2)=>{
    let colorArray = [];
    if(nums[index]!=0&&nums2[index]!=0){
       colorArray.push('#FF8F63')
       colorArray.push('#152364')
    }else{
        colorArray.push('#152364')
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
            $("#leftTable3").html((json.maxWeight/1000).toFixed(2));
            $("#leftTable4").html('');
            $("#leftTable4").html(json.stationNums);
            $("#lastZuiDaCheZhong").html('')
            $("#lastZuiDaCheZhong").html((json.maxWeight/1000).toFixed(2))
        }
    });
}

//******************************************************************************柱状图初始化***************************************************************************************

homePageInit.initLastEcharts = () => {
    $.ajax({
        type: 'POST',
        url: '/tWimMsg/getGuanJianChaoZhongCheLiangEchartsList',
        dataType: 'json',
        data: {
            stationPorts: homePageInit.stationPort.toString(),
            limit:6
        },
        error: function (msg) {
        },
        success: function (json) {
            homePageInit.setLastEcharts(json);
        }
    });
}

homePageInit.setLastEcharts = (json) => {
    var myChart = echarts.init(document.getElementById("liti"));
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
                        color: 'white',
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


    myChart.setOption(option);
    bindChartClick(myChart,4)
}

let initLeftChartDetail=(d)=>{
    let url="";
    switch (d) {
        case 1:
            url = '/tWimMsg/getCheLiuLiangEchartsList';
            break;
        case 2:
            url = '/tWimMsg/getChaoZaiEchartsList';
            break;
        case 3:
            url = '/tWimMsg/getChaoZaiEchartsList';
            break;
        case 4:
            url = '/tWimMsg/getGuanJianChaoZhongCheLiangEchartsList';
            break;
    }
    $.ajax({
        type: 'POST',
        url: url,
        dataType: 'json',
        data: {
            stationPorts: homePageInit.stationPort.toString()
        },
        error: function (msg) {
        },
        success: function (json) {
            $("#mainContent tbody").empty();
            let stationNames = json.stationNames.split(",")
            let nums = json.nums.split(",");

            let dom;
            if(d==1){
                for(let i=stationNames.length-1;i>=0;i--){
                    let tr = "<tr><td>"+stationNames[i]+"</td><td>"+nums[i]+"</td></tr>";
                    dom+=tr;
                }
            }else{
                if(d==2){
                    let numsBili = json.numsBili.split(",");
                    for(let i=0;i<stationNames.length;i++){
                        let num = numsBili[i];
                        if(undefined==num){
                            num="0%";
                        }else{
                            num = num*100+"%"
                        }
                        let tr = "<tr><td>"+stationNames[i]+"</td><td>"+num+"</td></tr>";
                        dom+=tr;
                    }
                }else{
                    for(let i=0;i<stationNames.length;i++){
                        let tr = "<tr><td>"+stationNames[i]+"</td><td>"+nums[i]+"</td></tr>";
                        dom+=tr;
                    }
                }
            }
            $("#mainContent tbody").append(dom);
            $("#chart1Detail").modal("show");
        }
    });
}

let initLeftChartDetail4=(d)=>{
    $.ajax({
        type: 'POST',
        url: '/tWimMsg/getGuanJianChaoZhongCheLiangEchartsList',
        dataType: 'json',
        data: {
            stationPorts: homePageInit.stationPort.toString()
        },
        error: function (msg) {
        },
        success: function (json) {
            $("#mainContent4 tbody").empty();
            let stationNames = json.stationNames.split(",")
            let cnt2 = json.cnt2.split(",");
            let cnt3 = json.cnt3.split(",");
            let dom;
            for(let i=0;i<stationNames.length;i++){
                let tr = "<tr><td>"+stationNames[i]+"</td><td>"+cnt2[i]+"</td><td>"+cnt3[i]+"</td></tr>";
                dom+=tr;
            }
            $("#mainContent4 tbody").append(dom);
            $("#chart4Detail").modal("show");
        }
    });
}