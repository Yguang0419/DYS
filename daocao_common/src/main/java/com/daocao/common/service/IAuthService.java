package com.daocao.common.service;

import com.daocao.common.domain.dto.LoginDto;

public interface IAuthService {
    String login(LoginDto loginDto);
}
