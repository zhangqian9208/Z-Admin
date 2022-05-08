package com.template.back.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author 张骞
 * @version 1.0
 * 定时任务配置类
 */
@Configuration
public class ScheduledConfig implements SchedulingConfigurer {

	@Value("${scheduled-thread-pool}")
	private int scheduledThreadPool;
 
	@Override
	public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
		scheduledTaskRegistrar.setScheduler(setTaskExecutors());
	}
	//
	@Bean(destroyMethod = "shutdown")
	public Executor setTaskExecutors() {
		return Executors.newScheduledThreadPool(scheduledThreadPool); // scheduledThreadPool个线程来处理。
	}
}