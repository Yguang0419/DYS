package com.daocao.ScenarioImpl.RedisDemo.XueBeng.DealWay;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daocao.ScenarioImpl.RedisDemo.XueBeng.CacheAvalancheService;
import com.daocao.common.mapper.CommoditiesMapper;
import com.daocao.common.domain.op.Commodities;
import com.daocao.common.utils.redis.RedisCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/*
* 3.1 加锁
    在缓存失效时，对缓存更新操作加锁，确保同一时间只有一个线程去数据库查询数据。
*
* */
@Service
public class Dealwith1 extends ServiceImpl<CommoditiesMapper, Commodities> implements CacheAvalancheService {
    @Autowired
    private RedisCacheUtil redisCacheUtil;
    @Autowired
    private CommoditiesMapper Mapper;
    @Override
    public Commodities getDataFromCache(String key) {
        Commodities cacheObject = redisCacheUtil.getCacheObject(key);
        if (cacheObject == null) {
            synchronized (this) {
                cacheObject = redisCacheUtil.getCacheObject(key);
                if (cacheObject == null) {
                    // 模拟从数据库获取数据
                    cacheObject = getDataFromDB(key);
                    // 设置缓存，所有缓存项设置相同的过期时间
                    redisCacheUtil.setCacheObject(key, cacheObject, 60, TimeUnit.SECONDS);
                }
            }
        }
        return null;
    }

    @Override
    public Commodities getDataFromDB(String key) {
        // 模拟从数据库获取数据
        Commodities commodities = null;
        try {
            //从数据库查数据
            commodities = Mapper.selectById(key);
            Thread.sleep(1000); // 模拟数据库查询延迟
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return commodities;
    }
}
