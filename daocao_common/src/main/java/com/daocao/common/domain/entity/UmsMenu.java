package com.daocao.auth.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("ums_menu")
public class UmsMenu implements Serializable {
    @TableId
    private Long id;
    private Long parentId;
    private String menuName;
    private String path;
    private String ComponentPath;
    private String perms;
    private String icon;
    private Integer menuType;
    private Integer sort;
    private Integer status;

    private String creator;

    private String updater;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private String remark;
    @TableLogic
    private Integer deleted;
}
