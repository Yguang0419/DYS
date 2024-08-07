package com.daocao.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.daocao.common.domain.entity.DysBoggers;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface IDysBoggersService extends IService<DysBoggers> {
    //    根据用户id查询所有博客
    List<DysBoggers> searchBoggersById();
    //    根据博客id查询博客
    DysBoggers searchOneBogger(Long BoggerId);

    //新增博客
    int addBoggers(DysBoggers dysBoggers);


}
