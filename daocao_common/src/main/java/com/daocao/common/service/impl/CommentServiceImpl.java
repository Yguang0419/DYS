package com.daocao.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daocao.common.domain.entity.Comments;
import com.daocao.common.domain.entity.Replies;
import com.daocao.common.mapper.CommentMapper;
import com.daocao.common.service.ICommentService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comments> implements ICommentService {

    private final CommentMapper commentMapper;
    private final  RepliesServiceImpl repliesService;
    public CommentServiceImpl (CommentMapper commentMapper, RepliesServiceImpl repliesService){
        this.commentMapper = commentMapper;
        this.repliesService = repliesService;
    }
    @Override
    public boolean addComment(Comments comments) {
        int i = commentMapper.insert(comments);
        return i==1;
    }

    @Override
    public List<Comments> getCommentsByArticle(Long BoggerId) {
//        根据文章id查询其对应的评论
        List<Comments> comments = commentMapper.selectList(new QueryWrapper<Comments>().
                eq("bogger_id", BoggerId));
        comments.forEach(comment -> {
            List<Replies> repliesByComment = repliesService.getRepliesByComment(comment.getId());
//            将评论和回复整合成一个集合对象set到comments元素中
            comment.setReplies(repliesByComment);
        });

        return comments;
    }
}
