package com.z_admin.back.common.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.z_admin.back.common.dao.system.Role;
import com.z_admin.back.common.vo.TreeVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 张骞
 * @version 1.0
 * 角色mapper
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 根据角色查询已经授权的模块
     * @param roleId
     * @return
     */
    @Select("select\n" +
            "            module_id as id,\n" +
            "            parent_id as parentId,\n" +
            "            name as name,\n" +
            "            case\n" +
            "                when module_id in (SELECT module_id FROM tb_role_module WHERE role_id=#{roleId})\n" +
            "                    then 'true'\n" +
            "                    else 'false'\n" +
            "                end\n" +
            "            as checked\n" +
            "        from\n" +
            "             tb_module")
    List<TreeVo> findByRole(Long roleId);

    /**
     * 用户角色中间表删除，根据角色名称删除所有中间表数据
     * @param roleId
     */
    @Delete("delete from tb_role_module  where role_id = #{roleId}")
    void deleteRoleAndModule(Long roleId);


    /**
     * 用户角色中间表保存
     * @param roleId
     * @param moduleId
     */
    @Insert("insert into tb_role_module (role_id, module_id) values (#{roleId},#{moduleId})")
    void saveRoleAndModule(Long roleId, Long moduleId);


    //中间表查询方法，关联用户和角色之间的关系，根据用户id查询角色
    @Select("select\n" +
            "            role_id,\n" +
            "            name,\n" +
            "            case\n" +
            "                when role_id IN (SELECT role_id FROM tb_role_admin WHERE admin_id = #{'adminId'})\n" +
            "                then 'checked'\n" +
            "                else ''\n" +
            "                end\n" +
            "                as checked\n" +
            "        from tb_role")
    List<Role> findRoleByUserId(Long adminId);

    /**
     * 删除用户与角色关联
     * @param adminId
     */
    @Delete("delete from tb_role_admin where admin_id = #{adminId}")
    void deleteRoleAndUser(Long adminId);

    /**
     * 保存用户与角色关联
     * @param adminId  传入的管理员id
     * @param roleId  传入的角色id
     */
    @Insert("insert into tb_role_admin (admin_id,role_id) values (#{adminId}, #{roleId})")
    void saveRoleAndUser(Long adminId, Long roleId);

    @Select("select * from tb_role where role_id in (select role_id from tb_role_module where module_id=#{moduleId})")
    List<Role> findRoleByModuleId(Long moduleId);
}
