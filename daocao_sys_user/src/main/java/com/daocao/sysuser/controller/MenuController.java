package com.daocao.sysuser.controller;

import com.daocao.common.domain.entity.UmsMenu;

import com.daocao.common.domain.vo.RouterVO;
import com.daocao.common.response.DaocaoResult;
import com.daocao.common.service.IUmsMenuService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @date 石添
 * @date 2023/12/14
 */
@RestController
@RequestMapping("sys/menu")
public class MenuController {

    private final IUmsMenuService menuService;

    public MenuController(IUmsMenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping
    public DaocaoResult addMenu(@Valid @RequestBody  UmsMenu umsMenu){
        int row = menuService.saveMenu(umsMenu);
        if (row > 0){
            return DaocaoResult.success();
        }
        return DaocaoResult.error("新增菜单失败");
    }

    /**
     * 修改
     */
    @PutMapping
    public DaocaoResult UpdateMenu(@Valid @RequestBody  UmsMenu umsMenu){
        int row = menuService.updateMenu(umsMenu);
        if (row > 0){
            return DaocaoResult.success();
        }
        return DaocaoResult.error("新增菜单失败");
    }


    /**
     * 查询自己的菜单
     */
    @GetMapping("self")
    public DaocaoResult searchSelfMenu() {
        // 获取当前登录的用户id,都在SecurityContextHolder中存储
        List<RouterVO> RouterVOList = menuService.searchSelfMenu();
        return DaocaoResult.success(RouterVOList);
    }
    /**
     * 查询所有的菜单
     */
    @GetMapping("list")
    public DaocaoResult list() {
        List<RouterVO> routerVOList = menuService.selectMenuList();
        return DaocaoResult.success(routerVOList);
    }
    /**
     * 删除数据
     */
    @DeleteMapping
    public DaocaoResult removeMenu(@RequestBody Long[] ids){
        int row =menuService.removeMenu(ids);
        if(row>0){
            return DaocaoResult.success();
        }
        return DaocaoResult.error();
    }

    /**
     * 根据id查询详情
     */
    @GetMapping("{id}")
    public DaocaoResult searchInfo(@PathVariable("id") Long id){
        UmsMenu menu =menuService.searchInfo(id);
        return DaocaoResult.success(menu);
    }

}
