package com.daocao.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.daocao.common.domain.dto.LoginDto;
import com.daocao.common.domain.entity.DysUser;

public interface IDysUserService  {
    String login(String account,String password);
}
