package com.daocao.sysuser.LEARN.SpringLearn;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")//设置模式，在spring的IOC容器种只有一个
public class StudentServiceImpl implements StudentService {
}
