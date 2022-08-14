package com.template.back.server;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 张骞
 * @version 1.0.0
 * 后台独立项目启动类
 */
//开启定时任务
@EnableScheduling
//启用Spring Cache框架注解
@EnableCaching
//由于上传逻辑移动至common工程，所以需要改变扫描把范围才能扫描到
@ComponentScan(basePackages={"com.template"})
//排除mongo的自动配置
@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
//过滤器扫描注解
@ServletComponentScan
//记录日志
@Slf4j
public class BackApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackApplication.class, args);
        log.info("项目启动成功...");
    }
}
