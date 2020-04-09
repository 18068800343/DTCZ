package com.ldxx.Thread;

import com.ldxx.bean.TongJiTableQuery;
import com.ldxx.service.tWimMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Callable;

@Component
public class PageCountYiChangCallable implements Callable<Integer> {

    @Autowired
    public tWimMsgService tWimMsgService;

    public static PageCountYiChangCallable pageCountCallable;

    public  TongJiTableQuery tongJiTableQuery;

    @PostConstruct
    public void init(){
        pageCountCallable = this;
        pageCountCallable.tWimMsgService = this.tWimMsgService;
    }
    public PageCountYiChangCallable() {
    }

    public PageCountYiChangCallable(TongJiTableQuery tongJiTableQuery) {
        this.tongJiTableQuery = tongJiTableQuery;
    }

    @Override
    public Integer call() throws Exception {
        synchronized (this){
            Integer totalCount = pageCountCallable.tWimMsgService.getCountYiChangByTableName(tongJiTableQuery);
            System.out.println(totalCount);
            return totalCount;
        }
    }

}
