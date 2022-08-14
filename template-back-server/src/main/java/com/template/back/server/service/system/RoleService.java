package com.template.back.server.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.template.back.common.pojo.system.Role;
import com.template.back.common.vo.TreeVo;

import java.util.List;
import java.util.Map;

/**
 * 角色管理业务层约束接口
 */
public interface RoleService extends IService<Role> {

    /**
     * 分页查询
     * @param page  页码
     * @param pageSize  每页长度
     * @return
     */
    Page findPage(Integer page, Integer pageSize, String name);

    /**
     * 插入数据的方法
     * @param role
     * @return
     */
    Boolean insert(Role role);

    /**
     * 根据id删除的方法
     * @param ids
     * @return
     */
    Boolean deleteByIds(List<Long> ids);

    /**
     * 根据id查询单条数据
     * @param id
     * @return
     */
    Role findOne(Long id);

    /**
     * 修改后保存的方法
     * @param role
     * @return
     */
    Boolean update(Role role);


    /**
     * 更新权限数据
     * @param roleId
     * @param moduleIds
     */
    void updateRoleModule(Long roleId, String moduleIds);

    /**
     * 根据用户查询角色列表
     * @param adminId
     * @return
     */
    List<Role> findRoleByUserId(Long adminId);

    /**
     * 保存用户与角色的关联数据
     */
    void updateRole(Map map);

    /**
     * 根据查询的权限树，进行数据封装的方法
     * @param treeVos
     * @return
     */
    Map<String, Object> convertTree(List<TreeVo> treeVos);

    /**
     * 根据ModuleId查询关联的角色
     * @param moduleId
     * @return
     */
    List<Role> findRoleByModuleId(Long moduleId);
}
