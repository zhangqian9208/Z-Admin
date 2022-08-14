package com.template.back.server.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.template.back.common.pojo.system.Logger;

/**
 * @author 张骞
 * @version 1.0.2
 * 错误捕获日志业务层接口
 */
public interface LoggerService extends IService<Logger> {

    Page findPage(Integer page, Integer pageSize);
}
