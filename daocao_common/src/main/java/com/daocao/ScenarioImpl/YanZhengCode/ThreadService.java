package com.daocao.ScenarioImpl.YanZhengCode;


import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class ThreadService {
    /*：@Async("asyncServiceExecutor") 注解表明该方法是异步执行的，使用名为 asyncServiceExecutor 的线程池。*/
    @Async("asyncServiceExecutor")
    public void send(String phone, int code)
    {
        try {
            //发短信
            TimeUnit.SECONDS.sleep(5);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        // 获取并打印当前线程的名字
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName+"---发送短信成功:"+code);

    }
}
