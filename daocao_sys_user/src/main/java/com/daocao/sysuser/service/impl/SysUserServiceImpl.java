package com.daocao.sysuser.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.daocao.common.constants.HttpStatus;
import com.daocao.common.domain.entity.UmsSysUser;

import com.daocao.common.exception.ServiceException;
import com.daocao.common.mapper.UmsSysUserMapper;
import com.daocao.common.utils.security.DaoCaoSecurityUtil;
import com.daocao.sysuser.domain.vo.UserInfoVO;
import com.daocao.sysuser.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @date 石添
 * @date 2023/12/24
 */
@Service
@Slf4j
public class SysUserServiceImpl implements ISysUserService {

    private final UmsSysUserMapper sysUserMapper;

    public SysUserServiceImpl(UmsSysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    @Override
    public UserInfoVO searchUserInfo() {
        // 获取用户id
        Long userId = DaoCaoSecurityUtil.getUserId();
        UmsSysUser umsSysUser = sysUserMapper.selectById(userId);
        if(ObjectUtil.isNull(umsSysUser)) {
            throw new ServiceException(HttpStatus.FORBIDDEN,"");
        }

        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtil.copyProperties(umsSysUser,userInfoVO);
        return userInfoVO;
    }
}
