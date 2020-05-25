let dayAndMonthTongjiDom_zhtj = {};
dayAndMonthTongjiDom_zhtj.initTable = (avgTime) => {
    $.ajax({
        type: 'POST',
        url: '/tAvgDay/gettAvgDayTotalByTime',
        dataType: 'json',
        data: {
            avgTime: avgTime,
        },
        error: function (msg) {
        },
        success: function (json) {
            initTableTr(json);
        }
    });
}

function initTableTr(dom) {
    let e = $("#table_zhtj").find("tr").eq(1);
    $(e).empty();
    let td = "<td data-id=\"0,6\"><a>" + nullToStr(dom.avgCount) + "</a></td>\n" +
        "                                <td data-id=\"0,7\"><a>" + nullToStr(dom.avgOverloadCount) + "</a></td>\n" +
        "                                <td data-id='" + dom.avgMaxID + "'><a>" + nullToStr(dom.avgMax) + "</a></td>\n" +
        "                                <td data-id=\"0,9\"><a>" + nullToStr(dom.range80Count1) + "</a></td>\n" +
        "                                <td data-id=\"0,10\"><a>" + nullToStr(dom.rangeCount3) + "</a></td>"
    if (nullToStr(dom.avgCount) != 0) {
        $(e).append(td);
    }
}

let nullToStr = (e) => {
    if (null == e || "" == e || undefined == e) {
        return 0;
    } else {
        return e;
    }
}