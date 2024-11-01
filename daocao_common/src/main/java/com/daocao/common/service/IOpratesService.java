package com.daocao.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.daocao.common.domain.entity.Oprates;

import java.util.List;

public interface IOpratesService  extends IService<Oprates> {
    /*获取全部操作demo*/
    List<Oprates> getAllOp();

    /*新增操作Demo*/
    boolean addOp(Oprates op);
    /*修改操作demo*/
    boolean modifyOp(Oprates op);
}
