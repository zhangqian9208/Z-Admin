package com.z_admin.back.server.controller.system;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.util.Date;

/**
 * @author 张骞
 * @version 1.0.0
 * 测试控制层代码
 */
@Api(tags = "测试相关接口")  //knife4j注解，用于自动生成api文档
@Controller
@Slf4j
public class TestController {

    @ApiOperation(value = "测试接口")  //knife4j注解，用于对接口方法进行说明
    @GetMapping("/hello")
    public String hello(Model m){
        return "Hello,Z-Admin Test Api!";
    }
}
