let dayAndMonthTongjiDom={};

dayAndMonthTongjiDom.initTable = () => {
    $("#dataTable").append("<div id='title'><ul   class='nav nav-tabs pull-left'>"
        + " <li class='active'><a href='#s1' data-toggle='tab' id='tab1'>交通量</a></li>"
        + " <li><a href='#s2' data-toggle='tab' id='tab2'>超载车辆数</a></li>"
        + " <li><a href='#s3' data-toggle='tab' id='tab3'>平均超载率</a></li>"
        + " <li ><a href='#s4' data-toggle='tab' id='tab4'>超载比例(%)</a></li>"
        + " <li ><a href='#s5' data-toggle='tab' id='tab5'>最大车重(KG)</a></li>"
        + "</ul></div>"
        + "<div id='myTabContent1' class='tab-content bg-color-white no-padding' style='overflow-x: auto; overflow-y: auto; height:450px; width:1040px;'>"
        + " <div class='tab-pane fade in active' id='s1'>"
        + " <table id='myTabOne1' data-type='交通量' class='table table-bordered table-hover' border='2'  bordercolor='black' cellspacing='0' cellpadding='0'></table></div>"
        + "<div class='tab-pane fade in' id='s2'><table id='myTabOne2' data-type='超载车辆数'  class='table table-bordered table-hover' border='2'  bordercolor='black' cellspacing='0' cellpadding='0'></table></div>"
        + "<div class='tab-pane fade in' id='s3'><table id='myTabOne3' data-type='平均超载率'  class='table table-bordered table-hover' border='2' bordercolor='black' cellspacing='0' cellpadding='0'></table></div>"
        + " <div class='tab-pane fade in' id='s4'>"
        + " <table id='myTabOne4' data-type='超载比例' class='table table-bordered table-hover' border='2'  bordercolor='black' cellspacing='0' cellpadding='0'></table></div>"
        + "  <div class='tab-pane fade in' id='s5'><table id='myTabOne5' data-type='最大车重' class='table table-bordered table-hover' border='2' bordercolor='black' cellspacing='0' cellpadding='0'></table></div>"
        + " </div>")
}

dayAndMonthTongjiDom.initTableBodyAjax = (stationIp,avgTime) => {
    $.ajax({
        type: 'POST',
        url: '/tAvgDay/gettAvgDayColumnByIpAndTime',
        dataType: 'json',
        data: {
            stationIP: stationIp,
            avgTime: avgTime,
            avgLaneNo: 0
        },
        error: function (msg) {
        },
        success: function (json) {
            initTableTbody(json)
        }
    });
}
let initTableTbody = (json) => {
    for (let i = 1; i <= 5; i++) {
        if (i == 5) {
            let tableDom = $("#myTabOne" + i).html('');
            tableDom.append("<thead>" + "</thead>" + "<tfoot>" +
                "<tr align='center'>" +
                "<td>" + "车道编号" + "</td>" +
                "<td>" + "车道名" + "</td>" +
                "<td>最大车重</td>" +
                "</tr>" +
                +
                    "</tfoot>");
            for (let n = 1; n <= json.length; n++) {
                if (n == json.length) {
                    //车道0即总计的一行的td
                    let tdDom = "";
                    tdDom =  "<td data-type='column'><a>" + json[1].avgMax + "</a></td>";
                    let dom = tableDom.find("tfoot");
                    $(dom).append("<tr align='center'>" +
                        "<td colspan='2'>" + formatLaneName(json[0].avgLaneNo) + "</td>" +
                        tdDom +
                        "</tr>")
                    break;
                }
                let tdDom = "";
                tdDom = "<td data-type='column'><a>" + json[n].avgMax + "</a></td>";
                let dom = tableDom.find("tfoot");
                $(dom).append("<tr align='center'>" +
                    "<td>" + json[n].avgLaneNo + "</td>" +
                    "<td>" + formatLaneName(json[n].avgLaneNo) + "</td>" +
                    tdDom +
                    "</tr>")
            }
        } else {
            let tableDom = $("#myTabOne" + i).html('');
            let k = 5 * (i) + i - 1;
            tableDom.append("<thead>" + "</thead>" + "<tfoot>" +
                "<tr align='center'>" +
                "<td>" + "车道编号" + "</td>" +
                "<td>" + "车道名" + "</td>" +
                "<td>" + "2轴车" + "</td>" +
                "<td>" + "3轴车" + "</td>" +
                "<td>" + "4轴车" + "</td>" +
                "<td>" + "5轴车" + "</td>" +
                "<td>" + "6轴车及以上" + "</td>" +
                "<td>" + "合计" + "</td>" +
                "</tr>" +
                +
                    "</tfoot>");
            for (let n = 1; n <= json.length; n++) {
                if (n == json.length) {
                    //车道0即总计的一行的td
                    let tdDom = "";
                    for (let j = k + 1; j <= k + 5; j++) {
                        tdDom = tdDom + "<td data-type='column'><a>" + json[1]['column' + j] + "</a></td>";
                    }
                    tdDom = tdDom + "<td data-type='column'><a>" + json[1]['column' + k] + "</a></td>";
                    let dom = tableDom.find("tfoot");
                    $(dom).append("<tr align='center'>" +
                        "<td colspan='2'>" + formatLaneName(json[0].avgLaneNo) + "</td>" +
                        tdDom +
                        "</tr>")
                    break;
                }
                let tdDom = "";
                for (let j = k + 1; j <= k + 5; j++) {
                    tdDom = tdDom + "<td data-type='column'+j+'><a>" + json[n]['column' + j] + "</a></td>";
                }
                tdDom = tdDom + "<td data-type='column'><a>" + json[n]['column' + k] + "</a></td>";
                let dom = tableDom.find("tfoot");
                $(dom).append("<tr align='center'>" +
                    "<td>" + json[n].avgLaneNo + "</td>" +
                    "<td>" + formatLaneName(json[n].avgLaneNo) + "</td>" +
                    tdDom +
                    "</tr>")

            }


        }
    }
}

let formatLaneName = (e) => {
    if (e == '0') {
        return '合计';
    } else if (e == '1') {
        return '停车道';
    } else if (e == '2') {
        return '车道2';
    } else if (e == '3') {
        return '车道1';
    } else {
        return '车道';
    }
}