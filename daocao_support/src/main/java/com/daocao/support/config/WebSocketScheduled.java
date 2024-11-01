package com.daocao.support.config;
import com.daocao.common.constants.CacheConstants;
import com.daocao.common.domain.vo.TaskVO;
import com.daocao.common.utils.redis.RedisCacheUtil;
import com.daocao.common.utils.security.DaoCaoSecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@EnableScheduling
@Slf4j
@Component
public class WebSocketScheduled {
    @Autowired
    private WebSocket webSocket;
    @Autowired
    private RedisCacheUtil redisCacheUtil;
    /*
    * 任务清单提醒
    * */
    @Scheduled(cron = "0/45 * * * * ?")
    public void sendMessageforTask() {
        Long UserId = 1L;
        LocalDate now = LocalDate.now();
        String today =now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        /*从redis里拿数据*/
        log.info("（查询任务）-----------------》当前日期：["+today+"]用户id：["+UserId+"]");
        List<TaskVO> dysTasks = redisCacheUtil.getCacheObject(CacheConstants.TASK_KEY + today + UserId);
        /*查看哪个任务开启时间到了*/
        if (dysTasks != null && !dysTasks.isEmpty()) {
            for (TaskVO taskVO : dysTasks) {
                LocalDateTime taskStart = taskVO.getTaskStartTime().toLocalDateTime();
                LocalDateTime cur = LocalDateTime.now();
                // 计算两个时间之间的差异
                Duration duration = Duration.between(cur, taskStart);
                long seconds = duration.getSeconds();
                // 判断差异是否小于一分钟
                // 判断差异是否小于一分钟且当前时间未超过任务开始时间
                if (seconds >= 0 && seconds <= 60) {
                    log.info("任务提醒：" + taskVO.getTaskName()+"-id["+taskVO.getTaskId()+"]"+"已开启！");
                    webSocket.sendOneMessage(String.valueOf(UserId), String.valueOf(taskVO.getTaskId()));
                } else if (seconds < 0) {
//                    log.info("任务提醒：" + taskVO.getTaskName()+"-id["+taskVO.getTaskId()+"]"+"已过期！");
                }
            }
        }


        log.info("账号：["+UserId+"] 当前暂无任务");
    }
}