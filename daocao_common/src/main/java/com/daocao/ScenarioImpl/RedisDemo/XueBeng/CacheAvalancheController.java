package com.daocao.ScenarioImpl.RedisDemo.XueBeng;

import com.daocao.common.response.DaocaoResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
*
* 缓存雪崩是指在某个时间点，大量缓存数据同时失效，
* 导致大量请求直接打到数据库，造成数据库压力过大甚至崩溃。
* */
@RestController
@RequestMapping("/cache")
public class CacheAvalancheController {
    private final CacheAvalancheImpl XBService;

    public CacheAvalancheController(CacheAvalancheImpl xbService) {
        XBService = xbService;
    }


    @PostMapping("/xb")
    public DaocaoResult getDataFromCache(String key){
            return DaocaoResult.success();
    }

}
