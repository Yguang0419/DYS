package com.daocao.auth.controller;


import com.daocao.common.response.DaocaoResult;
import com.daocao.common.service.IDysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description 用于前台登录验证
 * @author Yg
 * @create 2024-07-21 15:37
 */
@Slf4j
@RestController
@RequestMapping("dys")
public class DysAuthoController {
    @Autowired
    private IDysUserService DysUserService;

    @PostMapping("/login")
    public DaocaoResult dysUserLogin(String account,String password){
//调用service层登录方法
        log.info("账户名：{}，密码：{}", account,password);
        System.out.println("msg = " + account);
        String msg = DysUserService.login(account, password);
        return DaocaoResult.success(msg);
    }
}
