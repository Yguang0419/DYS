package com.daocao.sysuser.controller;

import com.daocao.common.domain.entity.Comments;
import com.daocao.common.domain.entity.DysBoggers;
import com.daocao.common.domain.vo.TaskVO;
import com.daocao.common.response.DaocaoResult;
import com.daocao.common.service.impl.CommentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("comment")
public class CommentController {
    private final CommentServiceImpl commentService;

    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }
//    获取评论
    @GetMapping("{BoggerId}")
    public DaocaoResult searchComments(@PathVariable("BoggerId") Long BoggerId){
        List<Comments> commentsByArticle = commentService.getCommentsByArticle(BoggerId);
        return DaocaoResult.success(commentsByArticle);
    }
//    添加评论
    @PostMapping()
    public DaocaoResult addComment(@Validated @RequestBody Comments comments){
        boolean b = commentService.addComment(comments);
        if (b){return DaocaoResult.success("回复成功");}
        return DaocaoResult.error();
    }
}
