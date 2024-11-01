package com.daocao.sysuser.service;

import com.daocao.sysuser.domain.vo.UserInfoVO;

import java.util.List;

public interface ISysUserService {
    UserInfoVO searchUserInfo();
    List<UserInfoVO> getUsersNeedingReminder();

}
