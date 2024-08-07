package com.daocao.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.daocao.common.domain.entity.DysUser;
import com.daocao.common.domain.entity.UmsMenu;
import com.daocao.common.mapper.DysUserMapper;
import com.daocao.common.service.IDysUserService;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DysUserServiceImpl implements IDysUserService {
    @Autowired
    private DysUserMapper dysUserMapper;
    @Override
    public String login(String account,String password) {

//       从数据库进行对比
        LambdaQueryWrapper<DysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DysUser::getAccount, account);
        DysUser user = dysUserMapper.selectOne(wrapper);
        if(user == null){
            return "用户不存在";
        } else if (user.getPassword().equals(password)) {
            return "登录成功";
        }else {
            return "密码错误";
        }

    }
}
