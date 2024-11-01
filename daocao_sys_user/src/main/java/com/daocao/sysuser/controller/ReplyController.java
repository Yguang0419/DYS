package com.daocao.sysuser.controller;

import com.daocao.common.domain.entity.Comments;
import com.daocao.common.domain.entity.Replies;
import com.daocao.common.response.DaocaoResult;
import com.daocao.common.service.impl.RepliesServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("reply")
public class ReplyController {
    private final RepliesServiceImpl repliesService;

    public ReplyController(RepliesServiceImpl repliesService) {
        this.repliesService = repliesService;
    }

    @PostMapping()
    public DaocaoResult addComment(@Validated @RequestBody Replies replies){
        boolean b = repliesService.addReply(replies);
        if (b){return DaocaoResult.success("回复成功");}
        return DaocaoResult.error();
    }
}
