package com.daocao.common.service;

import com.daocao.common.domain.entity.DysTask;
import com.daocao.common.domain.vo.TaskVO;

import java.util.List;

public interface IDysTaskService {
//    根据用户查询所有的任务
    List<DysTask> serachAllTask();
//    新增任务
//    int addTask(TaskVO dysTask);
//    改变任务的状态
    int changeTaskState(Long TaskId);
}
