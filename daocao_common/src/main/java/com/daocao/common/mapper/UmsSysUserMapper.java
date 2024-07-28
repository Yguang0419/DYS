package com.daocao.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.daocao.auth.domain.entity.UmsSysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UmsSysUserMapper extends BaseMapper<UmsSysUser> {
    UmsSysUser selectUserByUsername(@Param("account") String account,@Param("accountType") int accountType);
}
