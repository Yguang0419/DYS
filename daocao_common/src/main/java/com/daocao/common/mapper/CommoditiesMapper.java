package com.daocao.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.daocao.common.domain.op.Commodities;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


@Mapper
public interface CommoditiesMapper  extends BaseMapper<Commodities> {
//    int changeTaskState(@Param("TaskId") Long TaskId);
}
