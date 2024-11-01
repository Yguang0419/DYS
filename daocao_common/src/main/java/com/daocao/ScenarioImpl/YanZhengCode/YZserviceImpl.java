package com.daocao.ScenarioImpl.YanZhengCode;


import com.daocao.common.utils.redis.RedisCacheUtil;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class YZserviceImpl implements YZservice{
    @Autowired
    private ThreadService threadService;
    @Autowired
    private RedisCacheUtil redisCacheUtil;
    @Override
    public String send(String phone) {

        /*
        * 1.生成验证码
        * 2.调用短信平台，发送验证码（放入线程池）
        * 3.将验证码放到Redis中
        * */
        int anInt = RandomUtils.nextInt(100000, 999999);
        //调用用短信平台发送短信
        //放入线程池执行
        threadService.send(phone,anInt);
        //存入redis
        //......
        System.out.println("等待验证码中-----------------------");
        return "success";
    }
}
