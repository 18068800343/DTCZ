package com.ldxx.Thread;

import com.ldxx.bean.TongJiTableQuery;
import com.ldxx.service.Impl.RedisServiceImpl;
import com.ldxx.service.tWimMsgService;
import com.ldxx.vo.tWimMsgVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.Callable;

@Component
public class ExportExcelCallable implements Callable<Integer> {

    @Autowired
    public tWimMsgService tWimMsgService;

    @Autowired
    RedisServiceImpl redisServiceImpl;

    public static ExportExcelCallable pageCountCallable;

    public  TongJiTableQuery tongJiTableQuery;

    @PostConstruct
    public void init(){
        pageCountCallable = this;
        pageCountCallable.tWimMsgService = this.tWimMsgService;
        pageCountCallable.redisServiceImpl = this.redisServiceImpl;
    }
    public ExportExcelCallable() {
    }

    public ExportExcelCallable(TongJiTableQuery tongJiTableQuery,RedisServiceImpl redisServiceImpl) {
        this.tongJiTableQuery = tongJiTableQuery;
        this.redisServiceImpl = redisServiceImpl;
    }

    @Override
    public Integer call() throws Exception {
        synchronized (this){

        }
        return null;
    }

}
