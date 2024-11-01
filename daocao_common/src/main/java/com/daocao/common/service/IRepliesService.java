package com.daocao.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.daocao.common.domain.entity.DysBoggers;
import com.daocao.common.domain.entity.Replies;

import java.util.List;

public interface IRepliesService extends IService<Replies> {
//    添加回复
    boolean addReply(Replies reply);
// 获取回复
    List<Replies> getRepliesByComment(Long commentId);

}
