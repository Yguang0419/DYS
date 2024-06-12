package com.daocao.auth.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daocao.auth.domain.entity.UmsMenu;
import com.daocao.auth.domain.mapper.UmsMenuMapper;
import com.daocao.auth.domain.service.IUmsMenuService;
import org.springframework.stereotype.Service;

@Service
public class UmsMenuServiceImpl extends ServiceImpl<UmsMenuMapper, UmsMenu> implements IUmsMenuService {
}
