package com.daocao.common.domain.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
@Data
@TableName("dys_boggers")
public class DysBoggers implements Serializable {
    @TableId
    private Long boggerId;
    private String boggerTitle;
    private String authorName;
    @NotNull(message = "用户名称不能为空")
    private String boggerContent;
    private String boggerCoverImage;
    private Long boggerAuthor;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime boggerCreateTime;
    private List<String> boggerTags;
    // 新增方法，用于格式化LocalDateTime对象


}
