package com.daocao.sysuser.controller;

import com.daocao.common.domain.entity.DysTask;
import com.daocao.common.domain.vo.TaskVO;
import com.daocao.common.response.DaocaoResult;
import com.daocao.common.service.IDysTaskService;
import com.daocao.common.service.TaskVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 处理关于任务清单的事宜,taskVO用于新增任务，task负责其他，
 */
@RestController

@RequestMapping("dys/task")
public class DysTaskController {
    @Autowired
    private final IDysTaskService dysTaskService;
    @Autowired
    private final TaskVOService taskVOService;


//TODO: taskVO用于新增任务，task负责其他，
    public DysTaskController(IDysTaskService dysTaskService, TaskVOService taskVOService) {
        this.dysTaskService = dysTaskService;
        this.taskVOService = taskVOService;
    }

    /**
     * 新增任务清单
     */
    @PostMapping
    public DaocaoResult SaveTask(@Validated @RequestBody TaskVO dysTask){
        System.out.println("dysTask ========= " + dysTask);
        int addTask = taskVOService.addTask(dysTask);
        if(addTask==1){
            return DaocaoResult.success("任务添加成功");
        }else {
            return DaocaoResult.error("任务添加失败");
        }
    }
    /**
     * 根据用户id查询任务清单,
     * 获取当前登录的用户id,都在SecurityContextHolder中存储
     */
    @GetMapping
    public DaocaoResult searchTask(){
        List<TaskVO> dysTasks = dysTaskService.serachAllTask();
        System.out.println("--------------------------------------->!!!!");
        return DaocaoResult.success(dysTasks);

    }
    /**
     * 改变任务状态，只允许从未完成改成已完成
     */
    @PostMapping("changeState/{TaskId}")
    public DaocaoResult changeTaskState(@PathVariable("TaskId") Long TaskId){
        int changeTaskState = dysTaskService.changeTaskState(TaskId);
        if(changeTaskState==1){
            return DaocaoResult.success("任务完成");
        }else {
            return DaocaoResult.error("系统异常");
        }
    }


}
