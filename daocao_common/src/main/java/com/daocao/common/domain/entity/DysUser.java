package com.daocao.common.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName("dys_user")
public class DysUser implements Serializable {
    @TableId
    private Long userId;

    private String userName;
    @NotNull(message = "用户名称不能为空")
    private String nickName;
    private String avatar;
    private String introduce;
    private String account;
    private String password;
}
