package com.z_admin.back.common.utils.myself;

import com.z_admin.back.common.mapper.system.LoggerMapper;
import com.z_admin.back.common.dao.system.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author 张骞
 * @version 1.0.0
 * 自定义错误日志参数工具类
 */
@Component
public final class LoggerUtils {
    //注入
    @Autowired
    private LoggerMapper loggerMapper;

    private static LoggerUtils loggerUtils;

    /**
     * 解决静态方法不能注入mapper层的问题
     */
    @PostConstruct
    public void init() {
        loggerUtils = this;
        loggerUtils.loggerMapper = this.loggerMapper;
    }

    //私有化构造方法
    private LoggerUtils(){}

    /**
     * 保存用户自定义日志+当前登录用户信息的方法
     * @param stackTrace  出现异常的线程所包含的信息
     * @param exception  抛出的异常
     * @param remark  用户自定义备注
     */
    public static void saveLogAndUser(StackTraceElement stackTrace, Exception exception, String remark) {
        //创建日志对象
        Logger logger = new Logger();
        logger.setClasses(stackTrace.getClassName());  //获取抛出异常的类
        logger.setMethod(stackTrace.getMethodName());  //获取抛出异常的方法
        logger.setUserId(AdminThreadLocal.get().getAdminId().toString());  //获取当前登录的用户id
        logger.setNickName(AdminThreadLocal.get().getUserName());  //获取当前登录的用户姓名
        logger.setException(exception.getMessage());  //获取异常简要信息-异常类型
        logger.setRemark(remark);  //获取用户自定义的备注
        //插入数据
        loggerUtils.loggerMapper.insert(logger);
    }

    /**
     * 保存用户自定义日志+当前登录用户信息的方法
     * @param stackTrace  出现异常的线程所包含的信息
     * @param exception  抛出的异常
     */
    public static void saveLogAndUser(StackTraceElement stackTrace, Exception exception) {
        //创建日志对象
        Logger logger = new Logger();
        logger.setClasses(stackTrace.getClassName());  //获取抛出异常的类
        logger.setMethod(stackTrace.getMethodName());  //获取抛出异常的方法
        logger.setUserId(AdminThreadLocal.get().getAdminId().toString());  //获取当前登录的用户id
        logger.setNickName(AdminThreadLocal.get().getUserName());  //获取当前登录的用户姓名
        logger.setException(exception.getMessage());  //获取异常简要信息-异常类型
        //插入数据
        loggerUtils.loggerMapper.insert(logger);
    }

}
