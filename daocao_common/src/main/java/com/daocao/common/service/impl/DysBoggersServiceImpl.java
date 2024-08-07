package com.daocao.common.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daocao.common.domain.entity.DysBoggers;
import com.daocao.common.domain.entity.DysUser;
import com.daocao.common.mapper.DysBoggersMapper;
import com.daocao.common.service.IDysBoggersService;
import com.daocao.common.utils.security.DaoCaoSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DysBoggersServiceImpl  extends ServiceImpl<DysBoggersMapper, DysBoggers> implements IDysBoggersService {
    @Autowired
    private DysBoggersMapper dysBoggersMapper;

    /**
     * 根据用户id查询其所有的文章
     * @return
     */
    @Override
    public List<DysBoggers> searchBoggersById() {

        Long AuthorId = DaoCaoSecurityUtil.getUserId();

        LambdaQueryWrapper<DysBoggers> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DysBoggers::getBoggerAuthor,AuthorId);
        List<DysBoggers> dysBoggers = dysBoggersMapper.selectList(wrapper);
        return dysBoggers;
    }

    /**
     * 根据文章id查询其文章详情
     * @param BoggerId
     * @return
     */
    @Override
    public DysBoggers searchOneBogger(Long BoggerId) {
        LambdaQueryWrapper<DysBoggers> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DysBoggers::getBoggerId,BoggerId);
        DysBoggers dysBoggers = dysBoggersMapper.selectOne(wrapper);
        return dysBoggers;
    }

    @Override
    public int addBoggers(DysBoggers dysBoggers) {
        Date date = new Date();
        long timestamp = date.getTime();
        dysBoggers.setBoggerId(timestamp);
        LambdaQueryWrapper<DysBoggers> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DysBoggers::getBoggerAuthor,dysBoggers.getBoggerAuthor());
        int insert = dysBoggersMapper.insert(dysBoggers);
        return insert;
    }
}
