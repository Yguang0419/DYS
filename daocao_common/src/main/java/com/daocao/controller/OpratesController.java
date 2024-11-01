package com.daocao.controller;
/*
* TODO 管理维护操作案例
* */

import com.daocao.common.domain.entity.Oprates;
import com.daocao.common.response.DaocaoResult;
import com.daocao.common.service.impl.OpratesImpl;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/oprates")
@RestController
public class OpratesController {

    private  final OpratesImpl opratesImpl;

    public OpratesController(OpratesImpl opratesImpl) {
        this.opratesImpl = opratesImpl;
    }
    /*获取全部操作demo*/
    @GetMapping("/getAllOp")
    public DaocaoResult getAllOp()
    {
        return DaocaoResult.success(opratesImpl.getAllOp());
    }


    /*新增操作demo*/
    @PostMapping("/addOp")
    public DaocaoResult addOp(@RequestBody Oprates op){
        System.out.println("新增："+op);
        boolean b = opratesImpl.addOp(op);
        return b?DaocaoResult.success("操作成功"):DaocaoResult.error();
    }

    /*修改操作demo*/
    @PostMapping("/upOp")
    public DaocaoResult modifyOp(Oprates op)
    {
        boolean b = opratesImpl.modifyOp(op);
        return b?DaocaoResult.success("操作成功"):DaocaoResult.error();
    }
}
