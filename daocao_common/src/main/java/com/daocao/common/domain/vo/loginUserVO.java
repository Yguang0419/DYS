package com.daocao.common.domain.vo;

import cn.hutool.core.util.ObjectUtil;
import com.daocao.common.domain.entity.UmsSysUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class loginUserVO implements UserDetails {
    private  Long id;
    private  String token;
    private  long loginTime;
    private UmsSysUser umsSysUser =new UmsSysUser();

    /**
     * 用户权限
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> perms = umsSysUser.getPerms();
//        判空，返回数据
        if(ObjectUtil.isNotEmpty(perms)){
            return perms.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        }

        return null;
    }

    @Override
    public String getPassword() {
        return umsSysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return umsSysUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return umsSysUser.getStatus() == 0;
    }

    @Override
    public boolean isAccountNonLocked() {
        return umsSysUser.getStatus() == 0;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return umsSysUser.getStatus() == 0;
    }

    @Override
    public boolean isEnabled() {
        return umsSysUser.getStatus() == 0;
    }
}
