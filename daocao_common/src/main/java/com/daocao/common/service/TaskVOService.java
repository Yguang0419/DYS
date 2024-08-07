package com.daocao.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daocao.common.domain.entity.DysTask;
import com.daocao.common.domain.vo.TaskVO;
import com.daocao.common.mapper.DysTaskMapper;
import com.daocao.common.mapper.TaskVOMapper;

public interface TaskVOService extends IService<TaskVO> {
    //        新增任务
    int addTask(TaskVO dysTask);
}
