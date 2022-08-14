package com.template.back.common.mapper.school;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.template.back.common.pojo.school.Campus;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 张骞
 * @version 1.0.0
 * 校区映射接口
 */
@Mapper
public interface CampusMapper extends BaseMapper<Campus> {
}
