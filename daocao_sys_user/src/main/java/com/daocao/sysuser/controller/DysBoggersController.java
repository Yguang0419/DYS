package com.daocao.sysuser.controller;

import com.daocao.common.domain.entity.DysBoggers;
import com.daocao.common.response.DaocaoResult;
import com.daocao.common.service.IDysBoggersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dys/boggers")
public class DysBoggersController {
    @Autowired
    private  final IDysBoggersService dysBoggersService;

    public DysBoggersController(IDysBoggersService dysBoggersService) {
        this.dysBoggersService = dysBoggersService;
    }

    /**
     * 获取该用户的所有博客,获取当前登录的用户id,都在SecurityContextHolder中存储
     */
    @GetMapping
    public DaocaoResult serachAllBoggers(){
        List<DysBoggers> boggersList = dysBoggersService.searchBoggersById();
        return DaocaoResult.success(boggersList);
    }
    @GetMapping("Deatail/{BoggerId}")
    public DaocaoResult searchOneBogger(@PathVariable("BoggerId") Long BoggerId){
        DysBoggers boggersList = dysBoggersService.searchOneBogger(BoggerId);
        return DaocaoResult.success(boggersList);
    }
    /**
     * 新增博客
     */
    @PostMapping
    public DaocaoResult addBoggers(@RequestBody DysBoggers dysBoggers){
        System.out.println(dysBoggers);
        int i = dysBoggersService.addBoggers(dysBoggers);
        if(i==1){
            return DaocaoResult.success("发布成功");
        }
       else {
           return DaocaoResult.error("发布失败");
        }
    }
}