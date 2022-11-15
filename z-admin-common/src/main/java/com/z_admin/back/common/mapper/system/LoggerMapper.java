package com.z_admin.back.common.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.z_admin.back.common.dao.system.Logger;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author 张骞
 * @version 1.0.0
 * @Description logger对应的mapper，使用了mybatisplus
 */
@Mapper
public interface LoggerMapper extends BaseMapper<Logger> {
}
