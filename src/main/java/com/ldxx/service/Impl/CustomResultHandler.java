package com.ldxx.service.Impl;


import com.ldxx.util.CallbackProcesser;
import com.ldxx.vo.tWimMsgVo;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

public class CustomResultHandler implements ResultHandler {

    private final CallbackProcesser callbackProcesser;

    public CustomResultHandler(CallbackProcesser callbackProcesser) {
        super();
        this.callbackProcesser = callbackProcesser;
    }

    @Override
    public void handleResult(ResultContext resultContext) {
        tWimMsgVo detailsDownload = (tWimMsgVo)resultContext.getResultObject();
        System.out.println("detailsDownload:{"+detailsDownload+"}");

        callbackProcesser.processData(detailsDownload);
    }
}
