let dayAndMonthTongjiDom_lgs = {};
let dayAndMonthTongjiDom_lgs_week = {};

dayAndMonthTongjiDom_lgs.initTable = () => {
    $("#dataTable_lgs").append("<div id='title'><ul   class='nav nav-tabs pull-left'>"
        + " <li class='active'><a href='#s1_lgs' data-toggle='tab' id='tab1_lgs'>交通量</a></li>"
        + " <li><a href='#s2_lgs' data-toggle='tab' id='tab2_lgs'>超载车辆数</a></li>"
        + " <li><a href='#s3_lgs' data-toggle='tab' id='tab3_lgs'>平均超载率(%)</a></li>"
        + " <li ><a href='#s4_lgs' data-toggle='tab' id='tab4_lgs'>超载比例(%)</a></li>"
        + " <li ><a href='#s5_lgs' data-toggle='tab' id='tab5_lgs'>最大车重(kg)</a></li>"
        + "</ul></div>"
        + "<div id='myTabContent1_lgs' class='tab-content bg-color-white no-padding' style='overflow-x: auto; overflow-y: auto; height:450px; width:1040px;'>"
        + " <div class='tab-pane fade in active' id='s1_lgs'>"
        + " <table id='myTabOne1_lgs' data-type='交通量' class='table table-bordered table-hover' border='2'  bordercolor='black' cellspacing='0' cellpadding='0'></table></div>"
        + "<div class='tab-pane fade in' id='s2_lgs'><table id='myTabOne2_lgs' data-type='超载车辆数'  class='table table-bordered table-hover' border='2'  bordercolor='black' cellspacing='0' cellpadding='0'></table></div>"
        + "<div class='tab-pane fade in' id='s3_lgs'><table id='myTabOne3_lgs' data-type='平均超载率'  class='table table-bordered table-hover' border='2' bordercolor='black' cellspacing='0' cellpadding='0'></table></div>"
        + " <div class='tab-pane fade in' id='s4_lgs'>"
        + " <table id='myTabOne4_lgs' data-type='超载比例' class='table table-bordered table-hover' border='2'  bordercolor='black' cellspacing='0' cellpadding='0'></table></div>"
        + "  <div class='tab-pane fade in' id='s5_lgs'><table id='myTabOne5_lgs' data-type='最大车重' class='table table-bordered table-hover' border='2' bordercolor='black' cellspacing='0' cellpadding='0'></table></div>"
        + " </div>")
}
dayAndMonthTongjiDom_lgs_week.initTable = () => {
    $("#dataTable_lgs_week").append("<div id='title'><ul   class='nav nav-tabs pull-left'>"
        + " <li class='active'><a href='#s1_lgs_week' data-toggle='tab' id='tab1_lgs_week'>交通量</a></li>"
        + " <li><a href='#s2_lgs_week' data-toggle='tab' id='tab2_lgs_week'>超载车辆数</a></li>"
        + " <li><a href='#s3_lgs_week' data-toggle='tab' id='tab3_lgs_week'>平均超载率(%)</a></li>"
        + " <li ><a href='#s4_lgs_week' data-toggle='tab' id='tab4_lgs_week'>超载比例(%)</a></li>"
        + " <li ><a href='#s5_lgs_week' data-toggle='tab' id='tab5_lgs_week'>最大车重(kg)</a></li>"
        + "</ul></div>"
        + "<div id='myTabContent1_lgs_week' class='tab-content bg-color-white no-padding' style='overflow-x: auto; overflow-y: auto; height:450px; width:1040px;'>"
        + " <div class='tab-pane fade in active' id='s1_lgs_week'>"
        + " <table id='myTabOne1_lgs_week' data-type='交通量' class='table table-bordered table-hover' border='2'  bordercolor='black' cellspacing='0' cellpadding='0'></table></div>"
        + "<div class='tab-pane fade in' id='s2_lgs_week'><table id='myTabOne2_lgs_week' data-type='超载车辆数'  class='table table-bordered table-hover' border='2'  bordercolor='black' cellspacing='0' cellpadding='0'></table></div>"
        + "<div class='tab-pane fade in' id='s3_lgs_week'><table id='myTabOne3_lgs_week' data-type='平均超载率'  class='table table-bordered table-hover' border='2' bordercolor='black' cellspacing='0' cellpadding='0'></table></div>"
        + " <div class='tab-pane fade in' id='s4_lgs_week'>"
        + " <table id='myTabOne4_lgs_week' data-type='超载比例' class='table table-bordered table-hover' border='2'  bordercolor='black' cellspacing='0' cellpadding='0'></table></div>"
        + "  <div class='tab-pane fade in' id='s5_lgs_week'><table id='myTabOne5_lgs_week' data-type='最大车重' class='table table-bordered table-hover' border='2' bordercolor='black' cellspacing='0' cellpadding='0'></table></div>"
        + " </div>")
}

dayAndMonthTongjiDom_lgs.initTableBodyAjax = (companyName, avgTime) => {
    $.ajax({
        type: 'POST',
        url: '/tAvgDay/gettAvgDayColumnByCompanyNameAndTime_lgs',
        dataType: 'json',
        data: {
            companyName: companyName,
            avgTime: avgTime,
            avgLaneNo: 0
        },
        error: function (msg) {
        },
        success: function (json) {
            initTableTbody_lgs(json)
            dayAndMonthTongjiDom_lgs.bindClick(companyName);
        }
    });
}

dayAndMonthTongjiDom_lgs_week.initTableBodyAjax = (companyName, avgTime) => {
    $.ajax({
        type: 'POST',
        url: '/tAvgDay/gettAvgDayColumnByCompanyNameAndTime_lgs',
        dataType: 'json',
        data: {
            companyName: companyName,
            avgTime: avgTime,
            avgLaneNo: 0
        },
        error: function (msg) {
        },
        success: function (json) {
            initTableTbody_lgs_week(json)
            dayAndMonthTongjiDom_lgs_week.bindClick(companyName);
        }
    });
}

let initTableTbody_lgs = (json) => {
    for (let i = 1; i <= 5; i++) {
        if (i == 5) {
            let tableDom = $("#myTabOne" + i + "_lgs").html('');
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
                tdDom = "<td data-id='" + 0 + ",4' data-key='" + json[0].avgMaxID + "'><a >" + json[0].avgMax + "</a></td>";
                let dom = tableDom.find("tfoot");
                // if(Strack3){
                //     img="<td width='20%' rowspan='"+(json.length)+"'>" + getimgUrl_lgs(1) + "</td>" ;
                //     Strack3=false;
                // }
                $(dom).append("<tr align='center'>" +
                    // img+
                    // // // "<td>合计</td>" +
                    tdDom +
                    "</tr>")
                break;
                // }
                // let tdDom = "";
                // tdDom = "<td data-id='"+json[1].avgLaneNo+",4' data-key='"+json[n].avgMaxID+"'><a >" + json[n].avgMax + "</a></td>";
                // let dom = tableDom.find("tfoot");
                // if(Strack3){
                //     img = "<td width='20%' rowspan='" + (json.length) + "'>" + getimgUrl_lgs(1) + "</td>";
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
            let tableDom = $("#myTabOne" + i + "_lgs").html('');
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
                // let img;
                // if (n == json.length) {
                //车道0即总计的一行的td
                let tdDom = "";
                for (let j = k + 1; j <= k + 4; j++) {
                    tdDom = tdDom + "<td data-id='" + 0 + "," + j + "'><a >" + json[0]['column' + j].toFixed(2) + "%</a></td>";
                }
                tdDom = tdDom + "<td data-id='" + 0 + "," + k + "'><a >" + json[0]['column' + k].toFixed(2) + "%</a></td>";
                let dom = tableDom.find("tfoot");
                // if(Strack2){
                //     img="<td width='20%' rowspan='"+(json.length)+"'>" + getimgUrl_lgs(1) + "</td>" ;
                //     Strack2=false;
                // }
                $(dom).append("<tr align='center'>" +
                    // img+
                    // // // "<td>合计</td>" +
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
                //     img = "<td width='20%' rowspan='" + (json.length) + "'>" + getimgUrl_lgs(1) + "</td>";
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
            let tableDom = $("#myTabOne" + i + "_lgs").html('');
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
                if (n == json.length) {
                    //车道0即总计的一行的td
                    let tdDom = "";
                    for (let j = k + 1; j <= k + 5; j++) {
                        tdDom = tdDom + "<td data-id='" + 0 + "," + j + "'><a >" + json[0]['column' + j].toFixed(2) + "%</a></td>";
                    }
                    tdDom = tdDom + "<td data-id='" + 0 + "," + k + "'><a >" + json[0]['column' + k].toFixed(2) + "%</a></td>";
                    let dom = tableDom.find("tfoot");
                    // if (Strack2) {
                    //     img = "<td width='20%' rowspan='" + (json.length) + "'>" + getimgUrl_lgs(1) + "</td>";
                    //     Strack2 = false;
                    // }
                    $(dom).append("<tr align='center'>" +
                        // img +
                        // // // "<td>合计</td>" +
                        tdDom +
                        "</tr>")
                    break;
                }
                // let tdDom = "";
                // for (let j = k + 1; j <= k + 5; j++) {
                //     tdDom = tdDom + "<td data-id='" + json[n].avgLaneNo + "," + j + "'><a >" + json[n]['column' + j].toFixed(2) + "%</a></td>";
                // }
                // tdDom = tdDom + "<td data-id='" + json[n].avgLaneNo + "," + k + "'><a >" + json[n]['column' + k].toFixed(2) + "%</a></td>";
                // let dom = tableDom.find("tfoot");
                // if (Strack2) {
                //     img = "<td width='20%' rowspan='" + (json.length) + "'>" + getimgUrl_lgs(1) + "</td>";
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
            let tableDom = $("#myTabOne" + i + "_lgs").html('');
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
                // let url=getimgUrl_lgs(json[0].stationIP);
                // let img;
                if (n == json.length) {
                    //车道0即总计的一行的td
                    let tdDom = "";
                    for (let j = k + 1; j <= k + 5; j++) {
                        tdDom = tdDom + "<td data-id='" + json[0].avgLaneNo + "," + j + "'><a >" + json[0]['column' + j] + "</a></td>";
                    }
                    tdDom = tdDom + "<td data-id='" + json[0].avgLaneNo + "," + k + "'><a >" + json[0]['column' + k] + "</a></td>";
                    let dom = tableDom.find("tfoot");
                    // if(Strack){
                    //     img="<td width='20%' rowspan='"+(json.length)+"'>" + getimgUrl_lgs(1) + "</td>" ;
                    //     Strack=false;
                    // }
                    $(dom).append("<tr align='center'>" +
                        // img+
                        // // // // "<td>合计</td>" +
                        tdDom +
                        "</tr>")
                    break;
                }
                // let tdDom = "";
                // for (let j = k + 1; j <= k + 5; j++) {
                //     tdDom = tdDom + "<td data-id='"+json[n].avgLaneNo+","+j+"'><a >" + json[n]['column' + j] + "</a></td>";
                // }
                // tdDom = tdDom + "<td data-id='"+json[n].avgLaneNo+","+k+"'><a >" + json[n]['column' + k] + "</a></td>";
                // let dom = tableDom.find("tfoot");
                // if(Strack){
                //     img="<td width='20%' rowspan='"+(json.length)+"'>" + getimgUrl_lgs(1) + "</td>" ;
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

let initTableTbody_lgs_week = (json) => {
    for (let i = 1; i <= 5; i++) {
        if (i == 5) {
            let tableDom = $("#myTabOne" + i + "_lgs" + "_week").html('');
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
                tdDom = "<td data-id='" + 0 + ",4' data-key='" + json[0].avgMaxID + "'><a >" + json[0].avgMax + "</a></td>";
                let dom = tableDom.find("tfoot");
                // if(Strack3){
                //     img="<td width='20%' rowspan='"+(json.length)+"'>" + getimgUrl_lgs(1) + "</td>" ;
                //     Strack3=false;
                // }
                $(dom).append("<tr align='center'>" +
                    // img+
                    // // // "<td>合计</td>" +
                    tdDom +
                    "</tr>")
                break;
                // }
                // let tdDom = "";
                // tdDom = "<td data-id='"+json[1].avgLaneNo+",4' data-key='"+json[n].avgMaxID+"'><a >" + json[n].avgMax + "</a></td>";
                // let dom = tableDom.find("tfoot");
                // if(Strack3){
                //     img = "<td width='20%' rowspan='" + (json.length) + "'>" + getimgUrl_lgs(1) + "</td>";
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
            let tableDom = $("#myTabOne" + i + "_lgs" + "_week").html('');
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
                // let img;
                // if (n == json.length) {
                //车道0即总计的一行的td
                let tdDom = "";
                for (let j = k + 1; j <= k + 4; j++) {
                    tdDom = tdDom + "<td data-id='" + 0 + "," + j + "'><a >" + json[0]['column' + j].toFixed(2) + "%</a></td>";
                }
                tdDom = tdDom + "<td data-id='" + 0 + "," + k + "'><a >" + json[0]['column' + k].toFixed(2) + "%</a></td>";
                let dom = tableDom.find("tfoot");
                // if(Strack2){
                //     img="<td width='20%' rowspan='"+(json.length)+"'>" + getimgUrl_lgs(1) + "</td>" ;
                //     Strack2=false;
                // }
                $(dom).append("<tr align='center'>" +
                    // img+
                    // // // "<td>合计</td>" +
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
                //     img = "<td width='20%' rowspan='" + (json.length) + "'>" + getimgUrl_lgs(1) + "</td>";
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
            let tableDom = $("#myTabOne" + i + "_lgs" + "_week").html('');
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
                if (n == json.length) {
                    //车道0即总计的一行的td
                    let tdDom = "";
                    for (let j = k + 1; j <= k + 5; j++) {
                        tdDom = tdDom + "<td data-id='" + 0 + "," + j + "'><a >" + json[0]['column' + j].toFixed(2) + "%</a></td>";
                    }
                    tdDom = tdDom + "<td data-id='" + 0 + "," + k + "'><a >" + json[0]['column' + k].toFixed(2) + "%</a></td>";
                    let dom = tableDom.find("tfoot");
                    // if (Strack2) {
                    //     img = "<td width='20%' rowspan='" + (json.length) + "'>" + getimgUrl_lgs(1) + "</td>";
                    //     Strack2 = false;
                    // }
                    $(dom).append("<tr align='center'>" +
                        // img +
                        // // // "<td>合计</td>" +
                        tdDom +
                        "</tr>")
                    break;
                }
                // let tdDom = "";
                // for (let j = k + 1; j <= k + 5; j++) {
                //     tdDom = tdDom + "<td data-id='" + json[n].avgLaneNo + "," + j + "'><a >" + json[n]['column' + j].toFixed(2) + "%</a></td>";
                // }
                // tdDom = tdDom + "<td data-id='" + json[n].avgLaneNo + "," + k + "'><a >" + json[n]['column' + k].toFixed(2) + "%</a></td>";
                // let dom = tableDom.find("tfoot");
                // if (Strack2) {
                //     img = "<td width='20%' rowspan='" + (json.length) + "'>" + getimgUrl_lgs(1) + "</td>";
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
            let tableDom = $("#myTabOne" + i + "_lgs" + "_week").html('');
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
                // let url=getimgUrl_lgs(json[0].stationIP);
                // let img;
                if (n == json.length) {
                    //车道0即总计的一行的td
                    let tdDom = "";
                    for (let j = k + 1; j <= k + 5; j++) {
                        tdDom = tdDom + "<td data-id='" + json[0].avgLaneNo + "," + j + "'><a >" + json[0]['column' + j] + "</a></td>";
                    }
                    tdDom = tdDom + "<td data-id='" + json[0].avgLaneNo + "," + k + "'><a >" + json[0]['column' + k] + "</a></td>";
                    let dom = tableDom.find("tfoot");
                    // if(Strack){
                    //     img="<td width='20%' rowspan='"+(json.length)+"'>" + getimgUrl_lgs(1) + "</td>" ;
                    //     Strack=false;
                    // }
                    $(dom).append("<tr align='center'>" +
                        // img+
                        // // // // "<td>合计</td>" +
                        tdDom +
                        "</tr>")
                    break;
                }
                // let tdDom = "";
                // for (let j = k + 1; j <= k + 5; j++) {
                //     tdDom = tdDom + "<td data-id='"+json[n].avgLaneNo+","+j+"'><a >" + json[n]['column' + j] + "</a></td>";
                // }
                // tdDom = tdDom + "<td data-id='"+json[n].avgLaneNo+","+k+"'><a >" + json[n]['column' + k] + "</a></td>";
                // let dom = tableDom.find("tfoot");
                // if(Strack){
                //     img="<td width='20%' rowspan='"+(json.length)+"'>" + getimgUrl_lgs(1) + "</td>" ;
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
let getimgUrl_lgs = (e) => {
    var stationPort;
    if (e == 1) {
        stationPort = $("#stationPort_lgs").val()
    } else if (e == 2) {
        stationPort = $("#stationPort2_lgs").val()
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
                    var url = getLocalPath2_lgs() + json[i].acUrl;
                    imgUrl = imgUrl + '<a target="_blank" href="' + url + '" ><img  src="' + url + '" class="popup" style="max-width:300px !important;" /></a>'
                }
            } else {
                imgUrl = imgUrl + "无车道图"
            }
        }
    });
    return imgUrl;
}

let getLocalPath2_lgs = () => {
    var loginUrl;
    $.ajax({
        type: 'POST',
        url: '/tVehicleOverweight/getimgUrlPrefix',
        dataType: 'json',
        async: false,
        data: {},
        success: function (json) {
            loginUrl = "http://" + json.imgUrlPrefix + "" + "/dtcz_file/"
        }
    });
    return loginUrl;
}

let formatLaneName_lgs = (e) => {
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

dayAndMonthTongjiDom_lgs.bindClick = (stationIp) => {
    $("#dataTable_lgs").find("td").each(function () {
        let str = $(this).attr("data-id");
        if (str != undefined) {
            $(this).on('click', function () {
                let strIn = $(this).attr("data-id");
                let key = $(this).attr("data-key");
                let laneNo = strIn.split(",")[0];
                let column = strIn.split(",")[1];
                $.ajax({
                    type: 'POST',
                    url: '/tAvgDay/getEchartsListByLaneNoAndColumn_lgs',
                    dataType: 'json',
                    data: {
                        companyName: stationIp,
                        laneNo: laneNo,
                        column: column,
                    },
                    error: function (msg) {
                    },
                    success: function (json) {
                        dayAndMonthTongjiDom_lgs.makeLineChart(json, column);
                        lookMsg(key, 3);
                    }
                });
            })

        }
    })
}
dayAndMonthTongjiDom_lgs_week.bindClick = (stationIp) => {
    $("#dataTable_lgs_week").find("td").each(function () {
        let str = $(this).attr("data-id");
        if (str != undefined) {
            $(this).on('click', function () {
                let strIn = $(this).attr("data-id");
                let key = $(this).attr("data-key");
                let laneNo = strIn.split(",")[0];
                let column = strIn.split(",")[1];
                $.ajax({
                    type: 'POST',
                    url: '/tAvgDay/getEchartsListByLaneNoAndColumn_lgs_week',
                    dataType: 'json',
                    data: {
                        companyName: stationIp,
                        laneNo: laneNo,
                        column: column,
                    },
                    error: function (msg) {
                    },
                    success: function (json) {
                        dayAndMonthTongjiDom_lgs_week.makeLineChart(json, column);
                        lookMsg(key, 3);
                    }
                });
            })

        }
    })
}
dayAndMonthTongjiDom_lgs.makeLineChart = (data, column) => {
    if (column == '4') {
        $("#modalEchartsDetail_lgs").modal("show");
        initMyChart_lgs(data, 'myChart2_lgs');
    } else {
        $("#modalEcharts_lgs").modal("show");
        initMyChart_lgs(data, 'myChart_lgs');
    }
}

dayAndMonthTongjiDom_lgs_week.makeLineChart = (data, column) => {
    if (column == '4') {
        $("#modalEchartsDetail_lgs_week").modal("show");
        initMyChart_lgs(data, 'myChart2_lgs_week');
    } else {
        $("#modalEcharts_lgs_week").modal("show");
        initMyChart_lgs(data, 'myChart_lgs_week');
    }
}

let initMyChart_lgs = (data, id) => {
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


let dayAndMonthTongjiDomMonth_lgs = {};

dayAndMonthTongjiDomMonth_lgs.initTableMonth = () => {
    $("#dataTableMonth_lgs").append("<div id='title'><ul   class='nav nav-tabs pull-left'>"
        + " <li class='active'><a href='#s1Month_lgs' data-toggle='tab' id='tab1Month_lgs'>交通量</a></li>"
        + " <li><a href='#s2Month_lgs' data-toggle='tab' id='tab2Month_lgs'>超载车辆数</a></li>"
        + " <li><a href='#s3Month_lgs' data-toggle='tab' id='tab3Month_lgs'>平均超载率(%)</a></li>"
        + " <li ><a href='#s4Month_lgs' data-toggle='tab' id='tab4Month_lgs'>超载比例(%)</a></li>"
        + " <li ><a href='#s5Month_lgs' data-toggle='tab' id='tab5Month_lgs'>最大车重(kg)</a></li>"
        + "</ul></div>"
        + "<div id='myTabContent1Month_lgs' class='tab-content bg-color-white no-padding' style='overflow-x: auto; overflow-y: auto; height:450px; width:1040px;'>"
        + " <div class='tab-pane fade in active' id='s1Month_lgs'>"
        + " <table id='myTabOne1Month_lgs' data-id='交通量' class='table table-bordered table-hover' border='2'  bordercolor='black' cellspacing='0' cellpadding='0'></table></div>"
        + "<div class='tab-pane fade in' id='s2Month_lgs'><table id='myTabOne2Month_lgs' data-id='超载车辆数'  class='table table-bordered table-hover' border='2'  bordercolor='black' cellspacing='0' cellpadding='0'></table></div>"
        + "<div class='tab-pane fade in' id='s3Month_lgs'><table id='myTabOne3Month_lgs' data-id='平均超载率'  class='table table-bordered table-hover' border='2' bordercolor='black' cellspacing='0' cellpadding='0'></table></div>"
        + " <div class='tab-pane fade in' id='s4Month_lgs'>"
        + " <table id='myTabOne4Month_lgs' data-id='超载比例' class='table table-bordered table-hover' border='2'  bordercolor='black' cellspacing='0' cellpadding='0'></table></div>"
        + "  <div class='tab-pane fade in' id='s5Month_lgs'><table id='myTabOne5Month_lgs' data-id='最大车重' class='table table-bordered table-hover' border='2' bordercolor='black' cellspacing='0' cellpadding='0'></table></div>"
        + " </div>")
}

dayAndMonthTongjiDomMonth_lgs.initTableBodyAjaxMonth = (stationIp, avgTime) => {
    $.ajax({
        type: 'POST',
        url: '/tAvgDay/gettAvgDayColumnByIpAndTimeMonth_lgs',
        dataType: 'json',
        data: {
            companyName: stationIp,
            avgTime: avgTime,
            avgLaneNo: 0
        },
        error: function (msg) {
        },
        success: function (json) {
            initTableTbodyMonth_lgs(json)
            dayAndMonthTongjiDomMonth_lgs.bindClick(stationIp);
        }
    });
}
let initTableTbodyMonth_lgs = (json) => {
    for (let i = 1; i <= 5; i++) {
        if (i == 5) {
            let tableDom = $("#myTabOne" + i + "Month_lgs").html('');
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
                //     img="<td width='20%' rowspan='"+(json.length)+"'>" + getimgUrl_lgs(2) + "</td>" ;
                //     Strack3=false;
                // }
                $(dom).append("<tr align='center'>" +
                    // img+
                    // // // "<td>合计</td>" +
                    tdDom +
                    "</tr>")
                break;
                // }
                // let tdDom = "";
                // tdDom = "<td data-id='"+json[n].avgLaneNo+",4' data-key='"+json[n].avgMaxID+"'><a >" + json[n].avgMax + "</a></td>";
                // let dom = tableDom.find("tfoot");
                // if(Strack3){
                //     img = "<td width='20%' rowspan='" + (json.length) + "'>" + getimgUrl_lgs(2) + "</td>";
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
            let tableDom = $("#myTabOne" + i + "Month_lgs").html('');
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
                    tdDom = tdDom + "<td data-id='" + 0 + "," + j + "'><a >" + json[0]['column' + j].toFixed(2) + "%</a></td>";
                }
                tdDom = tdDom + "<td data-id='" + 0 + "," + k + "'><a >" + json[0]['column' + k].toFixed(2) + "%</a></td>";
                let dom = tableDom.find("tfoot");
                // if(Strack2){
                //     img="<td width='20%' rowspan='"+(json.length)+"'>" + getimgUrl_lgs(2) + "</td>" ;
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
                // if(Strack2){
                //     img="<td width='20%' rowspan='"+(json.length)+"'>" + getimgUrl_lgs(2) + "</td>" ;
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
            let tableDom = $("#myTabOne" + i + "Month_lgs").html('');
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
                //     img = "<td width='20%' rowspan='" + (json.length) + "'>" + getimgUrl_lgs(2) + "</td>";
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
                //     img = "<td width='20%' rowspan='" + (json.length) + "'>" + getimgUrl_lgs(2) + "</td>";
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
            let tableDom = $("#myTabOne" + i + "Month_lgs").html('');
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
                //     img="<td width='20%' rowspan='"+(json.length)+"'>" + getimgUrl_lgs(2) + "</td>" ;
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
                //     img="<td width='20%' rowspan='"+(json.length)+"'>" + getimgUrl_lgs(2) + "</td>" ;
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
dayAndMonthTongjiDomMonth_lgs.bindClick = (stationIp) => {
    $("#dataTableMonth_lgs").find("td").each(function () {
        let str = $(this).attr("data-id");
        if (str != undefined) {
            $(this).on('click', function () {
                let strIn = $(this).attr("data-id");
                let key = $(this).attr("data-key");
                let laneNo = strIn.split(",")[0];
                let column = strIn.split(",")[1];
                $.ajax({
                    type: 'POST',
                    url: '/tAvgDay/getEchartsListByLaneNoAndColumnMonth_lgs',
                    dataType: 'json',
                    data: {
                        companyName: stationIp,
                        laneNo: laneNo,
                        column: column,
                    },
                    error: function (msg) {
                    },
                    success: function (json) {
                        dayAndMonthTongjiDomMonth_lgs.makeLineChartMonth(json, column);
                        lookMsg1(key, 3);
                    }
                });
            })

        }
    })
}
dayAndMonthTongjiDomMonth_lgs.makeLineChartMonth = (data, column) => {
    if (column == '4') {
        $("#modalEchartsDetailMonth_lgs").modal("show");
        initMyChartMonth_lgs(data, 'myChartMonth2_lgs');
    } else {
        $("#modalEchartsMonth_lgs").modal("show");
        initMyChartMonth_lgs(data, 'myChartMonth_lgs');
    }
}

let initMyChartMonth_lgs = (data, id) => {
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