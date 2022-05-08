package com.template.back.server.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author 张骞
 * @version 1.0.2
 * 错误捕获日志业务层接口
 */
public interface LoggerService {

    Page findPage(Integer page, Integer pageSize);
}
