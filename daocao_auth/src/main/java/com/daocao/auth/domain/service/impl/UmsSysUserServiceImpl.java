package com.daocao.auth.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daocao.auth.domain.entity.UmsMenu;
import com.daocao.auth.domain.entity.UmsSysUser;
import com.daocao.auth.domain.mapper.UmsMenuMapper;
import com.daocao.auth.domain.mapper.UmsSysUserMapper;
import com.daocao.auth.domain.service.IUmsMenuService;
import com.daocao.auth.domain.service.IUmsSysUserService;
import org.springframework.stereotype.Service;

@Service
public class UmsSysUserServiceImpl extends ServiceImpl<UmsSysUserMapper, UmsSysUser> implements IUmsSysUserService {
}
