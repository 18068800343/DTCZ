let dayAndMonthTongjiDom_sj = {};
let dayAndMonthTongjiDom_sj_week = {};

dayAndMonthTongjiDom_sj.initTable = () => {
    $("#dataTable_sj").append("<div id='title'><ul   class='nav nav-tabs pull-left'>"
        + " <li class='active'><a href='#s1' data-toggle='tab' id='tab1_sj'>交通量</a></li>"
        + " <li><a href='#s2_sj' data-toggle='tab' id='tab2_sj'>超载车辆数</a></li>"
        + " <li><a href='#s3_sj' data-toggle='tab' id='tab3_sj'>平均超载率(%)</a></li>"
        + " <li ><a href='#s4_sj' data-toggle='tab' id='tab4_sj'>超载比例(%)</a></li>"
        + " <li ><a href='#s5_sj' data-toggle='tab' id='tab5_sj'>最大车重(kg)</a></li>"
        + "</ul></div>"
        + "<div id='myTabContent1_sj' class='tab-content bg-color-white no-padding' style='overflow-x: auto; overflow-y: auto; height:450px; width:1040px;'>"
        + " <div class='tab-pane fade in active' id='s1'>"
        + " <table id='myTabOne1_sj' data-type='交通量' class='table table-bordered table-hover' border='2'  bordercolor='black' cellspacing='0' cellpadding='0'></table></div>"
        + "<div class='tab-pane fade in' id='s2_sj'><table id='myTabOne2_sj' data-type='超载车辆数'  class='table table-bordered table-hover' border='2'  bordercolor='black' cellspacing='0' cellpadding='0'></table></div>"
        + "<div class='tab-pane fade in' id='s3_sj'><table id='myTabOne3_sj' data-type='平均超载率'  class='table table-bordered table-hover' border='2' bordercolor='black' cellspacing='0' cellpadding='0'></table></div>"
        + " <div class='tab-pane fade in' id='s4_sj'>"
        + " <table id='myTabOne4_sj' data-type='超载比例' class='table table-bordered table-hover' border='2'  bordercolor='black' cellspacing='0' cellpadding='0'></table></div>"
        + "  <div class='tab-pane fade in' id='s5_sj'><table id='myTabOne5_sj' data-type='最大车重' class='table table-bordered table-hover' border='2' bordercolor='black' cellspacing='0' cellpadding='0'></table></div>"
        + " </div>")
}

dayAndMonthTongjiDom_sj_week.initTable = () => {
    $("#dataTable_sj_week").append("<div id='title'><ul   class='nav nav-tabs pull-left'>"
        + " <li class='active'><a href='#s1_week' data-toggle='tab' id='tab1_sj_week'>交通量</a></li>"
        + " <li><a href='#s2_sj_week' data-toggle='tab' id='tab2_sj_week'>超载车辆数</a></li>"
        + " <li><a href='#s3_sj_week' data-toggle='tab' id='tab3_sj_week'>平均超载率(%)</a></li>"
        + " <li ><a href='#s4_sj_week' data-toggle='tab' id='tab4_sj_week'>超载比例(%)</a></li>"
        + " <li ><a href='#s5_sj_week' data-toggle='tab' id='tab5_sj_week'>最大车重(kg)</a></li>"
        + "</ul></div>"
        + "<div id='myTabContent1_sj_week' class='tab-content bg-color-white no-padding' style='overflow-x: auto; overflow-y: auto; height:450px; width:1040px;'>"
        + " <div class='tab-pane fade in active' id='s1_week'>"
        + " <table id='myTabOne1_sj_week' data-type='交通量' class='table table-bordered table-hover' border='2'  bordercolor='black' cellspacing='0' cellpadding='0'></table></div>"
        + "<div class='tab-pane fade in' id='s2_sj_week'><table id='myTabOne2_sj_week' data-type='超载车辆数'  class='table table-bordered table-hover' border='2'  bordercolor='black' cellspacing='0' cellpadding='0'></table></div>"
        + "<div class='tab-pane fade in' id='s3_sj_week'><table id='myTabOne3_sj_week' data-type='平均超载率'  class='table table-bordered table-hover' border='2' bordercolor='black' cellspacing='0' cellpadding='0'></table></div>"
        + " <div class='tab-pane fade in' id='s4_sj_week'>"
        + " <table id='myTabOne4_sj_week' data-type='超载比例' class='table table-bordered table-hover' border='2'  bordercolor='black' cellspacing='0' cellpadding='0'></table></div>"
        + "  <div class='tab-pane fade in' id='s5_sj_week'><table id='myTabOne5_sj_week' data-type='最大车重' class='table table-bordered table-hover' border='2' bordercolor='black' cellspacing='0' cellpadding='0'></table></div>"
        + " </div>")
}

dayAndMonthTongjiDom_sj.initTableBodyAjax = (stationIp, avgTime) => {
    $.ajax({
        type: 'POST',
        url: '/tAvgDay/gettAvgDayColumnByCompanyNameAndTime_sj',
        dataType: 'json',
        data: {
            provinceName: stationIp,
            avgTime: avgTime,
            avgLaneNo: 0
        },
        error: function (msg) {
        },
        success: function (json) {
            initTableTbody_sj(json)
            dayAndMonthTongjiDom_sj.bindClick(stationIp);
        }
    });
}

dayAndMonthTongjiDom_sj_week.initTableBodyAjax = (stationIp, avgTime) => {
    $.ajax({
        type: 'POST',
        url: '/tAvgDay/gettAvgDayColumnByCompanyNameAndTime_sj_week',
        dataType: 'json',
        data: {
            provinceName: stationIp,
            avgTime: avgTime,
            avgLaneNo: 0
        },
        error: function (msg) {
        },
        success: function (json) {
            if (json != null && json != undefined) {
                initTableTbody_sj_week(json)
                dayAndMonthTongjiDom_sj_week.bindClick(stationIp);
            }
        }
    });
}

let initTableTbody_sj = (json) => {
    for (let i = 1; i <= 5; i++) {
        if (i == 5) {
            let tableDom = $("#myTabOne" + i + "_sj").html('');
            tableDom.append("<thead>" + "</thead>" + "<tfoot>" +
                "<tr align='center'>" +
                // "<td>" + "车道图" + "</td>" +
                // "<td>" + "车道编号" + "</td>" +
                //"<td>" + "车道名" + "</td>" +
                "<td>最大车重</td>" +
                "</tr>" +
                +
                    "</tfoot>");
            // let Strack3=true;
            for (let n = 1; n <= 1; n++) {
                // let img;
                // if (n == json.length) {
                //车道0即总计的一行的td
                let tdDom = "";
                tdDom = "<td data-id='" + json[0].avgLaneNo + ",4' data-key='" + json[0].avgMaxID + "'><a >" + json[0].avgMax + "</a></td>";
                let dom = tableDom.find("tfoot");
                // if(Strack3){
                //     img="<td width='20%' rowspan='"+(json.length)+"'>" + getimgUrl_sj(1) + "</td>" ;
                //     Strack3=false;
                // }
                $(dom).append("<tr align='center'>" +
                    // img+
                    // "<td>合计</td>" +
                    tdDom +
                    "</tr>")
                break;
                // }
                // let tdDom = "";
                // tdDom = "<td data-id='"+json[1].avgLaneNo+",4' data-key='"+json[n].avgMaxID+"'><a >" + json[n].avgMax + "</a></td>";
                // let dom = tableDom.find("tfoot");
                // if(Strack3){
                //     img = "<td width='20%' rowspan='" + (json.length) + "'>" + getimgUrl_sj(1) + "</td>";
                //     Strack3 = false;
                // }
                // $(dom).append("<tr align='center'>" +
                //     img +
                //     "<td>" + json[n].avgLaneNo + "</td>" +
                //     /* "<td>" + formatLaneName(json[n].avgLaneNo) + "</td>" +*/
                //     tdDom +
                //     "</tr>")
            }
        } else if (i == 3) {
            let tableDom = $("#myTabOne" + i + "_sj").html('');
            let k = 5 * (i) + i - 1;
            tableDom.append("<thead>" + "</thead>" + "<tfoot>" +
                "<tr align='center'>" +
                // "<td>" + "车道图" + "</td>" +
                // "<td>" + "车道编号" + "</td>" +
                //"<td>" + "车道名" + "</td>" +
                "<td>" + "2轴车" + "</td>" +
                "<td>" + "3轴车" + "</td>" +
                "<td>" + "4轴车" + "</td>" +
                "<td>" + "5轴车" + "</td>" +
                "<td>" + "6轴车及以上" + "</td>" +
                "</tr>" +
                +
                    "</tfoot>");
            // var Strack2=true;
            for (let n = 1; n <= 1; n++) {
                let img;
                // if (n == json.length) {
                //车道0即总计的一行的td
                let tdDom = "";
                for (let j = k + 1; j <= k + 4; j++) {
                    tdDom = tdDom + "<td data-id='" + json[0].avgLaneNo + "," + j + "'><a >" + json[0]['column' + j].toFixed(2) + "%</a></td>";
                }
                tdDom = tdDom + "<td data-id='" + json[0].avgLaneNo + "," + k + "'><a >" + json[0]['column' + k].toFixed(2) + "%</a></td>";
                let dom = tableDom.find("tfoot");
                // if(Strack2){
                //     img="<td width='20%' rowspan='"+(json.length)+"'>" + getimgUrl_sj(1) + "</td>" ;
                //     Strack2=false;
                // }
                $(dom).append("<tr align='center'>" +
                    // img+
                    // "<td>合计</td>" +
                    tdDom +
                    "</tr>")
                break;
                // }
                // let tdDom = "";
                // for (let j = k + 1; j <= k + 4; j++) {
                //     tdDom = tdDom + "<td data-id='" + json[n].avgLaneNo + "," + j + "'><a >" + json[n]['column' + j].toFixed(2) + "%</a></td>";
                // }
                // tdDom = tdDom + "<td data-id='"+json[n].avgLaneNo+","+k+"'><a >" + json[n]['column' + k].toFixed(2) + "%</a></td>";
                // let dom = tableDom.find("tfoot");
                // if (Strack2) {
                //     img = "<td width='20%' rowspan='" + (json.length) + "'>" + getimgUrl_sj(1) + "</td>";
                //     Strack2 = false;
                // }
                // $(dom).append("<tr align='center'>" +
                //     img +
                //     "<td>" + json[n].avgLaneNo + "</td>" +
                //     //"<td>" + formatLaneName(json[n].avgLaneNo) + "</td>" +
                //     tdDom +
                //     "</tr>")

            }


        } else if (i == 4) {
            let tableDom = $("#myTabOne" + i + "_sj").html('');
            let k = 5 * (i) + i - 1;
            tableDom.append("<thead>" + "</thead>" + "<tfoot>" +
                "<tr align='center'>" +
                // "<td>" + "车道图" + "</td>" +
                // "<td>" + "车道编号" + "</td>" +
                //"<td>" + "车道名" + "</td>" +
                "<td>" + "2轴车" + "</td>" +
                "<td>" + "3轴车" + "</td>" +
                "<td>" + "4轴车" + "</td>" +
                "<td>" + "5轴车" + "</td>" +
                "<td>" + "6轴车及以上" + "</td>" +
                "<td>" + "合计" + "</td>" +
                "</tr>" +
                +
                    "</tfoot>");
            // var Strack2 = true;
            for (let n = 1; n <= 1; n++) {
                // let img;
                // if (n == json.length) {
                //车道0即总计的一行的td
                let tdDom = "";
                for (let j = k + 1; j <= k + 5; j++) {
                    tdDom = tdDom + "<td data-id='" + json[0].avgLaneNo + "," + j + "'><a >" + json[0]['column' + j].toFixed(2) + "%</a></td>";
                }
                tdDom = tdDom + "<td data-id='" + json[0].avgLaneNo + "," + k + "'><a >" + json[0]['column' + k].toFixed(2) + "%</a></td>";
                let dom = tableDom.find("tfoot");
                // if (Strack2) {
                //     img = "<td width='20%' rowspan='" + (json.length) + "'>" + getimgUrl_sj(1) + "</td>";
                //     Strack2 = false;
                // }
                $(dom).append("<tr align='center'>" +
                    // img +
                    // "<td>合计</td>" +
                    tdDom +
                    "</tr>")
                break;
                // }
                // let tdDom = "";
                // for (let j = k + 1; j <= k + 5; j++) {
                //     tdDom = tdDom + "<td data-id='" + json[n].avgLaneNo + "," + j + "'><a >" + json[n]['column' + j].toFixed(2) + "%</a></td>";
                // }
                // tdDom = tdDom + "<td data-id='" + json[n].avgLaneNo + "," + k + "'><a >" + json[n]['column' + k].toFixed(2) + "%</a></td>";
                // let dom = tableDom.find("tfoot");
                // if (Strack2) {
                //     img = "<td width='20%' rowspan='" + (json.length) + "'>" + getimgUrl_sj(1) + "</td>";
                //     Strack2 = false;
                // }
                // $(dom).append("<tr align='center'>" +
                //     img +
                //     "<td>" + json[n].avgLaneNo + "</td>" +
                //     //"<td>" + formatLaneName(json[n].avgLaneNo) + "</td>" +
                //     tdDom +
                //     "</tr>")

            }


        } else {
            let tableDom = $("#myTabOne" + i + "_sj").html('');
            let k = 5 * (i) + i - 1;
            tableDom.append("<thead>" + "</thead>" + "<tfoot>" +
                "<tr align='center'>" +
                // "<td>" + "车道图" + "</td>" +
                // "<td>" + "车道编号" + "</td>" +
                //"<td>" + "车道名" + "</td>" +
                "<td>" + "2轴车" + "</td>" +
                "<td>" + "3轴车" + "</td>" +
                "<td>" + "4轴车" + "</td>" +
                "<td>" + "5轴车" + "</td>" +
                "<td>" + "6轴车及以上" + "</td>" +
                "<td>" + "合计" + "</td>" +
                "</tr>" +
                +
                    "</tfoot>");
            // let Strack=true;
            for (let n = 1; n <= 1; n++) {
                // let url=getimgUrl_sj(json[0].stationIP);
                let img;
                // if (n == json.length) {
                //车道0即总计的一行的td
                let tdDom = "";
                for (let j = k + 1; j <= k + 5; j++) {
                    tdDom = tdDom + "<td data-id='" + json[0].avgLaneNo + "," + j + "'><a >" + json[0]['column' + j] + "</a></td>";
                }
                tdDom = tdDom + "<td data-id='" + json[0].avgLaneNo + "," + k + "'><a >" + json[0]['column' + k] + "</a></td>";
                let dom = tableDom.find("tfoot");
                // if(Strack){
                //     img="<td width='20%' rowspan='"+(json.length)+"'>" + getimgUrl_sj(1) + "</td>" ;
                //     Strack=false;
                // }
                $(dom).append("<tr align='center'>" +
                    // img+
                    // "<td>合计</td>" +
                    tdDom +
                    "</tr>")
                break;
                // }
                // let tdDom = "";
                // for (let j = k + 1; j <= k + 5; j++) {
                //     tdDom = tdDom + "<td data-id='"+json[n].avgLaneNo+","+j+"'><a >" + json[n]['column' + j] + "</a></td>";
                // }
                // tdDom = tdDom + "<td data-id='"+json[n].avgLaneNo+","+k+"'><a >" + json[n]['column' + k] + "</a></td>";
                // let dom = tableDom.find("tfoot");
                // if(Strack){
                //     img="<td width='20%' rowspan='"+(json.length)+"'>" + getimgUrl_sj(1) + "</td>" ;
                //     Strack=false;
                // }
                // $(dom).append("<tr align='center'>" +
                //     img+
                //     "<td>" + json[n].avgLaneNo + "</td>" +
                //     //"<td>" + formatLaneName(json[n].avgLaneNo) + "</td>" +
                //     tdDom +
                //     "</tr>")

            }


        }
    }
}

let initTableTbody_sj_week = (json) => {
    for (let i = 1; i <= 5; i++) {
        if (i == 5) {
            let tableDom = $("#myTabOne" + i + "_sj" + "_week").html('');
            tableDom.append("<thead>" + "</thead>" + "<tfoot>" +
                "<tr align='center'>" +
                // "<td>" + "车道图" + "</td>" +
                // "<td>" + "车道编号" + "</td>" +
                //"<td>" + "车道名" + "</td>" +
                "<td>最大车重</td>" +
                "</tr>" +
                +
                    "</tfoot>");
            // let Strack3=true;
            for (let n = 1; n <= 1; n++) {
                // let img;
                // if (n == json.length) {
                //车道0即总计的一行的td
                let tdDom = "";
                tdDom = "<td data-id='" + json[0].avgLaneNo + ",4' data-key='" + json[0].avgMaxID + "'><a >" + json[0].avgMax + "</a></td>";
                let dom = tableDom.find("tfoot");
                // if(Strack3){
                //     img="<td width='20%' rowspan='"+(json.length)+"'>" + getimgUrl_sj(1) + "</td>" ;
                //     Strack3=false;
                // }
                $(dom).append("<tr align='center'>" +
                    // img+
                    // "<td>合计</td>" +
                    tdDom +
                    "</tr>")
                break;
                // }
                // let tdDom = "";
                // tdDom = "<td data-id='"+json[1].avgLaneNo+",4' data-key='"+json[n].avgMaxID+"'><a >" + json[n].avgMax + "</a></td>";
                // let dom = tableDom.find("tfoot");
                // if(Strack3){
                //     img = "<td width='20%' rowspan='" + (json.length) + "'>" + getimgUrl_sj(1) + "</td>";
                //     Strack3 = false;
                // }
                // $(dom).append("<tr align='center'>" +
                //     img +
                //     "<td>" + json[n].avgLaneNo + "</td>" +
                //     /* "<td>" + formatLaneName(json[n].avgLaneNo) + "</td>" +*/
                //     tdDom +
                //     "</tr>")
            }
        } else if (i == 3) {
            let tableDom = $("#myTabOne" + i + "_sj" + "_week").html('');
            let k = 5 * (i) + i - 1;
            tableDom.append("<thead>" + "</thead>" + "<tfoot>" +
                "<tr align='center'>" +
                // "<td>" + "车道图" + "</td>" +
                // "<td>" + "车道编号" + "</td>" +
                //"<td>" + "车道名" + "</td>" +
                "<td>" + "2轴车" + "</td>" +
                "<td>" + "3轴车" + "</td>" +
                "<td>" + "4轴车" + "</td>" +
                "<td>" + "5轴车" + "</td>" +
                "<td>" + "6轴车及以上" + "</td>" +
                "</tr>" +
                +
                    "</tfoot>");
            // var Strack2=true;
            for (let n = 1; n <= 1; n++) {
                let img;
                // if (n == json.length) {
                //车道0即总计的一行的td
                let tdDom = "";
                for (let j = k + 1; j <= k + 4; j++) {
                    tdDom = tdDom + "<td data-id='" + json[0].avgLaneNo + "," + j + "'><a >" + json[0]['column' + j].toFixed(2) + "%</a></td>";
                }
                tdDom = tdDom + "<td data-id='" + json[0].avgLaneNo + "," + k + "'><a >" + json[0]['column' + k].toFixed(2) + "%</a></td>";
                let dom = tableDom.find("tfoot");
                // if(Strack2){
                //     img="<td width='20%' rowspan='"+(json.length)+"'>" + getimgUrl_sj(1) + "</td>" ;
                //     Strack2=false;
                // }
                $(dom).append("<tr align='center'>" +
                    // img+
                    // "<td>合计</td>" +
                    tdDom +
                    "</tr>")
                break;
                // }
                // let tdDom = "";
                // for (let j = k + 1; j <= k + 4; j++) {
                //     tdDom = tdDom + "<td data-id='" + json[n].avgLaneNo + "," + j + "'><a >" + json[n]['column' + j].toFixed(2) + "%</a></td>";
                // }
                // tdDom = tdDom + "<td data-id='"+json[n].avgLaneNo+","+k+"'><a >" + json[n]['column' + k].toFixed(2) + "%</a></td>";
                // let dom = tableDom.find("tfoot");
                // if (Strack2) {
                //     img = "<td width='20%' rowspan='" + (json.length) + "'>" + getimgUrl_sj(1) + "</td>";
                //     Strack2 = false;
                // }
                // $(dom).append("<tr align='center'>" +
                //     img +
                //     "<td>" + json[n].avgLaneNo + "</td>" +
                //     //"<td>" + formatLaneName(json[n].avgLaneNo) + "</td>" +
                //     tdDom +
                //     "</tr>")

            }


        } else if (i == 4) {
            let tableDom = $("#myTabOne" + i + "_sj" + "_week").html('');
            let k = 5 * (i) + i - 1;
            tableDom.append("<thead>" + "</thead>" + "<tfoot>" +
                "<tr align='center'>" +
                // "<td>" + "车道图" + "</td>" +
                // "<td>" + "车道编号" + "</td>" +
                //"<td>" + "车道名" + "</td>" +
                "<td>" + "2轴车" + "</td>" +
                "<td>" + "3轴车" + "</td>" +
                "<td>" + "4轴车" + "</td>" +
                "<td>" + "5轴车" + "</td>" +
                "<td>" + "6轴车及以上" + "</td>" +
                "<td>" + "合计" + "</td>" +
                "</tr>" +
                +
                    "</tfoot>");
            // var Strack2 = true;
            for (let n = 1; n <= 1; n++) {
                // let img;
                // if (n == json.length) {
                //车道0即总计的一行的td
                let tdDom = "";
                for (let j = k + 1; j <= k + 5; j++) {
                    tdDom = tdDom + "<td data-id='" + json[0].avgLaneNo + "," + j + "'><a >" + json[0]['column' + j].toFixed(2) + "%</a></td>";
                }
                tdDom = tdDom + "<td data-id='" + json[0].avgLaneNo + "," + k + "'><a >" + json[0]['column' + k].toFixed(2) + "%</a></td>";
                let dom = tableDom.find("tfoot");
                // if (Strack2) {
                //     img = "<td width='20%' rowspan='" + (json.length) + "'>" + getimgUrl_sj(1) + "</td>";
                //     Strack2 = false;
                // }
                $(dom).append("<tr align='center'>" +
                    // img +
                    // "<td>合计</td>" +
                    tdDom +
                    "</tr>")
                break;
                // }
                // let tdDom = "";
                // for (let j = k + 1; j <= k + 5; j++) {
                //     tdDom = tdDom + "<td data-id='" + json[n].avgLaneNo + "," + j + "'><a >" + json[n]['column' + j].toFixed(2) + "%</a></td>";
                // }
                // tdDom = tdDom + "<td data-id='" + json[n].avgLaneNo + "," + k + "'><a >" + json[n]['column' + k].toFixed(2) + "%</a></td>";
                // let dom = tableDom.find("tfoot");
                // if (Strack2) {
                //     img = "<td width='20%' rowspan='" + (json.length) + "'>" + getimgUrl_sj(1) + "</td>";
                //     Strack2 = false;
                // }
                // $(dom).append("<tr align='center'>" +
                //     img +
                //     "<td>" + json[n].avgLaneNo + "</td>" +
                //     //"<td>" + formatLaneName(json[n].avgLaneNo) + "</td>" +
                //     tdDom +
                //     "</tr>")

            }


        } else {
            let tableDom = $("#myTabOne" + i + "_sj" + "_week").html('');
            let k = 5 * (i) + i - 1;
            tableDom.append("<thead>" + "</thead>" + "<tfoot>" +
                "<tr align='center'>" +
                // "<td>" + "车道图" + "</td>" +
                // "<td>" + "车道编号" + "</td>" +
                //"<td>" + "车道名" + "</td>" +
                "<td>" + "2轴车" + "</td>" +
                "<td>" + "3轴车" + "</td>" +
                "<td>" + "4轴车" + "</td>" +
                "<td>" + "5轴车" + "</td>" +
                "<td>" + "6轴车及以上" + "</td>" +
                "<td>" + "合计" + "</td>" +
                "</tr>" +
                +
                    "</tfoot>");
            // let Strack=true;
            for (let n = 1; n <= 1; n++) {
                // let url=getimgUrl_sj(json[0].stationIP);
                let img;
                // if (n == json.length) {
                //车道0即总计的一行的td
                let tdDom = "";
                for (let j = k + 1; j <= k + 5; j++) {
                    tdDom = tdDom + "<td data-id='" + json[0].avgLaneNo + "," + j + "'><a >" + json[0]['column' + j] + "</a></td>";
                }
                tdDom = tdDom + "<td data-id='" + json[0].avgLaneNo + "," + k + "'><a >" + json[0]['column' + k] + "</a></td>";
                let dom = tableDom.find("tfoot");
                // if(Strack){
                //     img="<td width='20%' rowspan='"+(json.length)+"'>" + getimgUrl_sj(1) + "</td>" ;
                //     Strack=false;
                // }
                $(dom).append("<tr align='center'>" +
                    // img+
                    // "<td>合计</td>" +
                    tdDom +
                    "</tr>")
                break;
                // }
                // let tdDom = "";
                // for (let j = k + 1; j <= k + 5; j++) {
                //     tdDom = tdDom + "<td data-id='"+json[n].avgLaneNo+","+j+"'><a >" + json[n]['column' + j] + "</a></td>";
                // }
                // tdDom = tdDom + "<td data-id='"+json[n].avgLaneNo+","+k+"'><a >" + json[n]['column' + k] + "</a></td>";
                // let dom = tableDom.find("tfoot");
                // if(Strack){
                //     img="<td width='20%' rowspan='"+(json.length)+"'>" + getimgUrl_sj(1) + "</td>" ;
                //     Strack=false;
                // }
                // $(dom).append("<tr align='center'>" +
                //     img+
                //     "<td>" + json[n].avgLaneNo + "</td>" +
                //     //"<td>" + formatLaneName(json[n].avgLaneNo) + "</td>" +
                //     tdDom +
                //     "</tr>")

            }


        }
    }
}
let getimgUrl_sj = (e) => {
    var stationPort;
    if (e == 1) {
        stationPort = $("#stationPort_sj").val()
    } else if (e == 2) {
        stationPort = $("#stationPort2_sj").val()
    }
    let imgUrl = ""
    $.ajax({
        type: 'POST',
        url: '/StationSite/getAccessoryByPort',
        dataType: 'json',
        async: false,
        data: {
            stationPort: stationPort,
        },
        success: function (json) {
            if (json != null && json != '' && json.length != 0) {
                for (var i = 0; i < json.length; i++) {
                    var url = getLocalPath2_sj() + json[i].acUrl;
                    imgUrl = imgUrl + '<a target="_blank" href="' + url + '" ><img  src="' + url + '" class="popup" style="max-width:300px !important;" /></a>'
                }
            } else {
                imgUrl = imgUrl + "无车道图"
            }
        }
    });
    return imgUrl;
}

let getLocalPath2_sj = () => {
    var loginUrl;
    $.ajax({
        type: 'POST',
        url: '/tVehicleOverweight/getimgUrlPrefix',
        dataType: 'json',
        async: false,
        data: {},
        success: function (json) {
            loginUrl = "https://" + json.imgUrlPrefix + "" + "/dtcz_file/"
        }
    });
    return loginUrl;
}

let formatLaneName_sj = (e) => {
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

dayAndMonthTongjiDom_sj.bindClick = (stationIp) => {
    $("#dataTable_sj").find("td").each(function () {
        let str = $(this).attr("data-id");
        if (str != undefined) {
            $(this).on('click', function () {
                let strIn = $(this).attr("data-id");
                let key = $(this).attr("data-key");
                let laneNo = strIn.split(",")[0];
                let column = strIn.split(",")[1];
                $.ajax({
                    type: 'POST',
                    url: '/tAvgDay/getEchartsListByLaneNoAndColumn_sj',
                    dataType: 'json',
                    data: {
                        provinceName: stationIp,
                        laneNo: laneNo,
                        column: column,
                    },
                    error: function (msg) {
                    },
                    success: function (json) {
                        dayAndMonthTongjiDom_sj.makeLineChart(json, column);
                        lookMsg(key, 3);
                    }
                });
            })

        }
    })
}

dayAndMonthTongjiDom_sj_week.bindClick = (stationIp) => {
    $("#dataTable_sj_week").find("td").each(function () {
        let str = $(this).attr("data-id");
        if (str != undefined) {
            $(this).on('click', function () {
                let strIn = $(this).attr("data-id");
                let key = $(this).attr("data-key");
                let laneNo = strIn.split(",")[0];
                let column = strIn.split(",")[1];
                $.ajax({
                    type: 'POST',
                    url: '/tAvgDay/getEchartsListByLaneNoAndColumn_sj_week',
                    dataType: 'json',
                    data: {
                        provinceName: stationIp,
                        laneNo: laneNo,
                        column: column,
                    },
                    error: function (msg) {
                    },
                    success: function (json) {
                        dayAndMonthTongjiDom_sj_week.makeLineChart(json, column);
                        lookMsg(key, 3);
                    }
                });
            })

        }
    })
}
dayAndMonthTongjiDom_sj.makeLineChart = (data, column) => {
    if (column == '4') {
        $("#modalEchartsDetail_sj").modal("show");
        initMyChart_sj(data, 'myChart2_sj');
    } else {
        $("#modalEcharts_sj").modal("show");
        initMyChart_sj(data, 'myChart_sj');
    }
}

dayAndMonthTongjiDom_sj_week.makeLineChart = (data, column) => {
    if (column == '4') {
        $("#modalEchartsDetail_sj_week").modal("show");
        initMyChart_sj(data, 'myChart2_sj_week');
    } else {
        $("#modalEcharts_sj_week").modal("show");
        initMyChart_sj(data, 'myChart_sj_week');
    }
}

let initMyChart_sj = (data, id) => {
    let myChart = echarts.init(document.getElementById(id));
    let option = {
        title: {
            text: ''
        },
        toolbox: {
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                restore: {},
                saveAsImage: {},
                dataView: {}
            }
        },
        tooltip: {},
        xAxis: {
            type: 'category',
            data: data.x,
            name: "日期",
            boundaryGap: false
        },
        yAxis: {
            type: 'value',
            boundaryGap: [0, '100%']
        },
        dataZoom: [{
            type: 'inside',
            start: 0,
            end: 100
        }, {
            start: 0,
            end: 100,
            handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
            handleSize: '80%',
            handleStyle: {
                color: '#fff',
                shadowBlur: 3,
                shadowColor: 'rgba(0, 0, 0, 0.6)',
                shadowOffsetX: 2,
                shadowOffsetY: 2
            }
        }],
        series: [{
            name: '',
            type: 'line',
            data: data.y
        }]
    };
    myChart.setOption(option);
}


let dayAndMonthTongjiDomMonth_sj = {};

dayAndMonthTongjiDomMonth_sj.initTableMonth = () => {
    $("#dataTableMonth_sj").append("<div id='title'><ul   class='nav nav-tabs pull-left'>"
        + " <li class='active'><a href='#s1Month_sj' data-toggle='tab' id='tab1Month_sj'>交通量</a></li>"
        + " <li><a href='#s2Month_sj' data-toggle='tab' id='tab2Month_sj'>超载车辆数</a></li>"
        + " <li><a href='#s3Month_sj' data-toggle='tab' id='tab3Month_sj'>平均超载率(%)</a></li>"
        + " <li ><a href='#s4Month_sj' data-toggle='tab' id='tab4Month_sj'>超载比例(%)</a></li>"
        + " <li ><a href='#s5Month_sj' data-toggle='tab' id='tab5Month_sj'>最大车重(kg)</a></li>"
        + "</ul></div>"
        + "<div id='myTabContent1Month_sj' class='tab-content bg-color-white no-padding' style='overflow-x: auto; overflow-y: auto; height:450px; width:1040px;'>"
        + " <div class='tab-pane fade in active' id='s1Month_sj'>"
        + " <table id='myTabOne1Month_sj' data-id='交通量' class='table table-bordered table-hover' border='2'  bordercolor='black' cellspacing='0' cellpadding='0'></table></div>"
        + "<div class='tab-pane fade in' id='s2Month_sj'><table id='myTabOne2Month_sj' data-id='超载车辆数'  class='table table-bordered table-hover' border='2'  bordercolor='black' cellspacing='0' cellpadding='0'></table></div>"
        + "<div class='tab-pane fade in' id='s3Month_sj'><table id='myTabOne3Month_sj' data-id='平均超载率'  class='table table-bordered table-hover' border='2' bordercolor='black' cellspacing='0' cellpadding='0'></table></div>"
        + " <div class='tab-pane fade in' id='s4Month_sj'>"
        + " <table id='myTabOne4Month_sj' data-id='超载比例' class='table table-bordered table-hover' border='2'  bordercolor='black' cellspacing='0' cellpadding='0'></table></div>"
        + "  <div class='tab-pane fade in' id='s5Month_sj'><table id='myTabOne5Month_sj' data-id='最大车重' class='table table-bordered table-hover' border='2' bordercolor='black' cellspacing='0' cellpadding='0'></table></div>"
        + " </div>")
}

dayAndMonthTongjiDomMonth_sj.initTableBodyAjaxMonth = (stationIp, avgTime) => {
    $.ajax({
        type: 'POST',
        url: '/tAvgDay/gettAvgDayColumnByIpAndTimeMonth_sj',
        dataType: 'json',
        data: {
            provinceName: stationIp,
            avgTime: avgTime,
            avgLaneNo: 0
        },
        error: function (msg) {
        },
        success: function (json) {
            initTableTbodyMonth_sj(json)
            dayAndMonthTongjiDomMonth_sj.bindClick(stationIp);
        }
    });
}
let initTableTbodyMonth_sj = (json) => {
    for (let i = 1; i <= 5; i++) {
        if (i == 5) {
            let tableDom = $("#myTabOne" + i + "Month" + "_sj").html('');
            tableDom.append("<thead>" + "</thead>" + "<tfoot>" +
                "<tr align='center'>" +
                // "<td>" + "车道图" + "</td>" +
                // "<td>" + "车道编号" + "</td>" +
                //"<td>" + "车道名" + "</td>" +
                "<td>最大车重</td>" +
                "</tr>" +
                +
                    "</tfoot>");
            // let Strack3=true;
            for (let n = 1; n <= 1; n++) {
                let img;
                // if (n == json.length) {
                //车道0即总计的一行的td
                let tdDom = "";
                tdDom = "<td data-id='" + json[0].avgLaneNo + ",4' data-key='" + json[0].avgMaxID + "'><a >" + json[0].avgMax + "</a></td>";
                let dom = tableDom.find("tfoot");
                // if(Strack3){
                //     img="<td width='20%' rowspan='"+(json.length)+"'>" + getimgUrl_sj(2) + "</td>" ;
                //     Strack3=false;
                // }
                $(dom).append("<tr align='center'>" +
                    // img+
                    // "<td>合计</td>" +
                    tdDom +
                    "</tr>")
                break;
                // }
                // let tdDom = "";
                // tdDom = "<td data-id='"+json[n].avgLaneNo+",4' data-key='"+json[n].avgMaxID+"'><a >" + json[n].avgMax + "</a></td>";
                // let dom = tableDom.find("tfoot");
                // if(Strack3){
                //     img = "<td width='20%' rowspan='" + (json.length) + "'>" + getimgUrl_sj(2) + "</td>";
                //     Strack3 = false;
                // }
                // $(dom).append("<tr align='center'>" +
                //     img +
                //     "<td>" + json[n].avgLaneNo + "</td>" +
                //     /*"<td>" + formatLaneName(json[n].avgLaneNo) + "</td>" +*/
                //     tdDom +
                //     "</tr>")
            }
        } else if (i == 3) {
            let tableDom = $("#myTabOne" + i + "Month" + "_sj").html('');
            let k = 5 * (i) + i - 1;
            tableDom.append("<thead>" + "</thead>" + "<tfoot>" +
                "<tr align='center'>" +
                // "<td>" + "车道图" + "</td>" +
                // "<td>" + "车道编号" + "</td>" +
                //"<td>" + "车道名" + "</td>" +
                "<td>" + "2轴车" + "</td>" +
                "<td>" + "3轴车" + "</td>" +
                "<td>" + "4轴车" + "</td>" +
                "<td>" + "5轴车" + "</td>" +
                "<td>" + "6轴车及以上" + "</td>" +
                "</tr>" +
                +
                    "</tfoot>");
            // let Strack2=true;
            for (let n = 1; n <= 1; n++) {
                // let img;
                // if (n == json.length) {
                //车道0即总计的一行的td
                let tdDom = "";
                for (let j = k + 1; j <= k + 4; j++) {
                    tdDom = tdDom + "<td data-id='" + json[0].avgLaneNo + "," + j + "'><a >" + json[0]['column' + j].toFixed(2) + "%</a></td>";
                }
                tdDom = tdDom + "<td data-id='" + json[0].avgLaneNo + "," + k + "'><a >" + json[0]['column' + k].toFixed(2) + "%</a></td>";
                let dom = tableDom.find("tfoot");
                // if(Strack2){
                //     img="<td width='20%' rowspan='"+(json.length)+"'>" + getimgUrl_sj(2) + "</td>" ;
                //     Strack2=false;
                // }
                $(dom).append("<tr align='center'>" +
                    // img+
                    // "<td >合计</td>" +
                    tdDom +
                    "</tr>")
                break;
                // }
                // let tdDom = "";
                // for (let j = k + 1; j <= k + 4; j++) {
                //     tdDom = tdDom + "<td data-id='" + json[n].avgLaneNo + "," + j + "'><a >" + json[n]['column' + j].toFixed(2) + "%</a></td>";
                // }
                // tdDom = tdDom + "<td data-id='"+json[n].avgLaneNo+","+k+"'><a >" + json[n]['column' + k].toFixed(2) + "%</a></td>";
                // let dom = tableDom.find("tfoot");
                // if(Strack2){
                //     img="<td width='20%' rowspan='"+(json.length)+"'>" + getimgUrl_sj(2) + "</td>" ;
                //     Strack2=false;
                // }
                // $(dom).append("<tr align='center'>" +
                //     img +
                //     "<td>" + json[n].avgLaneNo + "</td>" +
                //     /*"<td>" + formatLaneName(json[n].avgLaneNo) + "</td>" +*/
                //     tdDom +
                //     "</tr>")
            }
        } else if (i == 4) {
            let tableDom = $("#myTabOne" + i + "Month" + "_sj").html('');
            let k = 5 * (i) + i - 1;
            tableDom.append("<thead>" + "</thead>" + "<tfoot>" +
                "<tr align='center'>" +
                // "<td>" + "车道图" + "</td>" +
                // "<td>" + "车道编号" + "</td>" +
                //"<td>" + "车道名" + "</td>" +
                "<td>" + "2轴车" + "</td>" +
                "<td>" + "3轴车" + "</td>" +
                "<td>" + "4轴车" + "</td>" +
                "<td>" + "5轴车" + "</td>" +
                "<td>" + "6轴车及以上" + "</td>" +
                "<td>" + "合计" + "</td>" +
                "</tr>" +
                +
                    "</tfoot>");
            // let Strack2 = true;
            for (let n = 1; n <= 1; n++) {
                let img;
                // if (n == json.length) {
                //车道0即总计的一行的td
                let tdDom = "";
                for (let j = k + 1; j <= k + 5; j++) {
                    tdDom = tdDom + "<td data-id='" + json[0].avgLaneNo + "," + j + "'><a >" + json[0]['column' + j].toFixed(2) + "%</a></td>";
                }
                tdDom = tdDom + "<td data-id='" + json[0].avgLaneNo + "," + k + "'><a >" + json[0]['column' + k].toFixed(2) + "%</a></td>";
                let dom = tableDom.find("tfoot");
                // if (Strack2) {
                //     img = "<td width='20%' rowspan='" + (json.length) + "'>" + getimgUrl_sj(2) + "</td>";
                //     Strack2 = false;
                // }
                $(dom).append("<tr align='center'>" +
                    // img +
                    // "<td >合计</td>" +
                    tdDom +
                    "</tr>")
                break;
                // }
                // let tdDom = "";
                // for (let j = k + 1; j <= k + 5; j++) {
                //     tdDom = tdDom + "<td data-id='" + json[n].avgLaneNo + "," + j + "'><a >" + json[n]['column' + j].toFixed(2) + "%</a></td>";
                // }
                // tdDom = tdDom + "<td data-id='" + json[n].avgLaneNo + "," + k + "'><a >" + json[n]['column' + k].toFixed(2) + "%</a></td>";
                // let dom = tableDom.find("tfoot");
                // if (Strack2) {
                //     img = "<td width='20%' rowspan='" + (json.length) + "'>" + getimgUrl_sj(2) + "</td>";
                //     Strack2 = false;
                // }
                // $(dom).append("<tr align='center'>" +
                //     img +
                //     "<td>" + json[n].avgLaneNo + "</td>" +
                //     /*"<td>" + formatLaneName(json[n].avgLaneNo) + "</td>" +*/
                //     tdDom +
                //     "</tr>")
            }
        } else {
            let tableDom = $("#myTabOne" + i + "Month" + "_sj").html('');
            let k = 5 * (i) + i - 1;
            tableDom.append("<thead>" + "</thead>" + "<tfoot>" +
                "<tr align='center'>" +
                // "<td>" + "车道图" + "</td>" +
                // "<td>" + "车道编号" + "</td>" +
                //"<td>" + "车道名" + "</td>" +
                "<td>" + "2轴车" + "</td>" +
                "<td>" + "3轴车" + "</td>" +
                "<td>" + "4轴车" + "</td>" +
                "<td>" + "5轴车" + "</td>" +
                "<td>" + "6轴车及以上" + "</td>" +
                "<td>" + "合计" + "</td>" +
                "</tr>" +
                +
                    "</tfoot>");
            // let Strack=true;
            for (let n = 1; n <= 1; n++) {
                // let img;
                // if (n == json.length) {
                //车道0即总计的一行的td
                let tdDom = "";
                for (let j = k + 1; j <= k + 5; j++) {
                    tdDom = tdDom + "<td data-id='" + json[0].avgLaneNo + "," + j + "'><a >" + json[0]['column' + j] + "</a></td>";
                }
                tdDom = tdDom + "<td data-id='" + json[0].avgLaneNo + "," + k + "'><a >" + json[0]['column' + k] + "</a></td>";
                let dom = tableDom.find("tfoot");
                // if(Strack){
                //     img="<td width='20%' rowspan='"+(json.length)+"'>" + getimgUrl_sj(2) + "</td>" ;
                //     Strack=false;
                // }
                $(dom).append("<tr align='center'>" +
                    // img+
                    // "<td >合计</td>" +
                    tdDom +
                    "</tr>")
                break;
                // }
                // let tdDom = "";
                // for (let j = k + 1; j <= k + 5; j++) {
                //     tdDom = tdDom + "<td data-id='"+json[n].avgLaneNo+","+j+"'><a >" + json[n]['column' + j] + "</a></td>";
                // }
                // tdDom = tdDom + "<td data-id='"+json[n].avgLaneNo+","+k+"'><a >" + json[n]['column' + k] + "</a></td>";
                // let dom = tableDom.find("tfoot");
                // if(Strack){
                //     img="<td width='20%' rowspan='"+(json.length)+"'>" + getimgUrl_sj(2) + "</td>" ;
                //     Strack=false;
                // }
                // $(dom).append("<tr align='center'>" +
                //     img+
                //     "<td>" + json[n].avgLaneNo + "</td>" +
                //     /*"<td>" + formatLaneName(json[n].avgLaneNo) + "</td>" +*/
                //     tdDom +
                //     "</tr>")
            }
        }
    }
}
dayAndMonthTongjiDomMonth_sj.bindClick = (stationIp) => {
    $("#dataTableMonth_sj").find("td").each(function () {
        let str = $(this).attr("data-id");
        if (str != undefined) {
            $(this).on('click', function () {
                let strIn = $(this).attr("data-id");
                let key = $(this).attr("data-key");
                let laneNo = strIn.split(",")[0];
                let column = strIn.split(",")[1];
                $.ajax({
                    type: 'POST',
                    url: '/tAvgDay/getEchartsListByLaneNoAndColumnMonth_sj',
                    dataType: 'json',
                    data: {
                        provinceName: stationIp,
                        laneNo: laneNo,
                        column: column,
                    },
                    error: function (msg) {
                    },
                    success: function (json) {
                        dayAndMonthTongjiDomMonth_sj.makeLineChartMonth(json, column);
                        lookMsg1(key, 3);
                    }
                });
            })

        }
    })
}
dayAndMonthTongjiDomMonth_sj.makeLineChartMonth = (data, column) => {
    if (column == '4') {
        $("#modalEchartsDetailMonth_sj").modal("show");
        initMyChartMonth_sj(data, 'myChartMonth2_sj');
    } else {
        $("#modalEchartsMonth_sj").modal("show");
        initMyChartMonth_sj(data, 'myChartMonth_sj');
    }
}

let initMyChartMonth_sj = (data, id) => {
    let myChart = echarts.init(document.getElementById(id));
    let option = {
        title: {
            text: ''
        },
        toolbox: {
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                restore: {},
                saveAsImage: {},
                dataView: {}
            }
        },
        tooltip: {},
        xAxis: {
            type: 'category',
            data: data.x,
            name: "日期",
            boundaryGap: false
        },
        yAxis: {
            type: 'value',
            boundaryGap: [0, '100%']
        },
        dataZoom: [{
            type: 'inside',
            start: 0,
            end: 100
        }, {
            start: 0,
            end: 100,
            handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
            handleSize: '80%',
            handleStyle: {
                color: '#fff',
                shadowBlur: 3,
                shadowColor: 'rgba(0, 0, 0, 0.6)',
                shadowOffsetX: 2,
                shadowOffsetY: 2
            }
        }],
        series: [{
            name: '',
            type: 'line',
            data: data.y
        }]
    };
    myChart.setOption(option);
}