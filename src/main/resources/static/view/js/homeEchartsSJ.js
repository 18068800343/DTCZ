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

let bindLeftLinks = (stationNames,links) => {
    let dom;
    $("#mainContent1 tbody").empty();
    for(let i=0;i<stationNames.length;i++){
        let link = links[i];
        if(link==0){
            link="<label style='color: #843534'>未接通</label>";
        }else{
            link = "<label style='color: #1B5ACB'>已接通</label>"
        }
        let tr = "<tr><td>"+stationNames[i]+"</td><td>"+link+"</td></tr>";
        dom+=tr;
    }
    $("#mainContent1 tbody").append(dom);
    $("#chart1Detail1").modal("show");
}

let bindMaxWeight = (idLocal) => {
    let dom;
    $("#mainContent1 tbody").empty();
    for(let i=0;i<stationNames.length;i++){
        let link = links[i];
        if(link==0){
            link="<label style='color: #843534'>未接通</label>";
        }else{
            link = "<label style='color: #1B5ACB'>已接通</label>"
        }
        let tr = "<tr><td>"+stationNames[i]+"</td><td>"+link+"</td></tr>";
        dom+=tr;
    }
    $("#mainContent1 tbody").append(dom);
    $("#chart1Detail1").modal("show");
}

homePageInit.initditu = () => {
    $.ajax({
        type: 'POST',
        url: '/tWimMsg/getDiTujwdByPort',
        dataType: 'json',
        async: false,
        data: {},
        error: function (msg) {
        },
        success: function (json) {
            if (json != null) {
                initHomeMap(json.lnglat.split(","),json.stationNames.split(","),json.nums.split(","));
            }
        }
    });
}
homePageInit.initditu()

function initHomeMap(lngLats,stationNames,nums){
    let geoCoordData = {};
    let markPointData = [];
    var size = [23,20,17,13,10,8]
    for(let i in lngLats){
        var liuliangNums=nums[i];
        let lngLatArray = [];
        let markPointItem = {};
        markPointItem.name = i+"";
        markPointItem.value = stationNames[i];
        //markPointItem.symbolSize=size[i];
        if(liuliangNums>40000){
            markPointItem.symbolSize=size[0];
        }else if(liuliangNums>30000){
            markPointItem.symbolSize=size[1];
        }else if(liuliangNums>20000){
            markPointItem.symbolSize=size[2];
        }else if(liuliangNums>10000){
            markPointItem.symbolSize=size[3];
        }else if(liuliangNums>5000){
            markPointItem.symbolSize=size[4];
        }else{
            markPointItem.symbolSize=size[5];
        }
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
            let myChartmainfir = ec.init(document.getElementById('mainfir'));
            let ecConfig = require('echarts/config');
            let zrEvent = require('zrender/tool/event');
            let curIndx = 0;
            let mapType = ["江苏"];

            myChart.on(ecConfig.EVENT.MAP_SELECTED, function(param) {});
            option = {

                tooltip: {
                    trigger: 'item',
                    //formatter:'dede{b}'
                    confine:true,
                    formatter: '{b}',
                    formatter: function (params,ticket,callback){
                        let $pna = params.name;
                        let res = '';
                        console.log(params,ticket)
                        if(stationNames[$pna]!=undefined){
                            return stationNames[$pna];
                        }else{
                            return $pna;
                        }

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
                            borderColor: '#6F9BD5',
                            borderWidth: 1
                        },
                        areaStyle: {
                            color: '#6F9BD5',//默认的地图板块颜色
                        },
                        emphasis: {
                            label: {
                                show: false
                            }
                        }
                    },
                    data: markPointData,
                    geoCoord: geoCoordData,
                    hoverable:false,//隐藏悬浮背景色
                    markPoint: {
                        /*symbol: 'circle',*/
                        symbolSize: (val, params) => {
                        	  return val[1] * 3;
                        	},
                        itemStyle: {
                            normal: {
                            	color: '#2e70bc', //地图背景色
                            	areaColor: '#006fff',
                                borderColor: '#E5E324',
                                borderWidth: 1, // 标注边线线宽，单位px，默认为1
                                label: {
                                    show: false
                                },
//                                show: false
                            }
                        },
                       /* effect: {
                            show: true,
                            shadowBlur: 0,
                            loop: true
                        },*/
                        itemStyle: {
                            normal: {
                                color: '#007CFC', //标志颜色
                            }
                        },
                        data : markPointData
                    }

                }]
            },
            
            myChartmainfir.on(ecConfig.EVENT.CLICK, eConsole);
            myChart.on(ecConfig.EVENT.CLICK, eConsole);
            function eConsole(param){
                var name=param.value;
                if(name!=null&&name!=""&&name!=undefined&&name!="-"){
                    window.location.href = 'shishijianceSJ.html';
                    sessionStorage.setItem("stationName", name);
                }
            }


            myChart.setOption(option);
            myChartmainfir.setOption(option);

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
                //initHomeMap(json.lnglat.split(","),homePageInit.stationName);
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
    let maxdataShadow = []
    for (let i = 0; i < barData.length; i++) {
        maxdataShadow.push(barData[barData.length-1])
    }
    let barCategoryGap;
    switch (barData.length) {
        case 1:
            barCategoryGap="80%";
            break;
        case 2:
            barCategoryGap="70%";
            break;
        case 3:
            barCategoryGap="60%";
            break;
        case 4:
            barCategoryGap="44%";
            break;
        case 5:
            barCategoryGap="35%";
            break;
        case 6:
            barCategoryGap="30%";
            break;
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
                },
                boundaryGap: ['0%', '20%'],//留白大小，坐标轴两边留白
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
                        show: false,
                        position: 'right',
                        distance: 10,
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

            },// 背景条
                {
                    type: 'bar',
                    barWidth: 12,
                    silent: true,
                    itemStyle: {
                        normal: { color: '#DDDDDD', barBorderRadius: 45 }
                    },
                    barGap: '-100%', // 可以使两个条重叠
                    barCategoryGap: '50%',
                    data: maxdataShadow,
                    label: {
                        normal: {
                            show: true,
                            position: 'right',
                            distance: 10,
                            color:'#white',
                            formatter: function(data) {
                                return dataShadow[data.dataIndex];
                            },
                        }
                    }
                },// 背景条2覆盖第一个背景
                {
                    type: 'bar',
                    barWidth: 12,
                    silent: true,
                    itemStyle: {
                        normal: { color: '#003363', barBorderRadius: 45 }
                    },
                    barGap: '-100%', // 可以使两个条重叠
                    barCategoryGap: barCategoryGap,
                    data: maxdataShadow,
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
   /* bindChartClick(myChart,1);*/
}

homePageInit.initFirTongji= () =>{
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
            homePageInit.stationName = json.stationNames.split(",").reverse()
            homePageInit.nums = json.nums.split(",").reverse();
            initFirLiuliangtongji(homePageInit.stationName, homePageInit.nums)

        }
    });
}

let initFirLiuliangtongji=(stationNames,nums)=>{
    if(nums!=null&&nums.length>0){
        for(let i=0;i<nums.length+1;i++){
            $("#fir_liuliang"+i+"").html("")
            $("#fir_liuliang"+i+"").html(nums[i])
            /* ($("#fir_liuliang"+i+"").attr("title",stationNames[i]))*/
            $("#Five_liuliang"+i+"").html("")
            $("#Five_liuliang"+i+"").html(nums[i])
        }
    }
    if(stationNames!=null&&stationNames.length>0){
        for(let i=0;i<stationNames.length+1;i++){
            $("#fir_liuliangName"+i+"").html("")
            $("#fir_liuliangName"+i+"").html(stationNames[i])
             $("#fir_liuliangName"+i+"").attr('title', stationNames[i])
            $("#Five_liuliangName"+i+"").html("")
            $("#Five_liuliangName"+i+"").html(stationNames[i])
        }
    }

}

/*let initFirChaoZaiLvTongji=()=>{

   $.ajax({
       type: 'POST',
       url: '/tWimMsg/getChaoZaiEchartsList',
       dataType: 'json',
       data: {
           stationPorts: homePageInit.stationPort.toString()
       },
       error: function (msg) {
       },
       success: function (json) {
          let stationNames = json.stationNames.split(",")
           let nums = json.nums.split(",");
           let numsBili = json.numsBili.split(",");
           for(let i=0;i<stationNames.length;i++){
               let num = numsBili[i];
               if(undefined==num){
                   num="0%";
               }else{
                   num = num*100
                   num = num.toFixed(2)+"%";
               }
               $("#fir_chaozailv"+i+"").html("")
               $("#fir_chaozailv"+i+"").html(num)
               $("#fir_chaozailvName"+i+"").html("")
               $("#fir_chaozailvName"+i+"").html(stationNames[i])
           }

       }
   });
}*/

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
            homePageInit.stationName = json.stationNames.split(",").reverse()
            homePageInit.nums = json.nums.split(",").reverse();
            homePageInit.setLeftEcharts(id, homePageInit.stationName, homePageInit.nums);
            initFirLiuliangtongji(homePageInit.stationName, homePageInit.nums)

        }
    });
}


homePageInit.initLiuZhouChaoZaiShuLiang = (id) => {
    $.ajax({
        type: 'POST',
        url: '/tWimMsg/initLiuZhouChaoZaiShuLiang',
        dataType: 'json',
        data: {
            stationPorts: homePageInit.stationPort.toString(),
            limit: 5
        },
        error: function (msg) {
        },
        success: function (json) {
            let stationName = json.stationNames.split(",").reverse()
            let nums = json.nums.split(",").reverse();
            homePageInit.setLeftEcharts(id, stationName, nums);
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
            for (let i in homePageInit.downNums) {
                homePageInit.initHuanEcharts("huan" + i, i, homePageInit.downNums, homePageInit.downStationName[i], homePageInit.downNums2);
                homePageInit.initHuanEcharts2("by" + i, i, homePageInit.downNums[i], homePageInit.downStationName[i], homePageInit.downNums2, homePageInit.downNums, homePageInit.downNums2[i]);
            }
        }
    });
}

let getHuanDataByJson2 = (index,nums,nums2)=>{
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
                formatter: '{c}',
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
    /*bindChartClick(myChart,2);*/
}
homePageInit.initHuanEcharts2 = (id,index,nums,stationName,nums2,nums3,num4) => {
    // 环形图
    let myChart = echarts.init(document.getElementById(id));
    let dataArray = getHuanDataByJson2(index, nums3, nums2);
    let colorArray = getColor(index,nums3,nums2);
    /*let option = {
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
            startAngle: 180, //开始角度 左侧角度
            endAngle: 0, //结束角度 右侧
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
    };*/
    let option = {
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
                    value: num4==0?1:num4,
                    itemStyle: {
                        normal: {
                            color: "rgba(1,218,220,0)"
                        }
                    }
                },
                    {
                        value: nums,
                        itemStyle: {
                            normal: {
                                color: "rgba(1,218,220,1)"
                            }
                        }

                    },
                    {
                        value: (num4-nums)==0?1:(num4-nums),
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
    /*bindChartClick(myChart,3);*/
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
            $("#leftTable1fir").html('');
            $("#leftTable1fir").html(json.totalCheLiu);
            
            
            $("#leftTable2").html('');
            $("#leftTable2").html(json.totalChaoZai);
            $("#leftTable2fir").html('');
            $("#leftTable2fir").html(json.totalChaoZai);
            homePageInit.idLocal = json.idLocal;
            $("#leftTable3").html('');
            $("#leftTable3").html((json.maxWeight/1000).toFixed(2));
            $("#leftTable3fir").html('');
            $("#leftTable3fir").html((json.maxWeight/1000).toFixed(2));

            $("#leftTable4").html('');
            $("#leftTable4").html(json.stationNums);
            $("#leftTable4fir").html('');
            $("#leftTable4fir").html(json.stationNums);
            $("#lastZuiDaCheZhong").html('')
            $("#lastZuiDaCheZhong").html((json.maxWeight/1000).toFixed(2))
            homePageInit.linkStationNames = json.stationNames.split(",");
            homePageInit.links = json.links.split(",");
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
    let myChart = echarts.init(document.getElementById("liti"));
    /*let option = {
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
     // 背景条
        
        legend: {
            data: ['一级预警(≥80吨)', '二级预警(≥100吨)'],
            textStyle: {
                color: '#A3DCEC',
                fontSize: 13
            },

        },
        
        xAxis: [
            {
            	 "axisLine":{       //y轴
                     "show":false

                },
                type: 'category',
                data: json.stationNames.split(","),
                axisLabel : {
                    formatter : function(params){
                        let newParamsName = "";
                        let paramsNameNumber = params.length;
                        let provideNumber = 3;
                        let rowNumber = Math.ceil(paramsNameNumber / provideNumber);
                        if (paramsNameNumber > provideNumber) {
                            for (let p = 0; p < rowNumber; p++) {
                                let tempStr = "";
                                let start = p * provideNumber;
                                let end = start + provideNumber;
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
                name: '一级预警(≥80吨)',
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
                        color: 'white',
                    }
                },
                data: json.cnt3.split(","),
                barWidth : 20,//柱图宽度
                itemStyle:{
                    barBorderRadius:[50,50,0,0],//柱顶弧形
                }
            }
        ]
    };*/
    let maxdataShadow = []
    let barData = json.cnt2.split(",")

    for (let i = 0; i < barData.length; i++) {
        maxdataShadow.push(Math.max.apply(null, barData) == 0 ? 1 : Math.max.apply(null, barData))
    }
    let option = {
        color: ['#6154FD', '#FE545E'],
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
        // 背景条

        legend: {
            data: ['一级预警(≥80吨)', '二级预警(≥100吨)'],
            textStyle: {
                color: '#A3DCEC',
                fontSize: 13
            },


        },

        xAxis: [
            {
                "axisLine":{       //y轴
                    "show":false

                },
                type: 'category',
                data: json.stationNames.split(","),
                axisLabel : {
                    formatter: function (params) {
                        let newParamsName = "";
                        let paramsNameNumber = params.length;
                        let provideNumber = 3;
                        let rowNumber = Math.ceil(paramsNameNumber / provideNumber);
                        if (paramsNameNumber > provideNumber) {
                            for (let p = 0; p < rowNumber; p++) {
                                let tempStr = "";
                                let start = p * provideNumber;
                                let end = start + provideNumber;
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
                }
            },{
                type: 'category',
                axisLine: {
                    show: false
                },
                axisTick: {
                    show: false
                },
                axisLabel: {
                    show: false
                },
                splitArea: {
                    show: false
                },
                splitLine: {
                    show: false
                },

            },
        ],
        yAxis: [
            {
                show:false,
                type: 'value',
            },

        ],
        series: [
            {
                type: 'bar',
                xAxisIndex: 1,
                zlevel: 1,
                itemStyle: {
                    normal: {
                        color: '#003363',
                        borderWidth: 0,
                        barBorderRadius:[50,50,0,0],//柱顶弧形
                        shadowBlur: {
                            shadowColor: 'rgba(255,255,255,0.31)',
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowOffsetY: 2,
                        },
                    }
                },
                barWidth: 20,
                barGap: '35%',
                data: maxdataShadow,
                label: {
                    normal: {
                        show: true,
                        position: 'top',
                        //distance:300,
                        color: 'white',
                        formatter: function(a) {
                            num = json.cnt2.split(",");
                            for (let i = 0; i < num.length; i++) {
                                if (a.dataIndex == i) {
                                    return num[i];
                                }
                            }
                        }
                    }
                },
            },
            {
                type: 'bar',
                xAxisIndex: 1,
                barGap: '35%',
                data: maxdataShadow,
                zlevel: 1,
                barWidth: 20,
                itemStyle: {
                    normal: {
                        color: '#003363',
                        borderWidth: 0,
                        barBorderRadius:[50,50,0,0],//柱顶弧形
                        shadowBlur: {
                            shadowColor: 'rgba(255,255,255,0.31)',
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowOffsetY: 2,
                        },
                    }
                },
                label: {
                    normal: {
                        show: true,
                        position: 'top',
                        //distance:300,
                        color: 'white',
                        formatter: function(a) {
                            num = json.cnt3.split(",");
                            for (let i = 0; i < num.length; i++) {
                                if (a.dataIndex == i) {
                                    return num[i];
                                }
                            }
                        }
                    }
                },
            },

            {
                name: '一级预警(≥80吨)',
                type: 'bar',
                label: {
                    normal: {
                        show: false,
                        position: 'top',
                        color: 'white',
                    }
                },
                data: json.cnt2.split(","),
                barWidth : 20,//柱图宽度
                zlevel: 2,
                barGap: '35%',
                itemStyle:{
                    barBorderRadius:[50,50,0,0],//柱顶弧形
                }


            },
            {


                name: '二级预警(≥100吨)',
                type: 'bar',
                label: {
                    normal: {
                        show: false,
                        position: 'top',
                        color: 'white',
                    }
                },
                data: json.cnt3.split(","),
                barWidth : 20,//柱图宽度
                zlevel: 2,
                barGap: '35%',
                itemStyle:{
                    barBorderRadius:[50,50,0,0],//柱顶弧形
                }

            },
        ]
    }


    myChart.setOption(option);
    /*bindChartClick(myChart,4)*/
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
                $("#chart1Detail b").empty();
                $("#chart1Detail b").append("当日车流量统计");
                for(let i=0;i<stationNames.length;i++){
                    let tr = "<tr><td>"+stationNames[i]+"</td><td>"+nums[i]+"</td></tr>";
                    dom+=tr;
                }
            }else{
                if(d==2){
                    $("#chart1Detail b").empty();
                    $("#chart1Detail b").append("当日超载比例统计");
                    let numsBili = json.numsBili.split(",");
                    for(let i=0;i<stationNames.length;i++){
                        let num = numsBili[i];
                        if(undefined==num){
                            num="0%";
                        }else{
                            num = num*100
                            num = num.toFixed(2)+"%";
                        }
                        let tr = "<tr><td>"+stationNames[i]+"</td><td>"+num+"</td></tr>";
                        dom+=tr;
                    }
                }else{
                    $("#chart1Detail b").empty();
                    $("#chart1Detail b").append("当日超载数量统计");
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

//***************************************************************初始化趋势统计图***************************************************************
homePageInit.initFirqstjt = (id, callback) => {


    $.ajax({
        type: 'POST',
        url: '/tWimMsg/getFirChaoZaiLv',
        dataType: 'json',
        data: {
            stationPorts: homePageInit.stationPort.toString(),
            limit: 6
        },
        error: function (msg) {
        },
        success: function (json) {
            //初始化超载率统计
            if(json!=null){
                var stationNames=[];
                var numsBili=[]
                for(var i=0;i<json.length;i++){
                    stationNames.push(json[i].stationNames)
                    numsBili.push(json[i].numsBili==undefined?"0%":((json[i].numsBili)*100).toFixed(2)+"%")
                }
                for(let i=0;i<numsBili.length;i++){
                    let num = numsBili[i];
                    $("#fir_chaozailv"+i+"").html("")
                    $("#fir_chaozailv"+i+"").html(num)
                    $("#fir_chaozailvName"+i+"").html("")
                    $("#fir_chaozailvName"+i+"").html(stationNames[i])
                     $("#fir_chaozailvName"+i+"").attr('title',stationNames[i])
                }
            }
            /*homePageInit.downStationNames = json.stationNames.split(",");
            homePageInit.downNums = json.nums.split(",");
            homePageInit.numCount = json.numCount.split(",");
            homePageInit.initFirqstjtEcharts(id,homePageInit.downStationNames,homePageInit.downNums, homePageInit.numCount );*/

        }
    });
    $.ajax({
        type: 'POST',
        url: '/tWimMsg/getQushitu',
        dataType: 'json',
        data: {
            stationPorts: homePageInit.stationPort.toString(),
        },
        error: function (msg) {
        },
        success: function (json) {
            //初始化超载率统计
            var dateArr = [];
            var liuliangArr = []
            var chaizaiArr = [];
            for (var i = 0; i < json.length; i++) {
                dateArr.push(json[i].avgTime)
                liuliangArr.push(json[i].zongliuliangnum)
                chaizaiArr.push(json[i].zongchaozainum)
            }

            let chart = homePageInit.initFirqstjtEcharts(id, dateArr, chaizaiArr, liuliangArr);
            callBackEchart(chart);
        }
    });
}
homePageInit.initFirqstjtEcharts = (id,stationNames,nums,numCount) => {
    // 趋势统计图
    let myChart = echarts.init(document.getElementById(id));

    let colors = ['#00f1b5', '#fd2b2a'];


    option = {
        color: colors,
        textStyle: {
            color: '#fff',
            fontSize: 13
        },
        tooltip: {
            trigger: 'none',
            axisPointer: {
                type: 'cross'
            }
        },
        legend: {
            data:['总流量', '超载量'],
            textStyle: {
                color: '#A3DCEC',
                fontSize: 13
            },
        },
        grid: {
            top: 70,
            bottom: 50
        },
        xAxis: [
            {
                type: 'category',
                axisTick: {
                    alignWithLabel: true
                },
                axisLine: { //y轴
                    show: false
                },
                splitLine: {
                    show: false
                },
                axisPointer: {
                    label: {
                        formatter: function (params) {
                            return '总流量  ' + params.value
                                + (params.seriesData.length ? '：' + params.seriesData[0].data : '');
                        }
                    }
                },
                data: stationNames,
                axisLabel : {//坐标轴刻度标签的相关设置。
                    interval:0,
                    //rotate:"45" //文字倾斜
                    formatter : function(params) { //文字换行
                        let newParamsName = "";// 最终拼接成的字符串
                        let paramsNameNumber = params.length;// 实际标签的个数
                        let provideNumber = 5;// 每行能显示的字的个数
                        let rowNumber = Math.ceil(paramsNameNumber / provideNumber);// 换行的话，需要显示几行，向上取整
                        /**
                         * 判断标签的个数是否大于规定的个数， 如果大于，则进行换行处理 如果不大于，即等于或小于，就返回原标签
                         */
                        // 条件等同于rowNumber>1
                        if (paramsNameNumber > provideNumber) {
                            /** 循环每一行,p表示行 */
                            for (let p = 0; p < rowNumber; p++) {
                                let tempStr = "";// 表示每一次截取的字符串
                                let start = p * provideNumber;// 开始截取的位置
                                let end = start + provideNumber;// 结束截取的位置
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
            },
            {
                type: 'category',
                axisTick: {
                    alignWithLabel: true
                },
                axisLine: {
                    onZero: false,
                    lineStyle: {
                        color: colors[0]
                    }
                },
                axisLine: { //y轴
                    show: false
                },
                splitLine: {
                    show: false
                },
                axisPointer: {
                    label: {
                        formatter: function (params) {
                            return '超载量  ' + params.value
                                + (params.seriesData.length ? '：' + params.seriesData[0].data : '');
                        }
                    }
                },

            }
        ],
        yAxis: [
            {
                type: 'value',
                position: 'left',
                splitLine: {
                    show: false
                },
                axisLine: { //y轴
                    show: false
                },
                splitLine: {
                    show: false
                },
            }
        ],
        series: [
            {
                name: '总流量',
                type: 'line',
                xAxisIndex: 1,
                smooth: true,
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                data: numCount
            },
            {
                name: '超载量',
                type: 'line',
                smooth: true,
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                data: nums
            }
        ]
    };


    myChart.setOption(option)
    return myChart;
}



//*********************************************************  首页第二个页面统计 ************************************************************************************************
homePageInit.initSecHomeData = ()=>{

    $.ajax({
        type: 'POST',
        url: '/tWimMsg/getSecHomeTotal',
        dataType: 'json',
        data: {
            axlesCount:'2,3,4,5,6',
            stationPorts: homePageInit.stationPort.toString(),
        },
        error: function (msg) {
        },
        success: function (json) {
            //车流量
            let secliuliangNum = json.liuliangCount.split(',')
            for (let i = 0; i < secliuliangNum.length; i++) {
                let zhouNum = secliuliangNum[i].split('-')[0]
                let zhouNumValue = secliuliangNum[i].split('-')[1]
                switch (zhouNum) {
                    case "2":
                        $("#leftTable1two").html('');
                        $("#leftTable1two").html(zhouNumValue);
                        break;
                    case "3":
                        $("#leftTable1third").html('');
                        $("#leftTable1third").html(zhouNumValue);
                        break;
                    case "4":
                        $("#leftTable1four").html('');
                        $("#leftTable1four").html(zhouNumValue);
                        break;
                    case "5":
                        $("#leftTable1five").html('');
                        $("#leftTable1five").html(zhouNumValue);
                        break;
                    case "6":
                        $("#leftTable1sex").html('');
                        $("#leftTable1sex").html(zhouNumValue);
                        break;
                    default:
                        $("#leftTable1two").html(0);
                        $("#leftTable1third").html(0);
                        $("#leftTable1four").html(0);
                        $("#leftTable1five").html(0);
                        break;
                }
            }

            //超载
            let secchaozaiNum = json.ChaoZaiCount.split(',')
            for (let i = 0; i < secchaozaiNum.length; i++) {
                let zhouNum = secchaozaiNum[i].split('-')[0]
                let zhouNumValue = secchaozaiNum[i].split('-')[1]
                switch (zhouNum) {
                    case "2":
                        $("#leftTable2two").html('');
                        $("#leftTable2two").html(zhouNumValue);
                        break;
                    case "3":
                        $("#leftTable2third").html('');
                        $("#leftTable2third").html(zhouNumValue);
                        break;
                    case "4":
                        $("#leftTable2four").html('');
                        $("#leftTable2four").html(zhouNumValue);
                        break;
                    case "5":
                        $("#leftTable2five").html('');
                        $("#leftTable2five").html(zhouNumValue);
                        break;
                    case "6":
                        $("#leftTable2sex").html('');
                        $("#leftTable2sex").html(zhouNumValue);
                        break;
                    default:
                        $("#leftTable2two").html(0);
                        $("#leftTable2third").html(0);
                        $("#leftTable2four").html(0);
                        $("#leftTable2five").html(0);
                        break;
                }
            }
            //超载比例
            for (let a = 0; a < secliuliangNum.length; a++) {
                for (let j = a; j < secchaozaiNum.length; j++) {
                    let zhouNum = secchaozaiNum[j].split('-')[0]
                    let bili;
                    let liuliangnum = parseInt(secliuliangNum[a].split('-')[1])
                    let chaozainum = parseInt(secchaozaiNum[j].split('-')[1])
                    let czbili = (chaozainum * 1) / (liuliangnum * 1);
                    if (undefined == czbili) {
                        bili = "0%";
                    } else {
                        bili = (czbili * 100).toFixed(2) + "%";
                    }
                    switch (zhouNum) {
                        case "2":
                            $("#leftTable3two").html('');
                            $("#leftTable3two").html(bili);
                            break;
                        case "3":
                            $("#leftTable3third").html('');
                            $("#leftTable3third").html(bili);
                            break;
                        case "4":
                            $("#leftTable3four").html('');
                            $("#leftTable3four").html(bili);
                            break;
                        case "5":
                            $("#leftTable3five").html('');
                            $("#leftTable3five").html(bili);
                            break;
                        case "6":
                            $("#leftTable3sex").html('');
                            $("#leftTable3sex").html(bili);
                            break;
                        default:
                            break;
                    }
                }
            }


        }
    });
}

homePageInit.initSecChaoZaiDataToday=()=>{
    homePageInit.initSecChaoZaiDataTodayEchars(2)
    homePageInit.initSecChaoZaiDataTodayEchars(3)
    homePageInit.initSecChaoZaiDataTodayEchars(4)
    homePageInit.initSecChaoZaiDataTodayEchars(5)
    homePageInit.initSecChaoZaiDataTodayEchars(6)
}
homePageInit.initSecChaoZaiDataTodayEchars=(zhoushu)=>{
    $.ajax({
        type: 'POST',
        url: '/tWimMsg/getSecChaoZaiEcharsList',
        dataType: 'json',
        data: {
            axlesCount:zhoushu,
            stationPorts: homePageInit.stationPort.toString(),
            limit:4,
        },
        error: function (msg) {
        },
        success: function (json) {
            setSecCZEchars(json,zhoushu);
        }
    });
}

let setSecCZEchars=(data,zhoushu)=>{
    let id;
    switch (zhoushu) {
        case 2:
            id="maint";
            break;
        case 3:
            id="maina";
            break;
        case 4:
            id="maind";
            break;
        case 5:
            id="mainf";
            break;
        case 6:
            id="mainh";
            break;
        default:
            break;
    }
// 基于准备好的dom，初始化echarts实例
    let myChart = echarts.init(document.getElementById(id));
    var bili=data.chaoZaiBili.split(",");
    var chaozaibili=[];
    for(var i=0;i<bili.length;i++){
        var bili2=(bili[i]*100).toFixed(2);
        chaozaibili[i]=bili2;
    }
    // 指定图表的配置项和数据
    let option = {
        textStyle: {
            color: '#fff',
            fontSize: 13
        },
        title: {
            text: '当日超载数量与比例',
            padding: [20, 0, 0, 0],//标题内边距上右下
            textStyle: {//textStyle设置标题样式
                color: '#A1A2B4',
                fontSize: 13
            },
        },

        xAxis: [{
            type: 'category',
            data: data.sjName.split(","),
            axisPointer: {
                type: 'shadow'
            },
            axisLabel: {
                show: true,
                textStyle: {
                    color: '#A1A2B4'
                },
                formatter: function (params) {
                    let newParamsName = "";
                    let paramsNameNumber = params.length;
                    let provideNumber = 3;
                    let rowNumber = Math.ceil(paramsNameNumber / provideNumber);
                    if (paramsNameNumber > provideNumber) {
                        for (let p = 0; p < rowNumber; p++) {
                            let tempStr = "";
                            let start = p * provideNumber;
                            let end = start + provideNumber;
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
            axisTick: { //y轴刻度线
                show: false
            },
            axisLine: { //y轴
                show: false
            },
            splitLine: {
                show: false
            }

        }],
        yAxis: [{
            type: 'value',
            axisLabel: {
                color: '#abb8ce',
            },
            axisTick: { //y轴刻度线
                show: false
            },
            axisLine: { //y轴
                show: false
            },
            splitLine: {
                show: false
            }
        },
            {
                type: 'value',
                axisLabel: {
                    color: '#abb8ce',
                    margin:-5,
                    interval: 'auto',
                    formatter: '{value} %'
                },
                axisTick: { //y轴刻度线
                    show: false
                },
                axisLine: { //y轴
                    show: false
                },
                splitLine: {
                    show: false
                }
            }
        ],
        series: [{
            type: 'bar',
            data: data.chaoZaicCount.split(","),
            barWidth: 20, //柱图宽度
            itemStyle:{
                barBorderRadius:[50,50,0,0],//柱顶弧形
            },
            label: {
                normal: {
                    show: true,
                    position: 'top'
                }
            },
            color:'#305BFF'

        },

            {
                type: 'bar',
                barWidth: 20, //柱图宽度
                yAxisIndex: 1,
                color: '#47FFCD',
                data: chaozaibili,
                itemStyle: {
                    barBorderRadius: [50, 50, 0, 0],//柱顶弧形
                },
                label: {
                    show: true,
                    position: 'top',
                    formatter: '{c}%'
                }

            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

homePageInit.initSecChaoZaiDataMonth=()=>{
    homePageInit.initSecChaoZaiEcharsMonth(2)
    homePageInit.initSecChaoZaiEcharsMonth(3)
    homePageInit.initSecChaoZaiEcharsMonth(4)
    homePageInit.initSecChaoZaiEcharsMonth(5)
    homePageInit.initSecChaoZaiEcharsMonth(6)
}
homePageInit.initSecChaoZaiEcharsMonth=(zhoushu)=>{
    $.ajax({
        type: 'POST',
        url: '/tWimMsg/getSecChaoZaiEcharsListMonth',
        dataType: 'json',
        data: {
            axlesCount:zhoushu,
            stationPorts: homePageInit.stationPort.toString(),
            limit:4,
        },
        error: function (msg) {
        },
        success: function (json) {
            initSecChaoZaiEchars(json,zhoushu);
        }
    });
}
let initSecChaoZaiEchars=(data,zhoushu)=> {
    let id;
    let chaoZaiCountMonth;
    switch (zhoushu) {
        case 2:
            id = "mainb";
            chaoZaiCountMonth = data.chaoZai2CountMonth == null ? 0 : data.chaoZai2CountMonth.split(",")
            break;
        case 3:
            id = "mainc";
            chaoZaiCountMonth = data.chaoZai3CountMonth.split(",")
            break;
        case 4:
            id="maine";
            chaoZaiCountMonth=data.chaoZai4CountMonth.split(",")
            break;
        case 5:
            id="maing";
            chaoZaiCountMonth=data.chaoZai5CountMonth.split(",")
            break;
        case 6:
            id="mainj";
            chaoZaiCountMonth=data.chaoZai6CountMonth.split(",")
            break;
        default:
            break;
    }
    let myChart = echarts.init(document.getElementById(id));
    // 指定图表的配置项和数据
    let option = {
        textStyle: {
            color: '#fff',
            fontSize: 13
        },
        title: {
            text: '当月累计超载数量',
            padding: [20, 0, 0, 0],//标题内边距上右下
            textStyle: {//textStyle设置标题样式
                color: '#A1A2B4',
                fontSize: 13
            },
        },

        xAxis: [{
            type: 'category',
            data: data.sjName.split(","),
            axisPointer: {
                type: 'shadow'
            },
            axisLabel: {
                show: true,
                textStyle: {
                    color: '#A1A2B4'
                },
                formatter: function (params) {
                    let newParamsName = "";
                    let paramsNameNumber = params.length;
                    let provideNumber = 3;
                    let rowNumber = Math.ceil(paramsNameNumber / provideNumber);
                    if (paramsNameNumber > provideNumber) {
                        for (let p = 0; p < rowNumber; p++) {
                            let tempStr = "";
                            let start = p * provideNumber;
                            let end = start + provideNumber;
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
            axisTick: { //y轴刻度线
                show: false
            },
            axisLine: { //y轴
                show: false
            },
            splitLine: {
                show: false
            }

        }],
        yAxis: [{
            type: 'value',
            axisLabel: {
                color: '#abb8ce',
            },
            axisTick: { //y轴刻度线
                show: false
            },
            axisLine: { //y轴
                show: false
            },
            splitLine: {
                show: false
            }
        },
            {
                type: 'value',
                axisLabel: {
                    color: '#abb8ce',
                },
                axisTick: { //y轴刻度线
                    show: false
                },
                axisLine: { //y轴
                    show: false
                },
                splitLine: {
                    show: false
                }
            }
        ],
        series: [{
            type: 'bar',
            data: chaoZaiCountMonth,
            barWidth: 20, //柱图宽度
            color:'#305BFF',
            label: {
                normal: {
                    show: true,
                    position: 'top'
                }
            },
            itemStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: '#DE6451'
                    }, {
                        offset: 1,
                        color: '#FEB243'
                    }]),
                    barBorderRadius: 30
                }
            }
        }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}


homePageInit.initGongSiTongJiYuJingData = () => {
    $.ajax({
        type: 'POST',
        url: '/tWimMsg/getGongSiTongJiYuJingData',
        dataType: 'json',
        data: {
            stationPorts: homePageInit.stationPort.toString(),
            limit: 4,
        },
        error: function (msg) {
        },
        success: function (json) {
            initGongSiTongJiYuJingEcharts(json);
        }
    });
}

let initGongSiTongJiYuJingEcharts = (data) => {
//==================================================================小图标==============================================
    let myChart = echarts.init(document.getElementById("firstmain"));
    // 指定图表的配置项和数据

    let company = data.company != null ? data.company.split(",") : new Array(6);
    let yiJi = data.yiJi != null ? data.yiJi.split(",") : [0, 0, 0, 0, 0, 0, 0];
    let erJi = data.erJi != null ? data.erJi.split(",") : [0, 0, 0, 0, 0, 0, 0];

    let option = {
        textStyle: {
            color: '#fff',
            fontSize: 13
        },
        legend: {
            data:['49-100吨', '100吨以上'],
            textStyle: {
                color: '#A3DCEC',
                fontSize: 13
            },
        },
        xAxis: [{
            type: 'category',
            data: company,
            axisPointer: {
                type: 'shadow'
            },
            axisLabel: {
                show: true,
                textStyle: {
                    color: '#A1A2B4'
                }
            },
            axisTick: { //y轴刻度线
                show: false
            },
            axisLine: { //y轴
                show: false
            },
            splitLine: {
                show: false
            }

        }],
        yAxis: [{
            type: 'value',
            axisLabel: {
                color: '#abb8ce',
            },
            axisTick: { //y轴刻度线
                show: false
            },
            axisLine: { //y轴
                show: false
            },
            splitLine: {
                show: false
            }
        },
            {
                type: 'value',
                axisLabel: {
                    color: '#abb8ce',
                },
                axisTick: { //y轴刻度线
                    show: false
                },
                axisLine: { //y轴
                    show: false
                },
                splitLine: {
                    show: false
                }
            }
        ],
        series: [{
            name: '49-100吨',
            type: 'bar',
            data: yiJi,
            barWidth: 20, //柱图宽度
            itemStyle:{
                barBorderRadius:[50,50,0,0],//柱顶弧形
            },
            label: {
                normal: {
                    show: true,
                    position: 'top'
                }
            },
            color: '#305BFF'
        },

            {
                name: '100吨以上',
                type: 'bar',
                yAxisIndex: 1,
                color: '#47FFCD',
                data: erJi,
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

homePageInit.initGongSiTongJiYuJingData_LiuZhou = () => {
    $.ajax({
        type: 'POST',
        url: '/tWimMsg/getGongSiTongJiYuJingData',
        dataType: 'json',
        data: {
            axlesCount: 6,
            stationPorts: homePageInit.stationPort.toString(),
            limit: 4,
        },
        error: function (msg) {
        },
        success: function (json) {
            initGongSiTongJiYuJingData_LiuZhou(json);
        }
    });
}

let initGongSiTongJiYuJingData_LiuZhou = (data) => {
//==================================================================小图标==============================================
    // 指定图表的配置项和数据
    let myChart = echarts.init(document.getElementById("foumain"));

    let company = data.company != null ? data.company.split(",") : new Array(6);
    let yiJi = data.yiJi != null ? data.yiJi.split(",") : [0, 0, 0, 0];
    let erJi = data.erJi != null ? data.erJi.split(",") : [0, 0, 0, 0];
    let option = {
        textStyle: {
            color: '#fff',
            fontSize: 13
        },
        legend: {
            data: ['49~100吨', '100吨以上'],
            textStyle: {
                color: '#A3DCEC',
                fontSize: 13
            },
        },
        xAxis: [
            {
                type: 'category',
                data: company,
                axisPointer: {
                    type: 'shadow'
                },
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#A1A2B4'
                    }
                },
                axisTick: { //y轴刻度线
                    show: false
                },
                axisLine: { //y轴
                    show: false
                },
                splitLine: {
                    show: false
                }

            }
        ],
        yAxis: [
            {
                type: 'value',
                axisLabel: {
                    color: '#abb8ce',
                },
                axisTick: { //y轴刻度线
                    show: false
                },
                axisLine: { //y轴
                    show: false
                },
                splitLine: {
                    show: false
                }
            },
            {
                type: 'value',
                axisLabel: {
                    color: '#abb8ce',
                },
                axisTick: { //y轴刻度线
                    show: false
                },
                axisLine: { //y轴
                    show: false
                },
                splitLine: {
                    show: false
                }
            }
        ],
        series: [
            {
                name: "49~100吨",
                type: 'bar',
                barWidth: 20, //柱图宽度
                data: yiJi,
                itemStyle: {
                    barBorderRadius: [50, 50, 0, 0],//柱顶弧形
                },
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                color: '#6154FD'
            },
            {
                name: "100吨以上",
                type: 'bar',
                barWidth: 20, //柱图宽度
                data: erJi,
                itemStyle: {
                    barBorderRadius: [50, 50, 0, 0],//柱顶弧形
                },
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                color: '#FE545E'
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

}


homePageInit.initShengJieTongJiYuJingData = () => {
    $.ajax({
        type: 'POST',
        url: '/tWimMsg/initShengJieTongJiYuJingData',
        dataType: 'json',
        data: {
            stationPorts: homePageInit.stationPort.toString(),
            limit: 4,
        },
        error: function (msg) {
        },
        success: function (json) {
            initShengJieTongJiYuJingData(json);
        }
    });
}

let initShengJieTongJiYuJingData = (data) => {
    let myChart = echarts.init(document.getElementById("secmain"));
    // 指定图表的配置项和数据

    let provinceStation = data.provinceStation != null ? data.provinceStation.split(",") : new Array(6);
    let yiJi = data.yiJi != null ? data.yiJi.split(",") : [0, 0, 0, 0];
    let erJi = data.erJi != null ? data.erJi.split(",") : [0, 0, 0, 0];

    let option = {
        textStyle: {
            color: '#fff',
            fontSize: 13
        },
        legend: {
            data: ['49~100吨', '100吨以上'],
            textStyle: {
                color: '#A3DCEC',
                fontSize: 13
            },
        },
        xAxis: [
            {
                type: 'category',
                data: provinceStation,
                axisPointer: {
                    type: 'shadow'
                },
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#A1A2B4'
                    }
                },
                axisTick: { //y轴刻度线
                    show: false
                },
                axisLine: { //y轴
                    show: false
                },
                splitLine: {
                    show: false
                }

            }
        ],
        yAxis: [
            {
                type: 'value',
                axisLabel: {
                    color: '#abb8ce',
                },
                axisTick: { //y轴刻度线
                    show: false
                },
                axisLine: { //y轴
                    show: false
                },
                splitLine: {
                    show: false
                }
            },
            {
                type: 'value',
                axisLabel: {
                    color: '#abb8ce',
                },
                axisTick: { //y轴刻度线
                    show: false
                },
                axisLine: { //y轴
                    show: false
                },
                splitLine: {
                    show: false
                }
            }
        ],
        series: [
            {
                name: '49~100吨',
                type: 'bar',
                barWidth: 20, //柱图宽度
                data: yiJi,
                itemStyle: {
                    barBorderRadius: [50, 50, 0, 0],//柱顶弧形
                },
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                color: '#05D3DB'
            },
            {
                name: '100吨以上',
                type: 'bar',
                barWidth: 20, //柱图宽度
                data: erJi,
                itemStyle: {
                    barBorderRadius: [50, 50, 0, 0],//柱顶弧形
                },
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                color: '#B38CFF'
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

homePageInit.initLiuZhouShengJieChaoZai = () => {
    $.ajax({
        type: 'POST',
        url: '/tWimMsg/getSecChaoZaiEcharsListByZhanDian',
        dataType: 'json',
        data: {
            axlesCount: 6,
            stationPorts: homePageInit.stationPort.toString(),
            limit: 5,
        },
        error: function (msg) {
        },
        success: function (json) {
            initLiuZhouShengJieChaoZai(json);
        }
    });
}

let initLiuZhouShengJieChaoZai = (data) => {
    // 指定图表的配置项和数据
    let myChart = echarts.init(document.getElementById("thimain"));

    let stationNames = data.stationNames != null ? data.stationNames.split(",") : new Array(4);
    let chaoZaicCount = data.chaoZaicCount != null ? data.chaoZaicCount.split(",") : [0, 0, 0, 0,0];

    let option = {

        textStyle: {
            color: '#fff',
            fontSize: 13
        },
        xAxis: [{
            type: 'category',
            data: stationNames,
            axisPointer: {
                type: 'shadow'
            },
            axisLabel: {
                show: true,
                textStyle: {
                    color: '#A1A2B4'
                },
                formatter: function (params) {
                    let newParamsName = "";
                    let paramsNameNumber = params.length;
                    let provideNumber = 3;
                    let rowNumber = Math.ceil(paramsNameNumber / provideNumber);
                    if (paramsNameNumber > provideNumber) {
                        for (let p = 0; p < rowNumber; p++) {
                            let tempStr = "";
                            let start = p * provideNumber;
                            let end = start + provideNumber;
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
            axisTick: { //y轴刻度线
                show: false
            },
            axisLine: { //y轴
                show: false
            },
            splitLine: {
                show: false
            }

        }],
        yAxis: [{
            type: 'value',
            axisLabel: {
                color: '#abb8ce',
            },
            axisTick: { //y轴刻度线
                show: false
            },
            axisLine: { //y轴
                show: false
            },
            splitLine: {
                show: false
            }
        },
            {
                type: 'value',
                axisLabel: {
                    color: '#abb8ce',
                },
                axisTick: { //y轴刻度线
                    show: false
                },
                axisLine: { //y轴
                    show: false
                },
                splitLine: {
                    show: false
                }
            }
        ],
        series: [{
            type: 'bar',
            data: chaoZaicCount,
            barWidth: 20, //柱图宽度
            itemStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: '#FE4BA1'
                    }, {
                        offset: 1,
                        color: '#FC5560'
                    }]),
                }
            },
            label: {
                normal: {
                    show: true,
                    position: 'top'
                }
            },
        },
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}


homePageInit.initDanZhouChaoZai = () => {
    $.ajax({
        type: 'POST',
        url: '/tWimMsg/getDanZhouChaoZai',
        dataType: 'json',
        data: {
            stationPorts: homePageInit.stationPort.toString(),
            limit: 4,
        },
        error: function (msg) {
        },
        success: function (json) {
            getDanZhouChaoZai(json);
        }
    });
}

function getListByJSON(json) {
    let listMap = {};
    let listx = [];
    let listy = [];
    for (let i in json) {
        listx[i] = json[i].maxAxle;
        listy[i] = json[i].evtTime;
    }
    listMap.listx = listx;
    listMap.listy = listy;
    return listMap;
}

let getDanZhouChaoZai = (data) => {
    // 指定图表的配置项和数据
    let myChart = echarts.init(document.getElementById("fivemain"));
    let listMap = getListByJSON(data);
    // let maxAxle = data.maxAxle != null ? data.maxAxle.split(",") : new Array(10);
    // let evtTime = data.evtTime != null ? data.evtTime.split(",") : new Array(10);
    let maxAxle = listMap.listx;
    let evtTime = listMap.listy;
    let option = {
        xAxis: {
            type: 'category',
            axisTick: {
                show: false,
            },
            boundaryGap: false,
            axisTick: {
                show: false,
            },
            axisLabel: {
                color: '#fff'
            },
            axisLine: {
                lineStyle: {
                    color: 'rgba(12,102,173,.5)',
                }
            },
        },
        yAxis: {
            type: 'value',
            axisTick: {
                show: false,//不显示刻度线
            },
            axisLabel: {
                color: '#fff',  //y轴上的字体颜色
                margin:2,
            },
            axisLine: {
                lineStyle: {
                    color: 'rgba(12,102,173,.5)',//y轴的轴线的宽度和颜色
                }
            },
            splitLine: {
                show: false
            }
        },
        series: [
            {
                type: 'line',
                symbol: 'none',
                smooth: true,
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: '#bb326f'
                        }, {
                            offset: 1,
                            color: '#2A1A6B'
                        }])
                    }
                },
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    option.xAxis.data = evtTime;
    option.series[0].data = maxAxle;
    myChart.setOption(option);
}

//=======================5========

homePageInit.initditu5 = () => {
    $.ajax({
        type: 'POST',
        url: '/tWimMsg/getDiTujwdByPort2',
        dataType: 'json',
        async: false,
        data: {},
        error: function (msg) {
        },
        success: function (json) {
            if (json != null) {
                initHomeMap5(json.lnglat.split(","),json.stationNames.split(","),json.nums.split(","));
            }
        }
    });
}
homePageInit.initditu5()




function initHomeMap5(lngLats,stationNames,nums){
    let geoCoordData = {};
    let markPointData = [];
    var size = [23,20,17,13,10,8]
    for(let i in lngLats){
        var liuliangNums=nums[i];
        let lngLatArray = [];
        let markPointItem = {};
        markPointItem.name = i+"";
        markPointItem.value = stationNames[i];
        //markPointItem.symbolSize=size[i];
        if(liuliangNums>40000){
            markPointItem.symbolSize=size[0];
        }else if(liuliangNums>30000){
            markPointItem.symbolSize=size[1];
        }else if(liuliangNums>20000){
            markPointItem.symbolSize=size[2];
        }else if(liuliangNums>10000){
            markPointItem.symbolSize=size[3];
        }else if(liuliangNums>5000){
            markPointItem.symbolSize=size[4];
        }else{
            markPointItem.symbolSize=size[5];
        }
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
            let myChartmainfir = ec.init(document.getElementById('mainfir'));
            let myChartmaintwo = ec.init(document.getElementById('maintwo'));
            let ecConfig = require('echarts/config');
            let zrEvent = require('zrender/tool/event');
            let curIndx = 0;
            let mapType = ["江苏"];

            myChart.on(ecConfig.EVENT.MAP_SELECTED, function(param) {});
            option = {

                tooltip: {
                    trigger: 'item',
                    //formatter:'dede{b}'
                    confine:true,
                    formatter: '{b}',
                    formatter: function (params,ticket,callback){
                        let $pna = params.name;
                        let res = '';
                        console.log(params,ticket)
                        if(stationNames[$pna]!=undefined){
                            return stationNames[$pna];
                        }else{
                            return $pna;
                        }

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
                            borderColor: '#6F9BD5',
                            borderWidth: 1
                        },
                        areaStyle: {
                            color: '#6F9BD5',//默认的地图板块颜色
                        },
                        emphasis: {
                            label: {
                                show: false
                            }
                        }
                    },
                    data: markPointData,
                    geoCoord: geoCoordData,
                    hoverable:false,//隐藏悬浮背景色
                    markPoint: {
                        /*symbol: 'circle',*/
                        symbolSize: (val, params) => {
                            return val[1] * 3;
                        },
                        itemStyle: {
                            normal: {
                                color: '#2e70bc', //地图背景色
                                areaColor: '#006fff',
                                borderColor: '#E5E324',
                                borderWidth: 1, // 标注边线线宽，单位px，默认为1
                                label: {
                                    show: false
                                },
//                                show: false
                            }
                        },
                        /* effect: {
                             show: true,
                             shadowBlur: 0,
                             loop: true
                         },*/
                        itemStyle: {
                            normal: {
                                color: '#007CFC', //标志颜色
                            }
                        },
                        data : markPointData
                    }

                }]
            },


                myChartmaintwo.setOption(option);

        }
    );
}


homePageInit.initHomeData5 = ()=>{
    $.ajax({
        type: 'POST',
        url: '/tWimMsg/getHomeDataObject2',
        dataType: 'json',
        data: {
            stationPorts: homePageInit.stationPort.toString(),
        },
        error: function (msg) {
        },
        success: function (json) {
            $("#leftTable15").html('');
            $("#leftTable15").html(json.totalCheLiu);
            $("#leftTable1fir5").html('');
            $("#leftTable1fir5").html(json.totalCheLiu);

            $("#leftTable25").html('');
            $("#leftTable25").html(json.totalChaoZai);
            $("#leftTable2fir5").html('');
            $("#leftTable2fir5").html(json.totalChaoZai);
            homePageInit.idLocal = json.idLocal;
            $("#leftTable35").html('');
            $("#leftTable35").html((json.maxWeight/1000).toFixed(2));
            $("#leftTable3fir5").html('');
            $("#leftTable3fir5").html((json.maxWeight/1000).toFixed(2));

            $("#leftTable45").html('');
            $("#leftTable45").html(json.stationNums);
            $("#leftTable4fir5").html('');
            $("#leftTable4fir5").html(json.stationNums);
            $("#lastZuiDaCheZhong5").html('')
            $("#lastZuiDaCheZhong5").html((json.maxWeight/1000).toFixed(2))
            homePageInit.linkStationNames = json.stationNames.split(",");
            homePageInit.links = json.links.split(",");
        }
    });
}


homePageInit.initFirTongji5= () =>{
    $.ajax({
        type: 'POST',
        url: '/tWimMsg/getCheLiuLiangEchartsList2',
        dataType: 'json',
        data: {
            stationPorts: homePageInit.stationPort.toString(),
            limit:6
        },
        error: function (msg) {
        },
        success: function (json) {
            homePageInit.stationName = json.stationNames.split(",")
            homePageInit.nums = json.nums.split(",");
            initFirLiuliangtongji(homePageInit.stationName, homePageInit.nums)

        }
    });
}


homePageInit.initFirqstjt5 = (id) => {
    $.ajax({
        type: 'POST',
        url: '/tWimMsg/getFirChaoZaiLv2',
        dataType: 'json',
        data: {
            stationPorts: homePageInit.stationPort.toString(),
            limit:6
        },
        error: function (msg) {
        },
        success: function (json) {
            //初始化超载率统计
            if(json!=null){
                var stationNames=[];
                var numsBili=[]
                for(var i=0;i<json.length;i++){
                    stationNames.push(json[i].stationNames)
                    numsBili.push(json[i].numsBili==undefined?"0%":((json[i].numsBili)*100).toFixed(2)+"%")
                }
                for(let i=0;i<numsBili.length;i++){
                    let num = numsBili[i];
                    $("#Five_chaozailv"+i+"").html("")
                    $("#Five_chaozailv"+i+"").html(num)
                    $("#Five_chaozailvName"+i+"").html("")
                    $("#Five_chaozailvName"+i+"").html(stationNames[i])
                }
            }

        }
    });
    $.ajax({
        type: 'POST',
        url: '/tWimMsg/getQushitu2',
        dataType: 'json',
        data: {
            stationPorts: homePageInit.stationPort.toString(),
        },
        error: function (msg) {
        },
        success: function (json) {
            //初始化超载率统计
            var dateArr=[];
            var liuliangArr=[]
            var chaizaiArr=[];
            for(var i=0;i<json.length;i++){
                dateArr.push(json[i].avgTime)
                liuliangArr.push(json[i].zongliuliangnum)
                chaizaiArr.push(json[i].zongchaozainum)
            }

            homePageInit.initFirqstjtEcharts5(id,dateArr,chaizaiArr, liuliangArr );

        }
    });
}


homePageInit.initFirqstjtEcharts5 = (id,stationNames,nums,numCount) => {
    // 趋势统计图
    let myChart = echarts.init(document.getElementById(id));

    let colors = ['#00f1b5', '#fd2b2a'];


    option = {
        color: colors,
        textStyle: {
            color: '#fff',
            fontSize: 13
        },
        tooltip: {
            trigger: 'none',
            axisPointer: {
                type: 'cross'
            }
        },
        legend: {
            data:['总流量', '超载量'],
            textStyle: {
                color: '#A3DCEC',
                fontSize: 13
            },
        },
        grid: {
            top: 70,
            bottom: 50
        },
        xAxis: [
            {
                type: 'category',
                axisTick: {
                    alignWithLabel: true
                },
                axisLine: { //y轴
                    show: false
                },
                splitLine: {
                    show: false
                },
                axisPointer: {
                    label: {
                        formatter: function (params) {
                            return '总流量  ' + params.value
                                + (params.seriesData.length ? '：' + params.seriesData[0].data : '');
                        }
                    }
                },
                data: stationNames,
                axisLabel : {//坐标轴刻度标签的相关设置。
                    interval:0,
                    //rotate:"45" //文字倾斜
                    formatter : function(params) { //文字换行
                        let newParamsName = "";// 最终拼接成的字符串
                        let paramsNameNumber = params.length;// 实际标签的个数
                        let provideNumber = 5;// 每行能显示的字的个数
                        let rowNumber = Math.ceil(paramsNameNumber / provideNumber);// 换行的话，需要显示几行，向上取整
                        /**
                         * 判断标签的个数是否大于规定的个数， 如果大于，则进行换行处理 如果不大于，即等于或小于，就返回原标签
                         */
                        // 条件等同于rowNumber>1
                        if (paramsNameNumber > provideNumber) {
                            /** 循环每一行,p表示行 */
                            for (let p = 0; p < rowNumber; p++) {
                                let tempStr = "";// 表示每一次截取的字符串
                                let start = p * provideNumber;// 开始截取的位置
                                let end = start + provideNumber;// 结束截取的位置
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
            },
            {
                type: 'category',
                axisTick: {
                    alignWithLabel: true
                },
                axisLine: {
                    onZero: false,
                    lineStyle: {
                        color: colors[0]
                    }
                },
                axisLine: { //y轴
                    show: false
                },
                splitLine: {
                    show: false
                },
                axisPointer: {
                    label: {
                        formatter: function (params) {
                            return '超载量  ' + params.value
                                + (params.seriesData.length ? '：' + params.seriesData[0].data : '');
                        }
                    }
                },

            }
        ],
        yAxis: [
            {
                type: 'value',
                position: 'left',
                splitLine: {
                    show: false
                },
                axisLine: { //y轴
                    show: false
                },
                splitLine: {
                    show: false
                },
            }
        ],
        series: [
            {
                name: '总流量',
                type: 'line',
                xAxisIndex: 1,
                smooth: true,
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                data: numCount
            },
            {
                name: '超载量',
                type: 'line',
                smooth: true,
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                data: nums
            }
        ]
    };


    myChart.setOption(option)
}