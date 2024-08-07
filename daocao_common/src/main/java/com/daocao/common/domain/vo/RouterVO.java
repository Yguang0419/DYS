package com.daocao.common.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.daocao.common.domain.entity.UmsMenu;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @date 石添
 * @date 2023/12/14
 */
@Data
public class RouterVO implements Serializable {

    private Long id;
    private Long parentId;
    private String menuName;
    private String path;
    private String componentPath;
    private String icon;
    private Integer menuType;
    private List<RouterVO> children = new ArrayList<>();
}
