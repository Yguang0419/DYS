package com.daocao.auth.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daocao.auth.domain.entity.UmsRole;
import com.daocao.auth.domain.mapper.UmsRoleMapper;
import com.daocao.auth.domain.service.IUmsRoleService;
import org.springframework.stereotype.Service;

@Service
public class UmsRoleServiceImpl extends ServiceImpl<UmsRoleMapper, UmsRole> implements IUmsRoleService {
}
