package com.ldxx.service;

import org.apache.ibatis.session.ResultContext;

public interface CustomResultHandler {
    public void handleResult(ResultContext resultContext);
}
