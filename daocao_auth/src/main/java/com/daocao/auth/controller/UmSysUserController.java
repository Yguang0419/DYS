package com.daocao.auth.controller;

import com.daocao.common.domain.entity.UmsSysUser;
import com.daocao.common.service.IUmsSysUserService;
import com.daocao.common.response.DaocaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ums/sysuser")
public class UmSysUserController {
//    注入service

    @Autowired
    private  IUmsSysUserService sysUserService;

//    public UmSysUserController(IUmsSysUserService sysUserService) {
//        this.sysUserService = sysUserService;
//    }

    /**
     * 新增用户列表接口
     */
    @PostMapping
    public DaocaoResult addSysUser(@RequestBody UmsSysUser user){
           boolean flag = sysUserService.save(user);
           if(flag){

               return DaocaoResult.success("新增用户成功");
           }

           return DaocaoResult.error("新增用户失败");

    }
    /**
     * 查询用户列表接口
     */
    @GetMapping
    public DaocaoResult searchList(){

        List<UmsSysUser> list = sysUserService.list();
        list.forEach(System.out::println);

        return DaocaoResult.success(list);
    }


}
