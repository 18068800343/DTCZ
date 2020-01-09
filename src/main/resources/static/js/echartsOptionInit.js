// 基于准备好的dom，初始化echarts实例

let czaicl = echarts.init(document.getElementById('czaicl'));
//		app.title = '世界人口总量 - 条形图';

let optionCZAICL = {

    title: {},
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
        data: ['S65苏皖省界', 'G30苏皖省界', 'S69济徐苏鲁省界', 'G3京福苏鲁省界', 'G25临连苏鲁省界', 'G15苏鲁省界']
    },

    series: [{
        name: '2011年',
        type: 'bar',
        data: [367, 213, 253, 353, 448, 270],
        barWidth: 20,
        itemStyle: {
            normal: {
                color: '#91FD63'
            }
        },

    },]
};

// 使用刚指定的配置项和数据显示图表。
czaicl.setOption(optionCZAICL);

<!--饼状图-->
let czhongcl = echarts.init(document.getElementById('czhongcl'));

let optionCzhongcl = {
    title: {
        x: 'center'
    },
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        right: 'right',
        data: ['S65苏皖省界', 'G30苏皖省界', 'S69济徐苏鲁省界', 'G3京福苏鲁省界', 'G25临连苏鲁省界', 'G15苏鲁省界'],
        color: '#FFFFFF',
    },
    series: [{
        name: '访问来源',
        type: 'pie',
        radius: '55%',
        center: ['50%', '60%'],
        color: ['#06d3c4', '#ffbb32', '#2ccc44', "#0278fe", "#FF3976", "#6058E1", "#e569ff", "#00d6fb"],
        data: [{
            value: 335,
            name: 'S65苏皖省界'
        },
            {
                value: 310,
                name: 'G30苏皖省界'
            },
            {
                value: 234,
                name: 'S69济徐苏鲁省界'
            },
            {
                value: 135,
                name: 'G3京福苏鲁省界'
            },
            {
                value: 200,
                name: 'G25临连苏鲁省界'
            },
            {
                value: 120,
                name: 'G15苏鲁省界'
            }

        ],
        itemStyle: {
            emphasis: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
        }
    }]
};
// 使用刚指定的配置项和数据显示图表。
czhongcl.setOption(optionCzhongcl);

let sxczaicl = echarts.init(document.getElementById('sxczaicl'));
//		app.title = '世界人口总量 - 条形图';

let optionSxczaicl = {

    title: {},
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
        data: ['S65苏皖省界', 'G30苏皖省界', 'S69济徐苏鲁省界', 'G3京福苏鲁省界', 'G25临连苏鲁省界', 'G15苏鲁省界']
    },

    series: [{
        name: '2011年',
        type: 'bar',
        data: [367, 213, 253, 353, 448, 270],
        barWidth: 20,
        itemStyle: {
            normal: {
                color: '#91FD63'
            }
        },

    },]
};

// 使用刚指定的配置项和数据显示图表。
sxczaicl.setOption(optionSxczaicl);

<!--饼状图-->
let myChart5 = echarts.init(document.getElementById('_right1'));

let option5 = {
    title: {
        x: 'center'
    },
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        right: 'right',
        data: ['S65苏皖省界', 'G30苏皖省界', 'S69济徐苏鲁省界', 'G3京福苏鲁省界', 'G25临连苏鲁省界', 'G15苏鲁省界'],
        color: '#FFFFFF',
    },
    series: [{
        name: '访问来源',
        type: 'pie',
        radius: '55%',
        center: ['50%', '60%'],
        color: ['#06d3c4', '#ffbb32', '#2ccc44', "#0278fe", "#FF3976", "#6058E1", "#e569ff", "#00d6fb"],
        data: [{
            value: 335,
            name: 'S65苏皖省界'
        },
            {
                value: 310,
                name: 'G30苏皖省界'
            },
            {
                value: 234,
                name: 'S69济徐苏鲁省界'
            },
            {
                value: 135,
                name: 'G3京福苏鲁省界'
            },
            {
                value: 200,
                name: 'G25临连苏鲁省界'
            },
            {
                value: 120,
                name: 'G15苏鲁省界'
            }

        ],
        itemStyle: {
            emphasis: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
        }
    }]
};
// 使用刚指定的配置项和数据显示图表。
myChart5.setOption(option5);

let myChart3 = echarts.init(document.getElementById('_bottom'));
let option3 = {
    color: ['#29DC93'],
    tooltip: {
        trigger: 'axis',
        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
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
            data: ['G15', 'G25', 'G3', 'G69', 'G30', 'S65', 'G1516', 'G36', 'S38', 'S68', , 'S50', 'G2', 'G40'],
            axisTick: {
                alignWithLabel: true
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
            name: '直接访问',
            type: 'bar',
            barWidth: '20%',
            data: [10, 52, 200, 334, 390, 330, 220, 192, 83, 29, 352, 201, 102, 91]
        }
    ]
};
// 使用刚指定的配置项和数据显示图表。
myChart3.setOption(option3);