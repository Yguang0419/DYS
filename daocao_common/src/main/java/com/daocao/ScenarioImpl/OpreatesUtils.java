package com.daocao.ScenarioImpl;

import com.daocao.ScenarioImpl.MiaoSha.MserviceImp;
import com.daocao.ScenarioImpl.YanZhengCode.YZserviceImpl;
import com.daocao.common.response.DaocaoResult;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@Component
public class OpreatesUtils {
    private final MserviceImp mservice;
    private final YZserviceImpl yzservice;



    public OpreatesUtils(MserviceImp mservice, YZserviceImpl yzservice) {
        this.mservice = mservice;
        this.yzservice = yzservice;
    }

    /*
     * 秒杀案例
     * */
    public DaocaoResult Miaosaha()
    {   mservice.MiaoSha();
        return DaocaoResult.success("秒杀成功");
    }
    /*
     * 发送短信验证码
     * */

    public DaocaoResult send(){
        String phone="13385405761";
        String RES = yzservice.send(phone);
        if(RES.equals("error"))
        {
            return DaocaoResult.error("发送失败");
        }
        return DaocaoResult.success("发送成功");
    }
}
