package com.daocao.ScenarioImpl.RedisDemo.XueBeng;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daocao.common.domain.op.Commodities;
import com.daocao.common.mapper.CommoditiesMapper;
import com.daocao.common.utils.redis.RedisCacheUtil;


import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CacheAvalancheImpl extends ServiceImpl<com.daocao.common.mapper.CommoditiesMapper,Commodities> implements CacheAvalancheService {

    private final RedisCacheUtil redisCacheUtil;

    private final CommoditiesMapper CommoditiesMapper;

    public CacheAvalancheImpl(RedisCacheUtil redisCacheUtil, CommoditiesMapper mapper) {
        this.redisCacheUtil = redisCacheUtil;
        this.CommoditiesMapper = mapper;
    }

    @Override
    public Commodities getDataFromCache(String key) {
//
        Commodities cacheObject = redisCacheUtil.getCacheObject(key);
        if (cacheObject == null) {
            // 模拟从数据库获取数据
            cacheObject = getDataFromDB(key);
            // 设置缓存，所有缓存项设置相同的过期时间
            redisCacheUtil.setCacheObject(key, cacheObject, 60, TimeUnit.SECONDS);
        }
        return null;
    }

    @Override
    public Commodities getDataFromDB(String key) {
        // 模拟从数据库获取数据
        Commodities commodities = null;
        try {
            //从数据库查数据
            commodities = CommoditiesMapper.selectById(key);
            Thread.sleep(1000); // 模拟数据库查询延迟
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return commodities;
    }

}
