package com.ldxx.Thread;

import com.ldxx.bean.TongJiTableQuery;
import com.ldxx.dao.tWimMsgDao;
import com.ldxx.service.tWimMsgService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.Callable;

@Component
public class PageCountCallable implements Callable<Integer> {

    @Autowired
    public tWimMsgService tWimMsgService;

    public static PageCountCallable pageCountCallable;

    public  TongJiTableQuery tongJiTableQuery;

    @PostConstruct
    public void init(){
        pageCountCallable = this;
        pageCountCallable.tWimMsgService = this.tWimMsgService;
    }
    public PageCountCallable() {
    }

    public PageCountCallable(TongJiTableQuery tongJiTableQuery) {
        this.tongJiTableQuery = tongJiTableQuery;
    }

    @Override
    public Integer call() throws Exception {
        synchronized (this){
            Integer totalCount = pageCountCallable.tWimMsgService.getCountByTableName(tongJiTableQuery);
            System.out.println(totalCount);
            return totalCount;
        }
    }

}
