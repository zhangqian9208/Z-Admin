package com.z_admin.back.server.service.system.Impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.z_admin.back.server.service.system.RoleService;
import com.z_admin.back.common.mapper.system.RoleMapper;
import com.z_admin.back.common.dao.system.Admin;
import com.z_admin.back.common.dao.system.Role;
import com.z_admin.back.common.utils.myself.AdminThreadLocal;
import com.z_admin.back.common.utils.myself.TreeUtils;
import com.z_admin.back.common.vo.TreeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author 张骞
 * @version 1.0
 * 角色管理业务层
 */
@Service  //标记为SpringBoot管理的业务层
@Slf4j  //日志注解
public class RoleServiceImpl extends ServiceImpl<RoleMapper,Role> implements RoleService {
    //注入mapper
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 分页查询
     * @param page  页码
     * @param pageSize  每页长度
     * @return
     */
    @Override
    public Page findPage(Integer page, Integer pageSize,String name) {
        //01.配置MP的查询条件，根据时间排序
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Role::getCreated);
        //1.1如果传入的name不为空，则增加查询条件
        if(StrUtil.isNotEmpty(name)){
            queryWrapper.like(Role::getName,name);
        }
        //02.配置分页数据
        Page<Role> pageRole = new Page<>(page,pageSize);
        //03.查询数据
        this.roleMapper.selectPage(pageRole, queryWrapper);
        //04.封装分页数据
        return pageRole;
    }

    /**
     * 插入数据的方法
     * @param role
     * @return
     */
    @Override
    public Boolean insert(Role role){
        //对数据进行非空验证
        if(ObjectUtil.isAllNotEmpty(role.getName())){
            //01.获取当前操作的用户信息
            Admin admin = AdminThreadLocal.get();
            //02.保存数据
            int insert = this.roleMapper.insert(role);
            return insert == 1;
        }
        return false;
    }

    /**
     * 根据id集合删除的方法
     * @param ids
     * @return
     */
    @Override
    public Boolean deleteByIds(List<Long> ids) {
        //构造根据id删除条件
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("role_id",ids);
        //执行删除
        int i = this.roleMapper.delete(queryWrapper);
        //除中间表数据
        for (Long id : ids) {
            this.roleMapper.deleteRoleAndModule(id);
        }
        //返回受影响值
        return i > 0;
    }

    /**
     * 根据id查询单条数据
     * @param id
     * @return
     */
    @Override
    public Role findOne(Long id) {
        return this.roleMapper.selectById(id);
    }

    /**
     * 修改后保存的方法
     * @param role
     * @return
     */
    @Override
    public Boolean update(Role role) {
        //乐观锁
        role.setVersion(role.getVersion());
        //提交并返回，根据受影响的行数，进行布尔值判断
        return this.roleMapper.updateById(role) == 1;
    }


    /**
     * 更新权限数据
     * @param roleId
     * @param moduleIds
     */
    @Override
    public void updateRoleModule(Long roleId, String moduleIds) {
        //第一步，先清空之前关系表的所有数据
        this.roleMapper.deleteRoleAndModule(roleId);
        //检验数据是否为空，如果为空，则不需要保存(也就是数据是否大于2)
        if(moduleIds.length()>2){
            //删除字符串中的括号(括号在第一个和最后一个)
            moduleIds = moduleIds.substring(1,moduleIds.length()-1);
            //第二步，保存新的关系表数据
            String[] strings = moduleIds.split(",");
            for (String moduleIdStr : strings) {
                Long moduleId = Convert.toLong(moduleIdStr);
                this.roleMapper.saveRoleAndModule(roleId, moduleId);
            }
        }

    }

    /**
     * 根据用户查询角色列表
     * @param userId
     * @return
     */
    @Override
    public List<Role> findRoleByUserId(Long userId) {
        return this.roleMapper.findRoleByUserId(userId);
    }

    /**
     * 保存用户与角色的关联数据
     */
    @Override
    public void updateRole(Map map) {
        //获取提交的数据
        Long adminId = Convert.toLong(map.get("adminId"));
        //获取提交的角色集合数据
        Map roles = (Map) map.get("roles");
        //将数据转为字符串
        String checkLists = Convert.toStr(roles.get("checkList"));
        //切割字符串前后大括号，删除字符串中的空格，并且按照","切割，返回字符串数组
        String[] roleIds = checkLists.substring(1,checkLists.length()-1).replaceAll(" ", "").split(",");
        //先删除之前的数据
        this.roleMapper.deleteRoleAndUser(adminId);
        //将新的数据保存进去
        if(checkLists.length()>2){
            for (String roleIdStr : roleIds) {
                this.roleMapper.saveRoleAndUser(adminId, Convert.toLong(roleIdStr));
            }
        }
    }

    /**
     * 根据查询的权限树，进行数据封装的方法
     * @param treeVos
     * @return
     */
    @Override
    public Map<String, Object> convertTree(List<TreeVo> treeVos) {
        // 1.1将后台选中的数据封装成list集合
        List<Long> checks = new ArrayList<>();
        // 1.1.1判断传入的集合非空，避免出现空指针异常
        if(ObjectUtil.isNotEmpty(treeVos)){
            //1.1.2遍历集合
            for (TreeVo treeVo : treeVos) {
                //1.1.3确认集合中对象的属性非空
                if(ObjectUtil.isNotEmpty(treeVo.getChecked())){
                    //1.1.4该属性为boolean属性，如果为true,则执行操作
                    if(treeVo.getChecked()){
                        checks.add(treeVo.getId());
                    }
                }
            }
        }

        //1.2 新的转换方法,使用工具类将子模块数据添加到父模块
        List<TreeVo> treeList = TreeUtils.getTreeList("0", treeVos);

        //1.3封装数据
        Map<String,Object> map = new HashMap<>();
        map.put("tree",treeList);
        map.put("checks",checks);

        //返回数据
        return map;
    }

    /**
     * 根据ModuleId查询关联的角色
     * @param moduleId
     * @return
     */
    @Override
    public List<Role> findRoleByModuleId(Long moduleId) {
        return this.roleMapper.findRoleByModuleId(moduleId);
    }
}
