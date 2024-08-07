package com.daocao.support.config.SpringSecerity;


import cn.hutool.core.util.ObjectUtil;
import com.daocao.common.domain.entity.UmsMenu;
import com.daocao.common.domain.entity.UmsRole;
import com.daocao.common.domain.entity.UmsSysUser;

import com.daocao.common.domain.vo.loginUserVO;
import com.daocao.common.mapper.UmsMenuMapper;
import com.daocao.common.mapper.UmsSysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
public class SysUserDetailsService implements UserDetailsService {
    private  final UmsSysUserMapper umsSysUserMapper;
    private final UmsMenuMapper umsMenuMapper;

    public SysUserDetailsService(UmsSysUserMapper umsSysUserMapper, UmsMenuMapper umsMenuMapper) {
        this.umsSysUserMapper = umsSysUserMapper;
        this.umsMenuMapper = umsMenuMapper;
    }

    /**
     * 实现方法 ，在此方法中根据账号查询用户，
     * 账号：用户名，手机号，邮箱；通过正则表达式判断账号类型
     * @param account
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {

        //验证账号类型
        int accountType=0;
        //根据账号查询用户，根据角色查权限，联查试试最好不要超过三张表
        UmsSysUser sysUser= umsSysUserMapper.selectUserByUsername(account,accountType);
//        根据角色查询权限
        //获取所有的角色ID
        if(ObjectUtil.isNotNull(sysUser)){
            List<UmsRole> roleList = sysUser.getRoleList();
//            取出id
            List<Long> roleIds = roleList.stream().map(UmsRole::getRoleId).collect(Collectors.toList());
            log.info("角色id=======>:{}", roleIds);
//            查询所有的菜单
            List<UmsMenu> MenuList = umsMenuMapper.selectByRoleIds(roleIds);
//            获取MenuList权限字段
            List<String> perms = MenuList.stream().map(UmsMenu::getPerms).collect(Collectors.toList());
            log.info("权限=======>:{}", perms);
            sysUser.setPerms(perms);

        }
        loginUserVO userVO = new loginUserVO();
        userVO.setUmsSysUser(sysUser);
        userVO.setId(sysUser.getId());

        return userVO;
    }
}
