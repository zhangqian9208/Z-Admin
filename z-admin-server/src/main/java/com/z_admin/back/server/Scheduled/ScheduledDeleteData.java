package com.z_admin.back.server.Scheduled;

import com.z_admin.back.common.mapper.system.HeartbeatMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author 张骞
 * 测试定时任务的方法
 */
@Component
@Slf4j
public class ScheduledDeleteData {

	private static final Logger logger = LogManager.getLogger(ScheduledDeleteData.class);

	//注入心跳连接数据库
	@Autowired
	private HeartbeatMapper heartbeatMapper;

	//时间间隔，单位毫秒(10小时执行一次)
	@Scheduled(fixedRate = 1000*60*60*10)
	public void scheduledExcSql() {
		this.heartbeatMapper.selectById(1L);
		log.info("——————————————————————————————————————————————");
		log.info("数据库心跳连接10小时执行一次，避免上线后数据库连接失败。");
		log.info("——————————————————————————————————————————————");
	}
}
