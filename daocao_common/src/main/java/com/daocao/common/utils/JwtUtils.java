package com.daocao.common.utils;


import cn.hutool.core.util.StrUtil;
import com.daocao.common.constants.CacheConstants;
import com.daocao.common.domain.entity.UmsSysUser;
import com.daocao.common.domain.vo.loginUserVO;
import com.daocao.common.utils.redis.RedisCacheUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 通过jwt生成token、解析、刷新token
 */
@Slf4j
@Component
public class JwtUtils {
    private String secret = "daocao";
    @Autowired
    private RedisCacheUtil redisCacheUtil;
//    创建token,会将用户数据存放到redis中以便方便实现单点登录，替人下线，查看在线用户等等
//    可以使用UUID当redis的key
    public  String createToken(loginUserVO loginUserVO) {

//        创建个map
        String token = UUID.randomUUID().toString().replaceAll("-", "");

//        将UUID存储到登录用户中，可以在后台系统中去根据token值获取redis中数据
        loginUserVO.setToken(token);
//        设置登录时间
        loginUserVO.setLoginTime(System.currentTimeMillis());
        HashMap<String, Object> Clams = new HashMap<>();
        Clams.put("token", token);
//       TODO 调用刷新token的方法
        refreshToken(loginUserVO);
//        生成token
        return Jwts.builder()
                .setClaims(Clams)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

    }
//    解析token,token:jwt字符串
    public Claims parseToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
    /**
     * 获取登录用户
     * 根据token，解析之后从redis中获取
     */
    public loginUserVO getLoginUser(HttpServletRequest request) {
//        通过jwt加密过的
        String token = request.getHeader("Daocao-Authonrization");
        log.info("token值：===========================》"+token);
        if(StrUtil.isNotEmpty(token)){

//        解析token
            Claims claims = parseToken(token);
            String parsseToken =(String) claims.get("token");

//        根据token从redis中获取数据
            loginUserVO UserVO=redisCacheUtil.getCacheObject(CacheConstants.LOGIN_USER_KEY + parsseToken);

//        获取登录时间
            long loginTime = UserVO.getLoginTime();
//        获取当前时间
            long currentTime = System.currentTimeMillis();
//        判断是否相差20分钟
            if ((currentTime - loginTime) > 20 * 60 * 1000) {
                refreshToken(UserVO);
            }
            return UserVO;
        }
        log.error("token ===============================>为空");
        return null;
    }
//    刷新token
    private void refreshToken(loginUserVO loginUserVO) {

//        将数据缓存到redis
        redisCacheUtil.setCacheObject(CacheConstants.LOGIN_USER_KEY + loginUserVO.getToken(), loginUserVO, 30, TimeUnit.MINUTES);
    }

}
