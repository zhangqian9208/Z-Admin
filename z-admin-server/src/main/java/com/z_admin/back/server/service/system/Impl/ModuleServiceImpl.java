package com.z_admin.back.server.service.system.Impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.z_admin.back.common.mapper.system.ModuleMapper;
import com.z_admin.back.common.mapper.system.RoleMapper;
import com.z_admin.back.common.dao.system.Admin;
import com.z_admin.back.common.dao.system.Module;
import com.z_admin.back.common.utils.myself.AdminThreadLocal;
import com.z_admin.back.common.vo.TreeVo;
import com.z_admin.back.server.service.system.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 张骞
 * @version 1.0
 * 模块管理业务层
 */
@Service  //标记为SpringBoot管理的业务层
public class ModuleServiceImpl extends ServiceImpl<ModuleMapper,Module> implements ModuleService {
    //注入mapper
    @Autowired
    private ModuleMapper moduleMapper;

    @Autowired
    private RoleMapper roleMapper;
    /**
     * 分页查询的方法
     *
     * @param page     页码
     * @param pageSize 每页长度
     * @return
     */
    @Override
    public Page queryPage(Integer page, Integer pageSize,String name) {
        //01.配置MP的查询条件，根据时间排序
        QueryWrapper<Module> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("sort");
        //1.1如果传入的name不为空，则增加查询条件
        if(StrUtil.isNotEmpty(name)){
            queryWrapper.like("name",name);
        }
        //02.配置分页数据
        Page<Module> modulePage = new Page<>(page, pageSize);
        //03.查询数据
        IPage<Module> moduleIPage = this.moduleMapper.selectPage(modulePage, queryWrapper);

        //04.自关联查询和赋值
        List<Module> moduleList = moduleIPage.getRecords();

        //05.如果集合不为空，则遍历数据
        for (Module module : moduleList) {
            //05-1.查询数据，并为自关联对象赋值
            Module parentModule = this.moduleMapper.selectById(module.getParentId());
            module.setModule(parentModule);
        }
        //05-2.将数据重新仿佛集合
        modulePage.setRecords(moduleList);
        //05-3.封装分页数据
        return modulePage;
    }

    /**
     * 根据id查询的方法
     *
     * @param id 传入查询的id
     * @return
     */
    @Override
    public Module queryById(Long id) {
        return this.moduleMapper.selectById(id);
    }

    /**
     * 查询所有模块
     * @return
     */
    @Override
    public List<Module> findAll() {
        //查询条件处，限制了模块类型，只加载主菜单和二级菜单
        List<Long> arr = new ArrayList<>();
        arr.add(1L);
        arr.add(0L);
        //构造查询条件
        LambdaQueryWrapper<Module> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Module::getCreated);
        queryWrapper.in(Module::getCtype,arr);
        //查询数据并返回
        return this.moduleMapper.selectList(queryWrapper);
    }

    /**
     * 保存数据的方法
     * @param module
     * @return
     */
    @Override
    public Boolean insert(Module module) {
        //对数据进行非空验证
        if(ObjectUtil.isAllNotEmpty(module.getName(),module.getCurl(),module.getCtype(),module.getState())){
            //01.获取当前操作的用户信息
            Admin admin = AdminThreadLocal.get();
            //02.保存数据
            int insert = this.moduleMapper.insert(module);
            return insert == 1;
        }
        return false;
    }

    /**
     * 根据id删除的方法
     * @param ids
     * @return
     */
    @Override
    public Boolean deleteByIds(List<Long> ids) {
        //构造根据id删除条件
        QueryWrapper<Module> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("module_id",ids);
        //执行删除
        int i = this.moduleMapper.delete(queryWrapper);
        //返回受影响值
        return i > 0;
    }

    /**
     * 根据id查询单个数据的方法
     * @param id
     * @return
     */
    @Override
    public Module findOne(Long id) {
        Module module = this.moduleMapper.selectById(id);
        //如果有父模块，则查询
        if(ObjectUtil.isNotEmpty(module.getParentId())){
            Module queryById = this.queryById(module.getParentId());
            module.setModule(queryById);
        }
        return module;
    }

    /**
     * 修改后保存数据的方法
     * @param module
     * @return
     */
    @Override
    public Boolean update(Module module) {
        //乐观锁
        //log.info("当前数据的版本号为："+dept.getVersion());
        module.setVersion(module.getVersion());
        //提交并返回，根据受影响的行数，进行布尔值判断
        return this.moduleMapper.updateById(module) == 1;
    }

    /**
     * 根据角色查询对应的模块数据
     * @param roleId
     * @return
     */
    @Override
    public List<TreeVo> findByRole(Long roleId) {
        //01.根据用户表查询
        return this.roleMapper.findByRole(roleId);
    }


    /**
     * 根据用户id，查询对应的模块，需要通过中间表查询
     * @param adminId
     * @return
     */
    @Override
    public List<Module> findModuleByUserId(Long adminId) {
        return this.moduleMapper.findModuleByUserId(adminId);
    }

    /**
     * 根据模块id查询下方子模块
     * @param id
     * @return
     */
    @Override
    public List<Module> findChildrenById(Long id) {
        return this.moduleMapper.findChildrenById(id);
    }
}
