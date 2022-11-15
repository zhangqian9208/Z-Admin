package com.z_admin.back.common.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.z_admin.back.common.dao.system.Setting;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 张骞
 * @version 1.0
 * 自定义设置项保存数据
 */
@Mapper
public interface SettingMapper extends BaseMapper<Setting> {
}
