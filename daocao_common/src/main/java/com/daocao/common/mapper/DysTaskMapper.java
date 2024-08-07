package com.daocao.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.daocao.common.domain.entity.DysTask;
import com.daocao.common.domain.entity.UmsMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DysTaskMapper  extends BaseMapper<DysTask> {
    int changeTaskState(@Param("TaskId") Long TaskId);
}
