package com.daocao.common.utils.security;

import cn.hutool.core.util.ObjectUtil;
import com.daocao.common.constants.HttpStatus;

import com.daocao.common.domain.vo.loginUserVO;
import com.daocao.common.exception.ServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @date 石添
 * @date 2023/12/14
 * 获取登陆的用户信息
 */
public class DaoCaoSecurityUtil {

    /**
     * 获取  Authentication
     * @return
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户
     */
    public static loginUserVO getLoginUser() {
        return (loginUserVO) getAuthentication().getPrincipal();
    }

    /**
     * 获取用户id
     * @return
     */
    public static Long getUserId() {
        Long userId = getLoginUser().getId();
        if(ObjectUtil.isNull(userId)) {
            throw new ServiceException(HttpStatus.FORBIDDEN,"");
        }
        return userId;
    }

    /**
     * 获取用户名
     * @return
     */
    public static String getUsername() {
        String username = getLoginUser().getUsername();
        if(ObjectUtil.isNull(username)) {
            throw new ServiceException(HttpStatus.FORBIDDEN,"");
        }
        return username;
    }

}
