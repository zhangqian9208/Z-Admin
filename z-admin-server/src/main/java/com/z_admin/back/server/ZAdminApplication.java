package com.z_admin.back.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 张骞
 * @version 2.0.0
 * Z-Admin项目启动类
 */

@Slf4j  //记录日志
@EnableSwagger2
@EnableScheduling  //开启定时任务
@ServletComponentScan  //过滤器扫描注解
@EnableCaching  //启用Spring Cache框架注解
@ComponentScan(basePackages={"com.z_admin"})  //由于上传逻辑移动至common工程，所以需要改变扫描把范围才能扫描到
@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})  //排除mongo的自动配置
public class ZAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZAdminApplication.class, args);
        log.info("————————————————————————");
        log.info("Z-Admin后台项目启动成功...");
        log.info("————————————————————————");
    }
}
