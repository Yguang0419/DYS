package com.daocao.sysuser.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @date 石添
 * @date 2023/12/24
 */
@Data
public class UserInfoVO implements Serializable {

    private Long id;
    private String nickname;
    private String avatar;
}
