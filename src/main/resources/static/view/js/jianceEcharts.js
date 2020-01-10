function setjiancezhandianLeft(list,stationName){
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
    var option = {
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
            data: /*['S65苏皖省界', 'G30苏皖省界', 'S69济徐苏鲁省界', 'G3京福苏鲁省界', 'G25临连苏鲁省界', 'G15苏鲁省界']*/stationName,
            color: '#FFFFFF',

        },
        series: [{
            name: '超载车辆',
            type: 'pie',
            radius: '55%',
            center: ['50%', '60%'],
            color: ['#06d3c4', '#ffbb32', '#2ccc44', "#0278fe", "#FF3976", "#6058E1", "#e569ff", "#00d6fb"],
            data:list,
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }]
    };
    return option;
}