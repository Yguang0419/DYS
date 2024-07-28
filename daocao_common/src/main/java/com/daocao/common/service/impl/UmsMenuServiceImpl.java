package com.daocao.common.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daocao.common.constants.HttpStatus;
import com.daocao.common.domain.entity.UmsMenu;
import com.daocao.common.domain.entity.UmsRole;
import com.daocao.common.domain.vo.RouterVO;
import com.daocao.common.exception.ServiceException;
import com.daocao.common.mapper.UmsMenuMapper;
import com.daocao.common.mapper.UmsRoleMapper;
import com.daocao.common.service.IUmsMenuService;
import com.daocao.common.utils.security.DaoCaoSecurityUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UmsMenuServiceImpl extends ServiceImpl<UmsMenuMapper, UmsMenu> implements IUmsMenuService {
    private final UmsRoleMapper roleMapper;

    public UmsMenuServiceImpl(UmsRoleMapper umsRoleMapper) {
        this.roleMapper = umsRoleMapper;
    }

    //   获取个人菜单列表
    @Override
    public List<RouterVO> searchSelfMenu() {
        Long userId = DaoCaoSecurityUtil.getUserId();
        List<Long> roles= roleMapper.selectByUserId(userId);
        List<UmsMenu> menuList = baseMapper.selectByRoleIds(roles);
//        通过递归设置菜单的数据树
// 获取所有一级菜单---》遍历一级菜单，获取他所有的子元素
        List<RouterVO> router = getRouter(menuList);
        router.forEach(System.out::println);
        return router;
    }
    /**
     * 查看所有的菜单
     * @param
     * @return
     */
    @Override
    public List<RouterVO> selectMenuList() {
        List<UmsMenu> menuList = baseMapper.selectList(null);
//        渲染children
        List<RouterVO> router = getRouter(menuList);
        return router;
    }

    /**
     * 添加菜单
     * @param umsMenu
     * @return
     */
    @Override
    public int saveMenu(UmsMenu umsMenu) {
//        判断是否登录
        String creator = DaoCaoSecurityUtil.getUsername();
        umsMenu.setCreator(creator);
        umsMenu.setUpdater(creator);
//        判断是否存在
        Long parentId = umsMenu.getParentId();
        String menuName = umsMenu.getMenuName();
        String path = umsMenu.getPath();
        LambdaQueryWrapper<UmsMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UmsMenu::getParentId,parentId).eq(UmsMenu::getMenuName,menuName).or().eq(UmsMenu::getPath,path);

        Long count = baseMapper.selectCount(wrapper);
        if(count>0){
          throw new ServiceException(HttpStatus.ERROR,"菜单已存在");
        }

        return baseMapper.insert(umsMenu);
    }

    /**
     * 根据id查询详情
     * @param id
     * @return
     */
    @Override
    public UmsMenu searchInfo(Long id) {
        UmsMenu menu = baseMapper.selectById(id);
        return menu;
    }


    /**
     * 修改菜单
     * @param umsMenu
     * @return
     */
    @Override
    public int updateMenu(UmsMenu umsMenu) {
        //        判断是否登录
        String creator = DaoCaoSecurityUtil.getUsername();
        umsMenu.setUpdater(creator);
//        判断是否存在
        Long parentId = umsMenu.getParentId();
        String menuName = umsMenu.getMenuName();
        String path = umsMenu.getPath();
        LambdaQueryWrapper<UmsMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UmsMenu::getParentId,parentId).eq(UmsMenu::getMenuName,menuName).or().eq(UmsMenu::getPath,path);

        Long count = baseMapper.selectCount(wrapper);
        if(count>0){
            throw new ServiceException(HttpStatus.ERROR,"菜单已存在");
        }

        return baseMapper.updateById(umsMenu);
    }

    @Override
    public int removeMenu(Long[] ids) {
        int num = baseMapper.deleteBatchIds(Arrays.asList(ids));
        return num;
    }

    private List<RouterVO> getRouter( List<UmsMenu> menuList){
        ArrayList<RouterVO> routerVOS = new ArrayList<>();
//        首先获取一级菜单路由
        List<UmsMenu> parentMenu = menuList.stream().filter(item -> item.getParentId() == 0).collect(Collectors.toList());
//转换对象
        for(UmsMenu menu : parentMenu){
            RouterVO routerVO = new RouterVO();
            BeanUtil.copyProperties(menu,routerVO);
            routerVOS.add(routerVO);

        }
//        循环一级路由，获取所有子菜单
        for (RouterVO routerVO : routerVOS) {
//            获取所有的子节点
            List<RouterVO> childrenList = buildTree(menuList, routerVO.getId());
            routerVO.setChildren(childrenList);
        }
        return routerVOS;
    }
//获取所有子节点
    private List<RouterVO> buildTree(List<UmsMenu> allMenu ,Long parentId){
        ArrayList<RouterVO> childrenList = new ArrayList<>();
        for (UmsMenu menu : allMenu) {
            if(menu.getParentId() .equals(parentId)){
                RouterVO routerVO = new RouterVO();
                BeanUtil.copyProperties(menu,routerVO);
                childrenList.add(routerVO);
            }
        }
        for (RouterVO childItem : childrenList) {
            childItem.setChildren(buildTree(allMenu,childItem.getId()));
        }
        return childrenList;

    }
}
