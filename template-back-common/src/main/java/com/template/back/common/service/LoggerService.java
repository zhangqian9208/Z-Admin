package com.template.back.common.service;

import com.template.back.common.mapper.system.LoggerMapper;
import com.template.back.common.pojo.system.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 张骞
 * @version 1.0.0
 * 日志表格记录
 */
@Service
@Slf4j
public class LoggerService {
    //01注入mapper
    @Autowired
    private LoggerMapper loggerMapper;

    //02保存日志的方法
    //2.1记录包含用户信息的日志
    public void saveLogAndUser(String classes,String method,String userName,Exception exception){
        //创建日志对象
        Logger logger = new Logger();
        logger.setClasses(classes);
        logger.setMethod(method);
        logger.setUserId(null);
        logger.setNickName(userName);
        logger.setException(exception.getMessage());
        logger.setRemark(null);
        //插入数据
        this.loggerMapper.insert(logger);
    }
    //2.1.1含开发者备注的重载方法
    public void saveLogAndUser(String classes,String method,String userName,Exception exception,String remark){
        //创建日志对象
        Logger logger = new Logger();
        logger.setClasses(classes);
        logger.setMethod(method);
        logger.setUserId(null);
        logger.setNickName(userName);
        logger.setException(exception.getMessage());
        logger.setRemark(remark);
        //插入数据
        this.loggerMapper.insert(logger);
    }

    //2.2记录不包含用户信息的日志
    public void saveLog(String classes,String method,Exception exception){
        //创建日志对象
        Logger logger = new Logger();
        logger.setClasses(classes);
        logger.setMethod(method);
        logger.setException(exception.getMessage());
        logger.setRemark(null);
        //插入数据
        this.loggerMapper.insert(logger);
    }
    //2.2.1不含开发者备注的重载方法
    public void saveLog(String classes,String method,Exception exception,String remark){
        //创建日志对象
        Logger logger = new Logger();
        logger.setClasses(classes);
        logger.setMethod(method);
        logger.setException(exception.getMessage());
        logger.setRemark(remark);
        //插入数据
        this.loggerMapper.insert(logger);
    }

}
