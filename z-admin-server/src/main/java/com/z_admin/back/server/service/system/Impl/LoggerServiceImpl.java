package com.z_admin.back.server.service.system.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.z_admin.back.common.mapper.system.LoggerMapper;
import com.z_admin.back.common.dao.system.Logger;
import com.z_admin.back.server.service.system.LoggerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class LoggerServiceImpl extends ServiceImpl<LoggerMapper,Logger> implements LoggerService {

    @Autowired
    private LoggerMapper loggerMapper;

    @Override
    public Page findPage(Integer page, Integer pageSize) {
        //01.配置MP的查询条件，根据时间排序
        QueryWrapper<Logger> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("created");
        //02配置分页数据
        Page<Logger> pageLog = new Page<>(page,pageSize);
        //03.查询数据
        IPage<Logger> loggerIPage = loggerMapper.selectPage(pageLog, queryWrapper);
        //04.封装分页数据

        return pageLog;
    }
}
