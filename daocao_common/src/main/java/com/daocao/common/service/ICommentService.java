package com.daocao.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.daocao.common.domain.entity.Comments;
import com.daocao.common.domain.entity.Replies;

import java.util.List;

public interface ICommentService  extends IService<Comments> {
//    添加评论
    boolean addComment(Comments comments);
//     获取评论
    List<Comments> getCommentsByArticle(Long articleId);
}


