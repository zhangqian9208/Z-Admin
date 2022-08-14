package com.template.back.common.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.template.back.common.pojo.Heartbeat;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 张骞
 * @version 1.0.0
 * 心跳连接映射类
 */
@Mapper
public interface HeartbeatMapper extends BaseMapper<Heartbeat> {
}
