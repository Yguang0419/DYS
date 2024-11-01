package com.daocao.common.domain.op;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("op_commodities")
public class Commodities implements Serializable {
    @TableId
    private Long id;
    private Long name;
    private Float price;
    private int stock;
}
