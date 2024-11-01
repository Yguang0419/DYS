package com.daocao.sysuser.controller;


import com.daocao.common.response.DaocaoResult;
import com.daocao.sysuser.service.ISysUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date 石添
 * @date 2023/12/24
 */
@RestController
@RequestMapping("sys/user")
public class UserController {

    private final ISysUserService userService;

    public UserController(ISysUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/info")
    public DaocaoResult searchUserInfo() {
        return DaocaoResult.success(userService.searchUserInfo());
    }

}
