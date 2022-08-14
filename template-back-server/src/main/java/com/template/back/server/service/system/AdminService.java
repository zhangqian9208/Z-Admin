package com.template.back.server.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.template.back.common.pojo.system.Admin;

import java.util.List;

/**
 * @author 张骞
 * @version
 * 用户业务层封装类
 */
public interface AdminService extends IService<Admin> {
    /**
     * 后台用户登录的校验方法
     * @param username 提交的用户名
     * @param password 提交的密码
     * @return
     */
    Admin login(String username, String password);

    /**
     * 后台用户登录的校验方法(可以查询用户名是否存在)
     * @param username
     * @return
     */
    Admin login(String username);

    /**
     * 根据用户id/用户名/密码查询用户的方法
     * @param adminId
     * @param userName
     * @param password
     * @return
     */
    Admin findAdminById(Long adminId, String userName, String password);

    /**
     * 查询管理员用户列表
     *
     * @param page         当前页码
     * @param pageSize     每页的数据长度
     * @return
     */
    Page findPage(Integer page, Integer pageSize,String name);

    /**
     * 插入一条数据的方法
     * @param admin
     * @return
     */
    Boolean insert(Admin admin);

    /**
     * 根据id删除的方法
     * @param ids
     * @return
     */
    Boolean deleteByIds(List<Long> ids);

    /**
     * 根据id查询单条数据的方法
     * @param id
     * @return
     */
    Admin findOne(Long id);

    /**
     * 更新数据的方法
     * @param admin
     * @return
     */
    Boolean update(Admin admin);

    /**
     * 根据部门id集合查询对应用户
     * @param deptIds
     * @return
     */
    List<Admin> findByDeptId(List<Long> deptIds);

    /**
     * 根据角色id查询用户信息
     * @param roleId
     * @return
     */
    List<Admin> findAdminByRoleId(Long roleId);
}
