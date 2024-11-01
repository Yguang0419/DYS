package com.daocao.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daocao.common.domain.entity.Comments;
import com.daocao.common.domain.entity.DysTask;
import com.daocao.common.domain.entity.Replies;
import com.daocao.common.domain.vo.TaskVO;
import com.daocao.common.mapper.DysTaskMapper;
import com.daocao.common.mapper.RepliesMapper;
import com.daocao.common.mapper.TaskVOMapper;
import com.daocao.common.service.IDysTaskService;
import com.daocao.common.service.IRepliesService;
import com.daocao.common.service.TaskVOService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RepliesServiceImpl extends ServiceImpl<RepliesMapper, Replies> implements IRepliesService {
    private final RepliesMapper repliesMapper;
    public RepliesServiceImpl(RepliesMapper repliesMapper) {
        this.repliesMapper = repliesMapper;
    }
    @Override
    public boolean addReply(Replies reply) {
        int i = repliesMapper.insert(reply);
        return i==1;
    }

    @Override
    public List<Replies> getRepliesByComment(Long commentId) {
        // 根据评论id获取其回复
        List<Replies> replies = repliesMapper.selectList(new QueryWrapper<Replies>().
                eq("comment_id", commentId));
        return replies;
    }
}
