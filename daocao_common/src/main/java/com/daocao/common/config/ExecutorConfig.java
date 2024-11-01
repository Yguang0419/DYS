package com.daocao.common.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;


@Configuration
@EnableAsync
@Slf4j
public class ExecutorConfig {

    @Bean
    public Executor asyncServiceExecutor(){
        log.info("start asyncServiceExecutor");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        核心线程数
        executor.setCorePoolSize(5);
//        最大线程数
        executor.setMaxPoolSize(8);
//        队列大小
        executor.setQueueCapacity(5);
//        线程池中的先吃的前缀名称
        executor.setThreadNamePrefix("async-service-");
//        拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//        执行初始化
        executor.initialize();
        return executor;
    }
}
