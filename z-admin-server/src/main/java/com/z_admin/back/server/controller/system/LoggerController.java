package com.z_admin.back.server.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.z_admin.back.common.utils.myself.LoggerUtils;
import com.z_admin.back.common.vo.R;
import com.z_admin.back.server.service.system.LoggerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 张骞
 * 自定义日志控制层
 */
@Api(tags = "后台日志相关接口")  //knife4j注解，用于自动生成api文档
@RestController
@RequestMapping("system/logger")
@Slf4j
public class LoggerController {

    //注入自定义日志业务层
    @Autowired
    private LoggerService logService;

    /**
     * 查看日志列表的功能
     * @return
     */
    @ApiOperation(value = "查询日志列表接口")  //knife4j注解，用于对接口方法进行说明
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "当前页码",required = true,defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize",value = "每页数据长度",required = true,defaultValue = "10")
    })  //knife4j注解，用于对接口参数进行说明
    @GetMapping("page")
    public R<Page> loggerList(@RequestParam(value = "page",defaultValue = "1")Integer page,
                              @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize
                              ){
        try{
            //调用业务层的查询方法
            Page pageInfo = this.logService.findPage(page, pageSize);
            return R.success(pageInfo);
        }catch (Exception exception){
            //记录自定义异常日志
            LoggerUtils.saveLogAndUser(Thread.currentThread().getStackTrace()[1],exception, "后台-查看日志列表异常");
            //打印错误日志
            log.error("Z-Admin后台模板工程出现异常！异常信息为：" + exception);
        }
        return R.error("查询数据出错");
    }
}
