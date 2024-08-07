package com.daocao.common.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daocao.common.domain.entity.DysBoggers;
import com.daocao.common.domain.vo.TaskVO;
import com.daocao.common.mapper.DysTaskMapper;
import com.daocao.common.service.IDysTaskService;
import com.daocao.common.domain.entity.DysTask;
import com.daocao.common.utils.security.DaoCaoSecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
@Slf4j
@Service
public class DysTaskServiceImpl extends ServiceImpl<DysTaskMapper, DysTask> implements IDysTaskService {
@Autowired
private DysTaskMapper dysTaskMapper;

    /**
     * 查询用户的任务
     * @param UserId
     * @return
     */
    @Override
    public List<DysTask> serachAllTask() {
        Long UserId = DaoCaoSecurityUtil.getUserId();
        LocalDate today = LocalDate.now();
        LocalDateTime dayStart = LocalDateTime.of(today, LocalTime.MIN);
        LocalDateTime dayEnd = LocalDateTime.of(today, LocalTime.MAX).minusNanos(1);
        log.info("dayStart: {}, dayEnd: {}", dayStart, dayEnd);

        LambdaQueryWrapper<DysTask> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DysTask::getUserId, UserId)
                .between(DysTask::getTaskStartTime, dayStart, dayEnd)
                .orderByAsc(DysTask::getTaskStartTime); // 按照时间升序排列

        List<DysTask> dysTasks = dysTaskMapper.selectList(wrapper);
        return dysTasks;
    }



    @Override
    public int changeTaskState(Long TaskId) {
        int count = dysTaskMapper.changeTaskState(TaskId);

        return count;
    }
}
