package com.ldxx.controller;

import com.ldxx.service.Impl.RedisServiceImpl;
import com.ldxx.service.tWimMsgService;
import com.ldxx.vo.tWimMsgVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("JedisController")
public class JedisController {
    @Resource
    RedisServiceImpl redisServiceImpl;

    @Autowired
    tWimMsgService tWimMsgService;

    @RequestMapping("/test")
    public tWimMsgVo jedisTest() {
        tWimMsgVo tWimMsgVo = new tWimMsgVo();
        tWimMsgVo.setDirection(1);
        tWimMsgVo.setDirectionName("上行");
        redisServiceImpl.set("vo1", tWimMsgVo);
        tWimMsgVo tWimMsgVo1 = (com.ldxx.vo.tWimMsgVo) redisServiceImpl.get("vo1");
        System.out.println(tWimMsgVo1.getDirectionName());
        return tWimMsgVo1;
    }
}
