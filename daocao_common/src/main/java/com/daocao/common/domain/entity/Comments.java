package com.daocao.common.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 评论实体类
 */
@Data
@TableName("comments")
public class Comments implements Serializable {
    @TableId
    private Long id;
    private Long boggerId;
    @NotNull(message = "评论不能为空")
    private String content;
    private String author;
    @TableField(exist = false)
    private List<Replies> replies;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime createTime;

}
