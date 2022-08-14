package com.template.back.common.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.template.back.common.pojo.system.Dept;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 张骞
 * @version 1.0
 * 管理员部门mapper
 */
@Mapper
public interface DeptMapper extends BaseMapper<Dept> {
}
