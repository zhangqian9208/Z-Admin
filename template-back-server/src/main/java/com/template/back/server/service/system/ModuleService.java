package com.template.back.server.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.template.back.common.pojo.system.Module;
import com.template.back.common.vo.TreeVo;

import java.util.List;

/**
 * 模块管理业务层约束接口
 */
public interface ModuleService extends IService<Module> {

    /**
     * 分页查询
     * @param page  页码
     * @param pageSize  每页长度
     * @return
     */
    Page queryPage(Integer page, Integer pageSize, String name);

    /**
     * 根据id查询
     * @param id 传入查询的id
     * @return
     */
    Module queryById(Long id);

    /**
     * 查询所有模块
     * @return
     */
    List<Module> findAll();

    /**
     * 新增保存的方法
     * @param module
     * @return
     */
    Boolean insert(Module module);

    /**
     * 根据id删除的方法
     * @param ids
     * @return
     */
    Boolean deleteByIds(List<Long> ids);

    /**
     * 根据id查询单个数据的方法
     * @param id
     * @return
     */
    Module findOne(Long id);

    /**
     * 修改后保存数据的方法
     * @param module
     * @return
     */
    Boolean update(Module module);

    /**
     * 根据角色查询对应的模块数据
     * @param roleId
     * @return
     */
    List<TreeVo> findByRole(Long roleId);

    /**
     * 根据用户id，查询对应的模块，需要通过中间表查询
     * @param adminId
     * @return
     */
    List<Module> findModuleByUserId(Long adminId);

    /**
     * 根据模块id查询下方子模块
     * @param id
     * @return
     */
    List<Module> findChildrenById(Long id);
}
