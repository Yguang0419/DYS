package com.daocao.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

//import com.daocao.common.domain.vo.TaskVO;

import com.daocao.common.constants.CacheConstants;
import com.daocao.common.domain.entity.DysTask;
import com.daocao.common.domain.vo.TaskVO;
import com.daocao.common.mapper.TaskVOMapper;

import com.daocao.common.service.TaskVOService;
import com.daocao.common.utils.redis.RedisCacheUtil;
import com.daocao.common.utils.security.DaoCaoSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskVOServiceImpl extends ServiceImpl<TaskVOMapper, TaskVO> implements TaskVOService {
    @Autowired
    private TaskVOMapper taskVOMapper;
    @Autowired
    private RedisCacheUtil redisCacheUtil;
    /**
     * 添加任务.
     * @param dysTask
     * @return
     */
    @Override
    public int addTask(TaskVO dysTask) {
        // 获取用户id
        Long userId = DaoCaoSecurityUtil.getUserId();
        dysTask.setUserId(userId);
//        保存并返回dys
        int insert = taskVOMapper.insert(dysTask);
        //重新查询今天的任务
        LocalDate now = LocalDate.now();
        LocalDateTime dayStart = LocalDateTime.of(now, LocalTime.MIN);
        LocalDateTime dayEnd = LocalDateTime.of(now, LocalTime.MAX).minusNanos(1);

        LambdaQueryWrapper<TaskVO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TaskVO::getUserId, userId)
                .between(TaskVO::getTaskStartTime, dayStart, dayEnd)
                .orderByAsc(TaskVO::getTaskStartTime); // 按照时间升序排列
        List<TaskVO> alltaskVOS = taskVOMapper.selectList(wrapper);

        //获取当天而不是当时的的时间戳
        String today =now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println("（添加任务）---------------------》当前日期："+today+"用户id："+userId);
        redisCacheUtil.setCacheObject(CacheConstants.TASK_KEY+today+userId,alltaskVOS);
        return insert;
    }

}
