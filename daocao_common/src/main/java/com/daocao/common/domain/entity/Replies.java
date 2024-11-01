package com.daocao.common.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 回复实体类
 */
@Data
@TableName("replies")
public class Replies implements Serializable {
    @TableId
    private Long id;
    private Long commentId;
    @NotNull(message = "评论不能为空")
    private String content;
    private String author;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime createTime;
}
