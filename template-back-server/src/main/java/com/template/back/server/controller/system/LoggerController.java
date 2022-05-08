package com.template.back.server.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.template.back.common.service.LoggerService;
import com.template.back.common.vo.R;
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
@RestController
@RequestMapping("system/logger")
@Slf4j
public class LoggerController {
    //注入自定义日志业务层
    @Autowired
    private LoggerService loggerService;

    @Autowired
    private com.template.back.server.service.system.LoggerService logService;

    /**
     * 查看日志列表的功能
     * @return
     */
    @GetMapping("page")
    public R<Page> loggerList(@RequestParam(value = "page",defaultValue = "1")Integer page,
                              @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize
                              ){
        //调用业务层的查询方法
        Page pageInfo = this.logService.findPage(page, pageSize);
        return R.success(pageInfo);
    }
}
