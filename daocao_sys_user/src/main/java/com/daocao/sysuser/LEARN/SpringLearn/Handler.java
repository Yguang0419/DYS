package com.daocao.sysuser.LEARN.SpringLearn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/use")
public class Handler {

    private int count;
    @Autowired
    private StudentService studentService;
    @GetMapping("/tese")
    public String tese(){
      count++;
        System.out.println(count);
        return null;
    }
}
