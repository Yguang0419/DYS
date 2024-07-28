package com.daocao.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daocao.common.domain.entity.UmsSysUser;
import com.daocao.common.mapper.UmsSysUserMapper;
import com.daocao.common.service.IUmsSysUserService;
import org.springframework.stereotype.Service;

@Service
public class UmsSysUserServiceImpl extends ServiceImpl<UmsSysUserMapper, UmsSysUser> implements IUmsSysUserService {
}
