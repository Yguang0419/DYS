package com.daocao.controller;


import com.daocao.ScenarioImpl.MiaoSha.MserviceImp;
import com.daocao.ScenarioImpl.OpreatesUtils;
import com.daocao.ScenarioImpl.YanZhengCode.YZserviceImpl;
import com.daocao.common.response.DaocaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
* 操作案例demo集合
* */
@RestController
@RequestMapping("/op")
public class OpreatesListController {
    @Autowired
    private OpreatesUtils opreatesUtils;
    /*
    * 根据Opid来转发对应的操作
    * */
    @GetMapping("/{Opid}")
    public DaocaoResult getOpId(@PathVariable int Opid){
        if (Opid==20241024){
            //        执行验证码操作
            return opreatesUtils.send();
        } else if (Opid==11586) {
            //        执行秒杀操作
            return opreatesUtils.Miaosaha();
            
        }
        return DaocaoResult.error("没有对应的操作");
    }

}
