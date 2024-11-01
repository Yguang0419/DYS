package com.daocao.ScenarioImpl.RedisDemo.XueBeng;

import com.baomidou.mybatisplus.extension.service.IService;
import com.daocao.common.domain.op.Commodities;

public interface CacheAvalancheService extends IService<Commodities> {
    /*
    * 从缓存获取
    * */
    Commodities getDataFromCache(String key);
    /*
    * 从数据库获取
    * */
    Commodities getDataFromDB(String key);
    /*
    *
    * */

}
