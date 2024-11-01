package com.daocao.auth.controller;

import com.daocao.common.domain.dto.LoginDto;
import com.daocao.common.service.IAuthService;
import com.daocao.common.response.DaocaoResult;
import com.daocao.support.OperationLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 认证接口
 */
@Slf4j
@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final IAuthService AuthService;

    public AuthController(IAuthService iAuthService) {
        this.AuthService = iAuthService;
    }

    /**
     * 系统用户登录
     * @return
     */
    @PostMapping("sys")
    @OperationLog
//    @RequestBody: 表示loginDto参数的值将从请求体中获取，并自动转换为LoginDto对象。
    public DaocaoResult sysLogin( @RequestBody LoginDto loginDto){
        log.info("auth/sys接口入参:{}",loginDto);
        String token = AuthService.login(loginDto);
        log.info("生成的token:{}",token);
        return DaocaoResult.success().put("token",token);
    }
//    @GetMapping
//    public String add(){
//        String password= passwordEncoder.encode("123456");
//        System.out.println(password);
//        return password;
//    }
}
