package com.z_admin.back.common.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.z_admin.back.common.dao.system.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 张骞
 * @version 1.0
 * 管理员用户mapper
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    //中间表查询方法，关联用户和角色之间的关系，根据角色id查询用户
    @Select("select  admin_id, name,user_name from tb_admin where admin_id IN (SELECT admin_id FROM tb_role_admin WHERE role_id = #{roleId}) ")
    List<Admin> findAdminByRoleId(Long roleId);
}
