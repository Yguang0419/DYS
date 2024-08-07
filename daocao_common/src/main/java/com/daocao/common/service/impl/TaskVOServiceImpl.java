package com.daocao.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

//import com.daocao.common.domain.vo.TaskVO;

import com.daocao.common.domain.vo.TaskVO;
import com.daocao.common.mapper.TaskVOMapper;

import com.daocao.common.service.TaskVOService;
import com.daocao.common.utils.security.DaoCaoSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskVOServiceImpl extends ServiceImpl<TaskVOMapper, TaskVO> implements TaskVOService {
    @Autowired
    private TaskVOMapper taskVOMapper;
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
        return insert;
    }
}
