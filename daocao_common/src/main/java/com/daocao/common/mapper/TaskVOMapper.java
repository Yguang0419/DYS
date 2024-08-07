package com.daocao.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.daocao.common.domain.entity.DysUser;
import com.daocao.common.domain.vo.TaskVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskVOMapper extends BaseMapper<TaskVO> {
}