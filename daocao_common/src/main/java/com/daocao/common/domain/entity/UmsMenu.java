package com.daocao.common.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@TableName("ums_menu")
public class UmsMenu implements Serializable {
    @TableId
    private Long id;
    private Long parentId;
    @NotNull(message = "菜单名称不能为空")
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


    @TableLogic
    private Integer deleted;

    @TableField(exist = false)
    private List<UmsMenu> children=new ArrayList<>();
}
