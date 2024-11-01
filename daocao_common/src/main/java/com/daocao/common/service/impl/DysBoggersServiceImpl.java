package com.daocao.common.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daocao.common.constants.CacheConstants;
import com.daocao.common.domain.entity.DysBoggers;
import com.daocao.common.domain.entity.DysUser;
import com.daocao.common.mapper.DysBoggersMapper;
import com.daocao.common.service.IDysBoggersService;
import com.daocao.common.utils.redis.RedisCacheUtil;
import com.daocao.common.utils.security.DaoCaoSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DysBoggersServiceImpl  extends ServiceImpl<DysBoggersMapper, DysBoggers> implements IDysBoggersService {
    @Autowired
    private DysBoggersMapper dysBoggersMapper;
    @Autowired
    private RedisCacheUtil redisCacheUtil;
    /**
     * 根据用户id查询其所有的文章
     * @return
     */
    @Override
    public List<DysBoggers> searchBoggersById() {

        Long AuthorId = DaoCaoSecurityUtil.getUserId();

        LambdaQueryWrapper<DysBoggers> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DysBoggers::getBoggerAuthor,AuthorId);
        List<DysBoggers> dysBoggers = dysBoggersMapper.selectList(wrapper);
        return dysBoggers;
    }

    /**
     * 根据文章id查询其文章详情
     * @param BoggerId
     * @return
     */
    @Override
    public DysBoggers searchOneBogger(Long BoggerId) {
        LambdaQueryWrapper<DysBoggers> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DysBoggers::getBoggerId,BoggerId);
        DysBoggers dysBoggers = dysBoggersMapper.selectOne(wrapper);
        return dysBoggers;
    }

    @Override
    public int addBoggers(DysBoggers dysBoggers) {
        Date date = new Date();
        long timestamp = date.getTime();
        dysBoggers.setBoggerId(timestamp);
        LambdaQueryWrapper<DysBoggers> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DysBoggers::getBoggerAuthor,dysBoggers.getBoggerAuthor());
        int insert = dysBoggersMapper.insert(dysBoggers);
        return insert;
    }



    /*查询阅读量*/
    @Override
    public int getView(Long BoggerId) {
        Object value = redisCacheUtil.getCacheObject(CacheConstants.BOGGERS_KEY + BoggerId);
        // 1. 从缓存中查找
        if (value != null) {
            // 1.1: 存在，直接返回
            if (value instanceof Integer) {
                // 安全地将 Long 转换为 int
                return (Integer) value;
            } else {
                // 处理类型不匹配的情况
                throw new ClassCastException("Cached value is not of type Long");
            }
        } else {
            // 1.2: 不存在，从数据库中查找，并写入缓存
            DysBoggers dysBoggers = dysBoggersMapper.selectById(BoggerId);
            if (dysBoggers == null) {
                // 处理数据库中不存在的情况
                // 可以选择返回默认值或抛出异常
                return 0; // 返回默认值
                // 或者
                // throw new NoSuchElementException("Bogger with ID " + BoggerId + " not found");
            }
            int viewCount = dysBoggers.getViewCount();
            redisCacheUtil.setCacheObject(CacheConstants.BOGGERS_KEY + BoggerId, viewCount);
            return viewCount;
        }
    }

    /*阅读量+1*/
    @Override
    public boolean addView(Long boggerId) {
        try {
            // 从缓存中查找
            String cacheKey = String.format("%s%s", CacheConstants.BOGGERS_KEY, boggerId);
            Integer readCount = redisCacheUtil.incrementReadCount(cacheKey);
//            缓存中存在，则更新数据库和缓存
//            TODO 这一段要考虑并发情况，（待优化，不是强一致性，定时更新而不必每次更新）
            if (readCount != null) {
                DysBoggers dysBoggers = dysBoggersMapper.selectById(boggerId);
                if (dysBoggers != null) {
                    dysBoggers.setViewCount(readCount);
                    dysBoggersMapper.updateById(dysBoggers);
                    // 更新缓存
                    redisCacheUtil.setCacheObject(cacheKey, dysBoggers.getViewCount());
                }
            }
//            不用判断，是不是在缓存中，因为想要使阅读量＋1.那么肯定就已经点开页面了，也就代表着已经从数据库中获取到了
            return true;
        } catch (Exception e) {
            // 记录异常日志
            log.error("Error in addView: ", e);
            return false;
        }
    }
}
