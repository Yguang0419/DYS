package com.daocao.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daocao.common.domain.entity.Oprates;
import com.daocao.common.mapper.OpratesMapper;
import com.daocao.common.service.IOpratesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpratesImpl extends ServiceImpl<OpratesMapper, Oprates> implements IOpratesService {
    private final OpratesMapper opratesMapper;
    public OpratesImpl(OpratesMapper opratesMapper, OpratesMapper opratesMapper1) {
        this.opratesMapper = opratesMapper1;
    }
    /*
    * 获取全部OP操作
    * */
    @Override
    public List<Oprates> getAllOp() {
        return opratesMapper.selectList(null);
    }

    @Override
    public boolean addOp(Oprates op) {
        int insert = opratesMapper.insert(op);
        return insert == 1;
    }

    @Override
    public boolean modifyOp(Oprates op) {
        int update = opratesMapper.updateById(op);
        return update==1;
    }
}
