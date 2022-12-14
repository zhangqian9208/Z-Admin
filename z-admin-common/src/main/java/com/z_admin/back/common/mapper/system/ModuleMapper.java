package com.z_admin.back.common.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.z_admin.back.common.dao.system.Module;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 张骞
 * @version 1.0
 * 功能模块mapper
 */
@Mapper
public interface ModuleMapper extends BaseMapper<Module> {
    /**
     * 根据用户id，查询对应的模块，需要通过中间表查询
     * @param adminId
     * @return
     */
    @Select("SELECT DISTINCT\n" +
            "            m.module_id As id, m.parent_id, m.name, m.ctype,m.req, m.state, m.curl,m.icon, m.remark\n" +
            "        FROM\n" +
            "            tb_module AS m,\n" +
            "            tb_role_module AS rm,\n" +
            "            tb_role_admin AS ru\n" +
            "        WHERE\n" +
            "                m.`module_id`=rm.`module_id`\n" +
            "        AND\t    rm.`role_id`=ru.`role_id`\n" +
            "        AND\t    ru.`admin_id`=#{adminId}")
    List<Module> findModuleByUserId(Long adminId);

    /**
     * 根据部门id，查询对应的子模块
     * @param id
     * @return
     */
    @Select("select * from tb_module where parent_id = #{id}")
    List<Module> findChildrenById(Long id);
}
