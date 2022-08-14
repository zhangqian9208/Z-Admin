package com.template.back.server.service.system.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.template.back.common.mapper.system.LoggerMapper;
import com.template.back.common.pojo.system.Logger;
import com.template.back.server.service.system.LoggerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class LoggerServiceImpl extends ServiceImpl<LoggerMapper,Logger> implements LoggerService {
    //注入自定义日志业务层
    @Autowired
    private LoggerService loggerService;

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

        /*PagesVo pagesVo = new PagesVo();
        pagesVo.setList(loggerIPage.getRecords());  //为数据赋值
        pagesVo.setPages(Convert.toInt(loggerIPage.getPages()));  //为页码赋值
        pagesVo.setTotal(loggerIPage.getTotal());  //为总数赋值
        pagesVo.setPrePage(page-1 == 0? page:page-1);  //三元运算符计算上一页
        pagesVo.setNextPage(page+1 > Convert.toInt(loggerIPage.getPages())? Convert.toInt(loggerIPage.getPages()):page+1);  //三元运算符计算下一页
        pagesVo.setPageNum(page);  //当前页码
        pagesVo.setNavigateFirstPage(1);  //首页
        pagesVo.setNavigateLastPage(Convert.toInt(loggerIPage.getPages()));  //尾页*/

        return pageLog;
    }
}
