package com.template.back.server.controller.system;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.util.Date;

/**
 * @author 张骞
 * @version 1.0.0
 * 测试控制层代码
 */
@Controller
@Slf4j
public class TestController {

    @RequestMapping("/hello")
    public String hello(Model m){
        m.addAttribute("now", DateFormat.getDateTimeInstance().format(new Date()));
        return "login";  //视图重定向hello.jsp
    }
}
