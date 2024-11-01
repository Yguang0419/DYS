package com.daocao.common.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.daocao.common.constants.HttpStatus;
import com.daocao.common.domain.dto.LoginDto;
import com.daocao.common.domain.vo.loginUserVO;
import com.daocao.common.exception.ServiceException;
import com.daocao.common.utils.JwtUtils;
import com.daocao.common.service.IAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements IAuthService {
    private  final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
//    如果类中有多个构造器，并且希望Spring使用其中一个特定的构造器进行依赖注入，那么你需要在那个构造器上添加 @Autowired 注解。
    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtUtils JwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = JwtUtils;
    }

    /**
     * login方法
     * @param loginDto
     * @return
     */
    @Override
    public String login(LoginDto loginDto) {
//        用于用户名和密码认证的标准令牌
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(loginDto.getAccount(), loginDto.getPassword());

//        传入前面创建的认证令牌
    try {
//        调用了loaduserByUsername方法，返回一个UserDetails对象
        Authentication authenticate = authenticationManager.authenticate(authentication);
        loginUserVO loginUser = (loginUserVO) authenticate.getPrincipal();
//        不为空，生成token
        if(ObjectUtil.isNull(loginUser)){
            log.info("认证失败");
           throw new ServiceException(HttpStatus.UNAUTHORIZED,"认证失败");
        }
//        用jwt创建token
        String token = jwtUtils.createToken(loginUser);

        log.info("jwt创建token:{}",token);
        return token;

        } catch (BadCredentialsException e) {
            // 处理凭据错误的情况，比如密码不对
            log.error("Bad credentials");
            return null;
        } catch (DisabledException e) {
            // 处理账号被禁用的情况
            log.warn("User account is disabled");
            return null;
         }
    }


}
