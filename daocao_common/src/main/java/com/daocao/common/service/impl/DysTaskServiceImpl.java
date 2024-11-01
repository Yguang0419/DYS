package com.daocao.common.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daocao.common.constants.CacheConstants;
import com.daocao.common.domain.entity.DysBoggers;
import com.daocao.common.domain.vo.TaskVO;
import com.daocao.common.mapper.DysTaskMapper;
import com.daocao.common.service.IDysTaskService;
import com.daocao.common.domain.entity.DysTask;
import com.daocao.common.utils.redis.RedisCacheUtil;
import com.daocao.common.utils.security.DaoCaoSecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Slf4j
@Service
public class DysTaskServiceImpl extends ServiceImpl<DysTaskMapper, DysTask> implements IDysTaskService {
@Autowired
private DysTaskMapper dysTaskMapper;
    @Autowired
    private RedisCacheUtil redisCacheUtil;

    /**
     * 查询用户的任务
     * @param
     * @return
     */
    @Override
    public List<TaskVO> serachAllTask() {
        Long UserId = DaoCaoSecurityUtil.getUserId();
        LocalDate now = LocalDate.now();
        String today =now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
       /*从redis里拿数据*/
        System.out.println("（查询任务）-----------------》当前日期："+today+"用户id："+UserId);
        List<TaskVO> dysTasks = redisCacheUtil.getCacheObject(CacheConstants.TASK_KEY + today + UserId);

        return dysTasks;
    }



    @Override
    public int changeTaskState(Long TaskId) {
        int count = dysTaskMapper.changeTaskState(TaskId);

        return count;
    }
}
