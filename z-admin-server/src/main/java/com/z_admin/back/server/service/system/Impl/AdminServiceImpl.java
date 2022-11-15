package com.z_admin.back.server.service.system.Impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.z_admin.back.common.mapper.system.AdminMapper;
import com.z_admin.back.common.mapper.system.DeptMapper;
import com.z_admin.back.common.mapper.system.RoleMapper;
import com.z_admin.back.common.dao.system.Admin;
import com.z_admin.back.common.dao.system.Dept;
import com.z_admin.back.common.utils.myself.AdminThreadLocal;
import com.z_admin.back.server.service.system.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张骞
 * @version 1.0
 * 用户
 */
@Service //业务层注解
@Slf4j   //控制台日志注解
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    //TODO 管理员用户密码的保存，没有进行加密，若考虑安全问题，可以使用MD5加密

    //注入mapper层
    @Autowired
    private AdminMapper adminMapper;

    //注入部门mapper
    @Autowired
    private DeptMapper deptMapper;

    //注入角色mapper
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 业务层管理员登录的方法
     *
     * @param userName 提交的用户名
     * @param password 提交的密码
     * @return 返回查询到的管理员用户数据
     */
    @Override
    public Admin login(String userName, String password) {
        //01构造查询条件
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName).eq("password", password).eq("state", 1);
        log.info("业务层方法被调用！" + userName + "," + password);
        //02查询数据
        Admin admin = this.adminMapper.selectOne(queryWrapper);
        //03返回数据
        log.info("业务层方法被调用！" + admin);
        return admin;
    }

    /**
     * 业务层管理员登录的方法（单独验证用户名）
     *
     * @param userName 提交的用户名
     * @return 返回查询到的管理员用户数据
     */
    @Override
    public Admin login(String userName) {
        //构造查询条件
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getUserName, userName);
        //查询数据
        Admin admin = this.adminMapper.selectOne(queryWrapper);
        //返回数据
        return admin;
    }

    /**
     * 根据用户id/用户名/密码查询用户的方法，用于验证登录
     *
     * @param adminId
     * @param userName
     * @param password
     * @return
     */
    @Override
    public Admin findAdminById(Long adminId, String userName, String password) {
        //01构造查询条件
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("admin_id", adminId).eq("user_name", userName).eq("password", password).eq("state", 1);
        log.info("业务层验证登录方法被调用！" + userName + "," + password);
        //02查询数据
        Admin admin = this.adminMapper.selectOne(queryWrapper);
        //03返回数据
        log.info("业务层验证登录方法被调用！" + admin);
        return admin;
    }

    /**
     * 查询管理员用户列表
     *
     * @param page     当前页码
     * @param pageSize 每页的数据长度
     * @return
     */
    @Override
    public Page findPage(Integer page, Integer pageSize, String name) {
        //01.配置MP的查询条件，根据时间排序
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("created");
        if (StrUtil.isNotEmpty(name)) {
            queryWrapper.like("name", name);
        }
        //02.配置分页数据
        Page<Admin> pageAdmin = new Page<>(page, pageSize);
        //03.查询数据
        IPage<Admin> adminIPage = this.adminMapper.selectPage(pageAdmin, queryWrapper);

        //04.查询关联的部门信息并赋值
        List<Admin> adminList = adminIPage.getRecords();

        //05.如果为空，直接返回数据
        if (CollUtil.isEmpty(adminList)) {
            //05-1.封装分页数据
            return pageAdmin;
        }

        //06.如果集合不为空，则遍历数据
        for (Admin admin : adminList) {
            //06-1.查询数据，并为自关联对象赋值
            Dept dept = this.deptMapper.selectById(admin.getDeptId());
            admin.setDept(dept);
        }

        //06-2.将数据重新放入集合
        pageAdmin.setRecords(adminList);
        //06-3.封装分页数据
        return pageAdmin;
    }

    /**
     * 插入一条数据的方法
     *
     * @param admin
     * @return
     */
    @Override
    public Boolean insert(Admin admin) {
        //对数据进行非空验证
        if (ObjectUtil.isAllNotEmpty(admin.getName(), admin.getUserName(), admin.getPassword(), admin.getState())) {
            //01.获取当前操作的用户信息
            Admin adminInfo = AdminThreadLocal.get();
            //02.保存数据
            int insert = this.adminMapper.insert(admin);
            return insert == 1;
        }
        return false;
    }

    /**
     * 根据id删除的方法
     *
     * @param ids
     * @return
     */
    @Override
    public Boolean deleteByIds(List<Long> ids) {
        //构造根据id删除条件
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Admin::getAdminId, ids);
        int delete = this.adminMapper.delete(queryWrapper);
        //删除中间表
        for (Long id : ids) {
            this.roleMapper.deleteRoleAndUser(id);
        }
        //返回状态
        return delete > 0;
    }

    /**
     * 根据id查询单条数据的方法
     *
     * @param id
     * @return
     */
    @Override
    public Admin findOne(Long id) {
        return this.adminMapper.selectById(id);
    }

    /**
     * 修改后保存数据的方法
     *
     * @param admin
     * @return
     */
    @Override
    public Boolean update(Admin admin) {
        //乐观锁
        //log.info("当前数据的版本号为："+dept.getVersion());
        admin.setVersion(admin.getVersion());
        //提交并返回，根据受影响的行数，进行布尔值判断
        return this.adminMapper.updateById(admin) == 1;
    }

    /**
     * 根据部门id集合查询对应用户
     *
     * @param deptIds
     * @return
     */
    @Override
    public List<Admin> findByDeptId(List<Long> deptIds) {
        //构造查询条件
        LambdaQueryWrapper<Admin> adminQueryWrapper = new LambdaQueryWrapper<>();
        adminQueryWrapper.in(Admin::getDeptId, deptIds);
        //查询数据
        List<Admin> admins = this.adminMapper.selectList(adminQueryWrapper);
        return admins;
    }

    /**
     * 根据角色id查询用户信息
     *
     * @param roleId
     * @return
     */
    @Override
    public List<Admin> findAdminByRoleId(Long roleId) {
        return this.adminMapper.findAdminByRoleId(roleId);
    }
}
