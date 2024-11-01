package com.daocao.support.filter;

import cn.hutool.core.util.ObjectUtil;
import com.daocao.common.domain.vo.loginUserVO;
import com.daocao.common.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class JwtAuthticationFilter  extends OncePerRequestFilter {
    /**
     * 该接口会在请求前执行一次，获取request中的数据，其中token就在其中
     * 获取token，根据token从redis中获取数据
     */
    private final JwtUtils JwtUtils;

    public JwtAuthticationFilter(com.daocao.common.utils.JwtUtils jwtUtils) {
        JwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        获取登录用户
        loginUserVO UserVo =  (loginUserVO) JwtUtils.getLoginUser(request);
//        判断是否为null
        if(ObjectUtil.isNotNull(UserVo)) {
//            鉴权，跳转的时候需要访问/index页面
            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(UserVo, null, UserVo.getAuthorities());
//            将用户信息存储到SecurityContex中，将将用户信息存储到SecurityContex中存储到将用户信息存储到SecurityContex中将用户信息存储到SecurityContex中Holder中
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
//        放行,交由后边的过滤器执行
        filterChain.doFilter(request,response);

        }

}
