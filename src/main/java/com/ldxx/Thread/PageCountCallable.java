package com.ldxx.Thread;

import com.ldxx.dao.tWimMsgDao;
import com.ldxx.service.tWimMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Callable;

@Component
public class PageCountCallable implements Callable<Integer> {

    @Autowired
    public tWimMsgService tWimMsgService;

    public static PageCountCallable pageCountCallable;


    @PostConstruct
    public void init(){
        pageCountCallable = this;
        pageCountCallable.tWimMsgService = this.tWimMsgService;
    }


    @Override
    public Integer call() throws Exception {
        synchronized (this){
            Integer totalCount = pageCountCallable.tWimMsgService.getCountByTableName("t_vehicle_msg");
            System.out.println(totalCount);
            return totalCount;
        }
    }

}
